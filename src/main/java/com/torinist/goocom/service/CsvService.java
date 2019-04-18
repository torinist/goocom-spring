package com.torinist.goocom.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.torinist.goocom.dto.BookCsvUpdatedDto;
import com.torinist.goocom.entity.BookEntity;
import com.torinist.goocom.entity.CircleEntity;
import com.torinist.goocom.repository.BookRepository;
import com.torinist.goocom.repository.CircleRepository;
import com.torinist.goocom.util.exception.LogicException;

/**
 * CSVに関するサービス<br>
 * 他のサービスとは異なり、複数のリポジトリを使うような使い方をするため、BaseServiceInterfaceはimplementsしない
 * 
 * @author oki
 *
 */
@Service
public class CsvService {

	@Autowired
	CircleRepository circleRepository;

	@Autowired
	BookRepository bookRepository;

	/**
	 * 本一覧、サークル一覧をCSVから取得する.
	 * 
	 * @param eventYear イベント年
	 * @param file CSVファイル
	 * @return 登録した本のレコード数
	 * @throws LogicException ファイル読み込みに失敗
	 */
	public int uploadCsvBooks(String eventYear, MultipartFile file) throws LogicException {
		List<BookCsvUpdatedDto> dtos = new ArrayList<>();
		// CSVを読み込む
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "utf-8"))) {
			String str;
			while ((str = br.readLine()) != null) {
				// 1行をカンマで分割する
				String[] data = str.split(",", 0);

				// 本インスタンスを作成する
				BookEntity book = new BookEntity();
				book.setName(data[5]);
				book.setIssueYear(Integer.parseInt(eventYear));

				// サークルインスタンスを作成する
				CircleEntity circle = new CircleEntity();
				circle.setName(data[4]);

				// リストに追加する
				BookCsvUpdatedDto dto = new BookCsvUpdatedDto(book, circle);
				dtos.add(dto);
			}
		} catch (IOException e) {
			throw new LogicException("ファイル読み込みに失敗しました。" + e);
		}

		// 本とサークルをアップロードする
		return uploadBookCsvUpdatedDtos(dtos);

	}

	/**
	 * BookとCircleを登録する.<br>
	 * Circleはサークル名で検索を行い、同じレコードがあったら登録は行わない.<br>
	 * 
	 * @param dtos
	 * @return
	 */
	int uploadBookCsvUpdatedDtos(List<BookCsvUpdatedDto> dtos) {

		// 登録した本の数を計測する
		int bookCount = 0;
		// サークル登録をする
		for (BookCsvUpdatedDto dto : dtos) {
			CircleEntity circleEntity = dto.getCircle();

			// circleEntityの存在確認を行うため、circleEntityのサークル名と同じレコードをDBから取得する
			List<CircleEntity> circleNameLikes = circleRepository
					.findByNameLikeOrderByIdAsc(circleEntity.getName());
			// 一致するサークル名があったら、取得したレコードの1番目をcircleEntityに入れる
			if (circleNameLikes == null || circleNameLikes.isEmpty()) {
				// CircleEntityがDBになかったらそのまま登録
				circleEntity = circleRepository.save(circleEntity);
			} else {
				// circleEntityがすでにDBに登録されている場合、saveしない
				circleEntity = circleNameLikes.get(0);
			}

			BookEntity bookEntity = dto.getBook();
			bookEntity.setCircleId(circleEntity.getId());
			bookEntity = bookRepository.save(bookEntity);

			bookCount++;
		}
		return bookCount;
	}
}

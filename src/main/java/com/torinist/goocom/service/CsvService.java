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
import com.torinist.goocom.entity.CsvUpdatedEntity;
import com.torinist.goocom.repository.BookRepository;
import com.torinist.goocom.repository.CircleRepository;
import com.torinist.goocom.repository.CsvUpdatedRepository;
import com.torinist.goocom.util.StringUtils;
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

	@Autowired
	CsvUpdatedRepository csvUpdatedRepository;

	/**
	 * 本一覧、サークル一覧をCSVから取得する.
	 * 
	 * @param eventDate イベント年月日
	 * @param file CSVファイル
	 * @return 登録した本のレコード数
	 * @throws LogicException ファイル読み込みに失敗
	 */
	public int uploadCsvBooks(String eventDate, MultipartFile file) throws LogicException {
		List<BookCsvUpdatedDto> dtos = new ArrayList<>();

		// eventDateから年だけ取得する
		String eventYear = StringUtils.getStringYear(eventDate);

		// CSV更新情報を登録する
		CsvUpdatedEntity csvUpdatedEntity = insertCsvUpdated(eventDate);

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
		int bookCount = uploadBookCsvUpdatedDtos(dtos);
		
		// CSVを更新する
		updateCsvUpdated(csvUpdatedEntity);
		
		return bookCount;

	}

	/**
	 * CSV更新情報を登録する.
	 * 
	 * @param eventDate イベント年月日
	 * @return 登録したCSV更新情報
	 */
	CsvUpdatedEntity insertCsvUpdated(String eventDate) {
		CsvUpdatedEntity entity;
		// eventDateを検索する
		List<CsvUpdatedEntity> entities = csvUpdatedRepository.findByUpdatedTabNameLikeOrderByIdAsc(eventDate);
		if (entities.isEmpty()) {
			// 取得出来なかったら登録する
			entity = new CsvUpdatedEntity();
			entity.setUpdatedTabName(eventDate);
			entity.setUpdatedFlg(false);
			entity = csvUpdatedRepository.save(entity);
		} else {
			// 取得出来たらそれを返す
			entity = entities.get(0);
		}
		return entity;
	}

	/**
	 * CSV更新情報を更新する.
	 * 
	 * @param entity 登録したCSV更新情報
	 * @return 更新したCSV更新情報
	 */
	CsvUpdatedEntity updateCsvUpdated(CsvUpdatedEntity entity) {
		entity.setUpdatedFlg(true);
		return csvUpdatedRepository.save(entity);
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

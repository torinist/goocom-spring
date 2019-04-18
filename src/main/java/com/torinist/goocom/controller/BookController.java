package com.torinist.goocom.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.torinist.goocom.controller.common.BaseController;
import com.torinist.goocom.resource.BookResource;
import com.torinist.goocom.service.BookService;
import com.torinist.goocom.service.CsvService;
import com.torinist.goocom.util.exception.LogicException;

@Controller
@RequestMapping("/book")
public class BookController implements BaseController<BookResource, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@Autowired
	private CsvService csvService;

	@Override
	@GetMapping("/listall")
	public List<BookResource> getListAll() {
		logger.debug("BookController#listAllを実行しました。");
		return bookService.findListAll();
	}

	@Override
	@GetMapping("/{id}")
	public BookResource getOne(@PathVariable("id") Integer id) {
		BookResource resource = new BookResource();
		resource.setId(id);
		return bookService.findOne(resource);
	}

	@Override
	@PostMapping("/search")
	public List<BookResource> search(BookResource resource) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	@PostMapping("/add")
	public BookResource addOne(BookResource resource) {
		return bookService.insertOne(resource);
	}

	@Override
	@PostMapping("/delete/{id}")
	public int deleteOne(@PathVariable("id") Integer id) {
		BookResource resource = new BookResource();
		resource.setId(id);
		return bookService.deleteOne(resource);
	}

	@RequestMapping("/addcsv")
	public ResponseEntity<String> postCsv(@RequestParam("event_year") String eventYear,
			@RequestParam("upload_file") MultipartFile multipartFile) {
		logger.debug("BookController#postCsvを実行しました。");
		logger.debug("eventYear: " + eventYear + ", upload_file: " + multipartFile);

		if (multipartFile == null || multipartFile.getSize() == 0) {
			return new ResponseEntity<>("ファイルが指定されていません。", HttpStatus.BAD_REQUEST);
		}

		// CSVファイルであることを確認する
		Pattern pattern = Pattern.compile("^.*(.csv|.CSV)$");
		boolean result = pattern.matcher(multipartFile.getOriginalFilename()).matches();
		if (!result) {
			return new ResponseEntity<>("CSVファイルのみ登録できます。", HttpStatus.BAD_REQUEST);
		}

		try {
			// CSVファイルの中身を登録する
			csvService.uploadCsvBooks(eventYear, multipartFile);
		} catch (LogicException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}

		// エラーが返らなかった場合はOKが返る
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}

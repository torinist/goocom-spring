package com.torinist.goocom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.torinist.goocom.entity.BookEntity;
import com.torinist.goocom.repository.BookRepository;
import com.torinist.goocom.resource.BookResource;
import com.torinist.goocom.service.common.BaseServiceInterface;
import com.torinist.goocom.util.BeanUtils;

/**
 * 本に関するロジックを実装するクラス
 * 
 * @author oki
 *
 */
@Service
public class BookService implements BaseServiceInterface<BookResource> {

	@Autowired
	BookRepository bookRepository;

	/**
	 * すべての本を取得する.<br>
	 * 本がなかった場合は空のリストを返す.
	 */
	@Override
	public List<BookResource> findListAll() {
		List<BookEntity> entities = bookRepository.findAll();
		List<BookResource> resources = BeanUtils.newInstanceCopyList(entities, BookResource.class);
		return resources;
	}

	/**
	 * resourceのIDに紐づく本を取得する.
	 */
	@Override
	public BookResource findOne(BookResource resource) {
		BookEntity bookEntity = bookRepository.findById(resource.getCircleId()).get();
		return BeanUtils.newInstanceCopyProperties(bookEntity, BookResource.class);
	}

	/**
	 * resourceを登録する.
	 */
	@Override
	public BookResource insertOne(BookResource resource) {
		BookEntity entity = BeanUtils.newInstanceCopyProperties(resource, BookEntity.class);
		entity = bookRepository.save(entity);
		return BeanUtils.newInstanceCopyProperties(entity, BookResource.class);
	}


	@Override
	public List<BookResource> search(BookResource resource) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int deleteOne(BookResource resource) {
		bookRepository.deleteById(resource.getId());
		return 1;
	}
}

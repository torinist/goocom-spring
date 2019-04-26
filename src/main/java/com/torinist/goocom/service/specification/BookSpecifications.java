package com.torinist.goocom.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.torinist.goocom.entity.BookEntity;
import com.torinist.goocom.util.StringUtils;

/**
 * Criteria用の本仕様
 * 
 * @author oki
 *
 */
public class BookSpecifications {

	/**
	 * 本の名前の検索条件
	 * 
	 * @param name
	 * @return
	 */
	public Specification<BookEntity> nameContains(String name) {
		return StringUtils.isEmpty(name) ? null : (root, query, cb) -> {
			return cb.like(root.get("name"), "%" + name + "%");
		};
	}

	/**
	 * サークルIDの検索条件
	 * 
	 * @param circleId
	 * @return
	 */
	public Specification<BookEntity> circleIdContains(Integer circleId) {
		return StringUtils.isEmpty(circleId) ? null : (root, query, cb) -> {
			return cb.like(root.get("circleId"), circleId.toString());
		};
	}

	/**
	 * ジャンルIDの検索条件
	 * 
	 * @param genreId
	 * @return
	 */
	public Specification<BookEntity> genreIdContains(Integer genreId) {
		return StringUtils.isEmpty(genreId) ? null : (root, query, cb) -> {
			return cb.like(root.get("genreId"), genreId.toString());
		};
	}
}

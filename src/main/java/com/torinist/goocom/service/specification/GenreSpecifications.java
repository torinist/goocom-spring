package com.torinist.goocom.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.torinist.goocom.entity.GenreEntity;
import com.torinist.goocom.util.StringUtils;

/**
 * Criteria用のジャンル仕様
 * 
 * @author oki
 *
 */
public class GenreSpecifications {

	/**
	 * ジャンル名の検索条件
	 * 
	 * @param name
	 * @return
	 */
	public Specification<GenreEntity> nameContains(String name) {
		return StringUtils.isEmpty(name) ? null : (root, query, cb) -> {
			return cb.like(root.get("name"), "%" + name + "%");
		};
	}

}

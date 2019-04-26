package com.torinist.goocom.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.torinist.goocom.entity.CircleEntity;
import com.torinist.goocom.util.StringUtils;

/**
 * Criteria用のサークル仕様
 * 
 * @author oki
 *
 */
public class CircleSpecifications {

	/**
	 * サークル名の検索条件
	 * 
	 * @param name
	 * @return
	 */
	public Specification<CircleEntity> nameContains(String name) {
		return StringUtils.isEmpty(name) ? null : (root, query, cb) -> {
			return cb.like(root.get("name"), "%" + name + "%");
		};
	}

	/**
	 * サークル主の検索条件
	 * 
	 * @param writer
	 * @return
	 */
	public Specification<CircleEntity> writerContains(String writer) {
		return StringUtils.isEmpty(writer) ? null : (root, query, cb) -> {
			return cb.like(root.get("writer"), "%" + writer + "%");
		};
	}
}

package com.torinist.goocom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.torinist.goocom.entity.CircleEntity;

/**
 * サークルテーブルにアクセスするためのリポジトリインタフェース
 * 
 * @author
 *
 */
public interface CircleRepository extends JpaRepository<CircleEntity, Integer>, JpaSpecificationExecutor<CircleEntity> {

	/**
	 * 指定された名前の部分一致を行う.<br>
	 * Idでソートする.
	 * 
	 * @param name サークル名の一部分
	 * @return
	 */
	public List<CircleEntity> findByNameContainingOrderByIdAsc(String name);

	/**
	 * 指定された名前に一致したレコードを取得する.<br>
	 * Idでソートする.
	 * 
	 * @param name
	 * @return
	 */
	public List<CircleEntity> findByNameLikeOrderByIdAsc(String name);
}

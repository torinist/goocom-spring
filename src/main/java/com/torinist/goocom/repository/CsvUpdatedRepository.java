package com.torinist.goocom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.torinist.goocom.entity.CsvUpdatedEntity;

/**
 * CSVテーブルにアクセスするためのリポジトリインタフェース
 * 
 * @author oki
 *
 */
public interface CsvUpdatedRepository extends JpaRepository<CsvUpdatedEntity, Integer> {

	/**
	 * 指定されて名前に一致したレコードを取得する.<br>
	 * Idでソートする.
	 * 
	 * @param updatedTabName
	 * @return
	 */
	public List<CsvUpdatedEntity> findByUpdatedTabNameLikeOrderByIdAsc(String updatedTabName);
}

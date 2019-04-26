package com.torinist.goocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.torinist.goocom.entity.GenreEntity;

/**
 * ジャンルDBにアクセスするためのリポジトリクラス
 * 
 * @author
 *
 */
public interface GenreRepository extends JpaRepository<GenreEntity, Integer>, JpaSpecificationExecutor<GenreEntity> {

}

package com.torinist.goocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.torinist.goocom.entity.BookEntity;

/**
 * 本テーブルにアクセスするためのリポジトリインタフェース
 * 
 * @author oki
 *
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {

	/**
	 * サークルIDに紐づく本を取得する.<br>
	 * ソート順は発行年の降順.<br>
	 * 
	 * @param circleId
	 * @return
	 */
	public BookEntity findByCircleIdOrderByIssueYearDesc(int circleId);

	/**
	 * ジャンルIDに紐づく本を取得する.<br>
	 * ソート順はサークル順昇順.
	 * 
	 * @param circleId
	 * @return
	 */
	public BookEntity findByGenreIdOrderByCircleId(int genreId);
}

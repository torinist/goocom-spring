package com.torinist.goocom.resource;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResource {

	/** 本ID */
	private Integer id;

	/** 本タイトル */
	private String name;

	/** 本画像 */
	private String fileData;

	/** サークルID */
	private Integer circleId;

	/** サークル名 */
	private String circleName;

	/** 発行年 */
	private Integer issueYear;

	/** ジャンルID */
	private Integer genreId;

	/** ジャンル名 */
	private String genreName;

	/** 更新日 */
	private Date updatedTime;

	/** 作成日 */
	private Date createdTime;
}

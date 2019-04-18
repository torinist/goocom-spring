package com.torinist.goocom.resource;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreResource {

	/** ジャンルID */
	private int id;

	/** ジャンル名 */
	private String name;

	/** 更新日 */
	private Date updatedTime;

	/** 作成日 */
	private Date createdTime;
}

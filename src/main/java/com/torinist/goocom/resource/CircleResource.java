package com.torinist.goocom.resource;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleResource {

	/** サークルID */
	private int id;

	/** サークル名 */
	private String name;

	/** サークル主 */
	private String writer;

	/** 更新日 */
	private Date updatedTime;

	/** 作成日 */
	private Date createdTime;
}

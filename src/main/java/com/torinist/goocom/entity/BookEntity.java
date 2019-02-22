package com.torinist.goocom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(name = "file_url")
	private String fileUrl;

	@Column(name = "circle_id")
	private int circleId;

	@Column(name = "issue_year")
	private int issueYear;

	@Column(name = "genre_id")
	private int genreId;

	@Column(name = "updated_time")
	private Date updatedTime;

	@Column(name = "created_time")
	private Date createdTime;

	@Version
	private int version;
}

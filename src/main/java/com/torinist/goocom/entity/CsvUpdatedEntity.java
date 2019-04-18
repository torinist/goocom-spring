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
@Table(name = "csv_updated")
public class CsvUpdatedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "updated_tab_name")
	private String updatedTabName;

	@Column(name = "updated_flg")
	private boolean updatedFlg;

	@Column(name = "updated_time")
	private Date updatedTime;

	@Column(name = "created_time")
	private Date createdTime;

	@Version
	private int version;
}

package com.torinist.goocom.dto;

import com.torinist.goocom.entity.BookEntity;
import com.torinist.goocom.entity.CircleEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author oki
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCsvUpdatedDto {

	private BookEntity book;

	private CircleEntity circle;

}

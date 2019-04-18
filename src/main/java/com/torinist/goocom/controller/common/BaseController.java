package com.torinist.goocom.controller.common;

import java.util.List;

public interface BaseController<T, K> {

	/**
	 * Tをリストで取得する.
	 * 
	 * @return
	 */
	public List<T> getListAll();

	/**
	 * 引数のIDに紐づくTを取得する.
	 * 
	 * @param resource
	 * @return
	 */
	public T getOne(K id);

	/**
	 * Tをもとに検索を行い、リストで取得する
	 * 
	 * @param resource
	 * @return
	 */
	public List<T> search(T resource);

	/**
	 * Tを登録する.
	 * 
	 * @param resource
	 * @return
	 */
	public T addOne(T resource);

	/**
	 * 引数のIDに紐づくTを削除する.
	 * 
	 * @param resource
	 * @return
	 */
	public int deleteOne(K id);
}

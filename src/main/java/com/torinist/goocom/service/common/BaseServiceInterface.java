package com.torinist.goocom.service.common;

import java.util.List;

public interface BaseServiceInterface<T> {

	/**
	 * Tのリストを取得する.
	 * 
	 * @return
	 */
	public List<T> findListAll();

	/**
	 * TをresourceのIDで取得する.
	 * 
	 * @param resource
	 * @return
	 */
	public T findOne(T resource);

	/**
	 * resourceを登録する.
	 * 
	 * @param resource
	 * @return
	 */
	public T insertOne(T resource);

	/**
	 * resourceで検索し、Tを取得する.
	 * 
	 * @param resource
	 * @return
	 */
	public List<T> search(T resource);

	/**
	 * resourceで検索し、Tを削除する.
	 * 
	 * @param resource
	 * @return 削除した件数を返す
	 */
	public int deleteOne(T resource);
}

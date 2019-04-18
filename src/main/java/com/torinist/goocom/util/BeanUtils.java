package com.torinist.goocom.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanInstantiationException;

public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

	/**
	 * 指定されたクラスのインスタンスを生成し、sourceの内容をコピーする.
	 * 
	 * @param source コピー元
	 * @param clazz 生成するクラス
	 * @return sourceをコピーしたclazz型のインスタンス
	 */
	public static <T> T newInstanceCopyProperties(Object source, Class<T> clazz) {
		if (source == null) {
			return null;
		}
		try {
			Constructor<T> constructor = clazz.getConstructor();
			T result = constructor.newInstance();
			BeanUtils.copyProperties(source, result);
			return result;
		} catch (NoSuchMethodException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (SecurityException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (InstantiationException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (IllegalAccessException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (IllegalArgumentException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (InvocationTargetException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		}
	}

	/**
	 * sourceの一覧を、指定されたクラスのListにして返す.
	 * 
	 * @param sources コピー元の一覧
	 * @param clazz 生成するクラス
	 * @return sourcesをコピーしたclazz型のList
	 */
	public static <T> List<T> newInstanceCopyList(List<?> sources, Class<T> clazz) {
		if (sources == null) {
			return new ArrayList<T>();
		}
		try {
			List<T> results = new ArrayList<T>();
			for (Object source : sources) {
				Constructor<T> constructor = clazz.getConstructor();
				T targetInstance = constructor.newInstance();
				BeanUtils.copyProperties(source, targetInstance);
				results.add(targetInstance);
			}
			return results;
		} catch (NoSuchMethodException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (SecurityException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (InstantiationException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (IllegalAccessException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (IllegalArgumentException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		} catch (InvocationTargetException e) {
			throw new BeanInstantiationException(clazz, "インスタンスを生成することができませんでした。", e);
		}
	}
}

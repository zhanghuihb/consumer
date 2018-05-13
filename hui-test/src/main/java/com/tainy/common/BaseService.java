package com.tainy.common;

public interface BaseService<T> {
	public void add(T t) throws Exception;

	/**
	 * @throws Exception
	 * @inheritDoc
	 */
	public void edit(T t) throws Exception;

	/**
	 * @throws Exception
	 * @inheritDoc
	 */
	public void remove(Integer id) throws Exception;

	/**
	 * @throws Exception
	 * @inheritDoc
	 */
	public T findById(Integer id) throws Exception;
}

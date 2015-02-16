package com.dao;

public interface IDao<O> {

	void save(O o);

	Object search(O o);

	void delete(O o);

	void update(O o);

}

package com.dao;

import java.util.List;

public interface IDao<O extends String> {

	boolean save(O o);

	List<List<String>> search(O o);

	boolean delete(O o);

	boolean update(O o);

}

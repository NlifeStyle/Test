package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtils;

public class DaoImp implements IDao<String> {

	private Connection conn;
	Statement stat;
	ResultSet rs;
	StringBuilder sb;

	public DaoImp() {

	}

	@Override
	public void save(String o) {
		// TODO Auto-generated method stub
		try {
			executeUpdate(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object search(String o) {
		// TODO Auto-generated method stub
		List<List<String>> result = new ArrayList<List<String>>();
		try {
			conn = DBUtils.getConnection();
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(o);
			while (rs.next()) {
				List<String> rowList = new ArrayList<String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					rowList.add(rs.getString(i));
				}
				result.add(rowList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		print(result);
		return result;
	}

	@Override
	public void delete(String o) {
		// TODO Auto-generated method stub
		try {
			executeUpdate(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(String o) {
		// TODO Auto-generated method stub
		try {
			executeUpdate(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		IDao<String> dao = new DaoImp();
		dao.save("insert into jasonlocaltest (id,name) values('2','goodluck')");
		dao.update("update jasonlocaltest set name='updateTest' where id='1'");
		dao.search("select * from jasonlocaltest");
		dao.delete("delete from jasonlocaltest where id='2'");
	}

	private void print(Object o) {
		if (o instanceof List) {
			System.out.println();
			List<?> list = (List<?>) o;
			for (int i = 0; i < list.size(); i++) {
				print(list.get(i));
			}
		} else {
			System.out.print(o.toString() + "\t");
		}
	}

	public int executeUpdate(String o) throws SQLException {
		conn = DBUtils.getConnection();
		Statement stat = null;
		int result = -1;
		try {
			stat = conn.createStatement();
			result = stat.executeUpdate(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}

//
// public List<List<String>> getListResult(String sql) {
// List<List<String>> result = new ArrayList<List<String>>();
// ResultSet rs = executeQuery(sql);
// if (rs != null) {
// try {
// while (rs.next()) {
// List<String> rowList = new ArrayList<String>();
// ResultSetMetaData rsmd = rs.getMetaData();
// for (int i = 1; i <= rsmd.getColumnCount(); i++) {
// rowList.add(rs.getString(i));
// }
// result.add(rowList);
// }
// } catch (SQLException e) {
// e.printStackTrace();
// } finally {
// try {
// rs.close();
// conn.close();
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// }
// return result;
// }


package com.yhf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yhf.txl.utils.DbUtil;

public class BasicDao {
	private static Connection connection = DbUtil.getConnection();

	/**
	 * @Title: query
	 * @Description: 查询数据
	 * @param: sql
	 * @return: ResultSet
	 */
	public static ResultSet query(String sql) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean update(String sql) {

		try {
			return connection.prepareStatement(sql).executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

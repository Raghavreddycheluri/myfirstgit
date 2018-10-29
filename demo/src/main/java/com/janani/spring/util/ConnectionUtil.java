package com.janani.spring.util;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionUtil {
	public static JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}

	public static DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		 dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		 dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		 dataSource.setUsername("scott");
		 dataSource.setPassword("tiger");
		return dataSource;
	}
}

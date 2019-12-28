package com.janani.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.janani.spring.model.Course;

@Component
public class CourseDAO {

	// private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void create(Course course) {

		String query = "INSERT INTO COURSE(CODE,NAME,DESCRIPTION) VALUES(?,?,?)";
		jdbcTemplate.update(query, course.getCode(), course.getName(), course.getDescription());

	}

	public void update(Course course) {

		String query = "UPDATE COURSE SET DESCRIPTION=? WHERE CODE=?";
		jdbcTemplate.update(query, course.getDescription(), course.getCode());

	}

	public void delete(String code) {

		String query = "DELETE FROM COURSE WHERE CODE=?";
		jdbcTemplate.update(query, code);

	}

	public Course findByCode(String code) {

		String query = "SELECT CODE,NAME,DESCRIPTION FROM COURSE WHERE CODE=?";
		Course course = jdbcTemplate.queryForObject(query, new Object[] { code }, (rs, row) -> {
			Course c = convert(rs);
			return c;
		});
		return course;

	}

	public List<Course> findAll() {

		String query = "SELECT CODE,NAME,DESCRIPTION FROM COURSE";
		List<Course> courses = jdbcTemplate.query(query, (rs, rows) -> {
			Course course = convert(rs);
			return course;
		});
		return courses;
	}

	private Course convert(ResultSet rs) throws SQLException {

		Course course = new Course();
		course.setCode(rs.getString("CODE"));
		course.setName(rs.getString("NAME"));
		course.setDescription(rs.getString("DESCRIPTION"));
		return course;

	}
}

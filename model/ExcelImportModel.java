package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbutil.Dbconn;
import entity.Student;

public class ExcelImportModel {
	private static PreparedStatement ps;
	private static ResultSet rs;
	static Dbconn s = new Dbconn();

	public List<Student> parseExcel(InputStream inputStream) {
		List<Student> studentList = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
			String line;
			boolean isFirstLine = true;
			
			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(",");
				if (parts.length >= 6) {
					Student student = new Student();
					student.setId(Integer.parseInt(parts[0].trim()));
					student.setName(parts[1].trim());
					student.setSex(parts[2].trim());
					student.setAge(Integer.parseInt(parts[3].trim()));
					student.setGrade(parts[4].trim());
					student.setScore(Float.parseFloat(parts[5].trim()));
					studentList.add(student);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public int importStudents(List<Student> studentList) {
		int count = 0;
		try {
			Connection conn = s.getConnection();
			String sql = "INSERT INTO student(id, name, sex, age, grade, score) VALUES (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);

			for (Student student : studentList) {
				ps.setInt(1, student.getId());
				ps.setString(2, student.getName());
				ps.setString(3, student.getSex());
				ps.setInt(4, student.getAge());
				ps.setString(5, student.getGrade());
				ps.setFloat(6, student.getScore());
				count += ps.executeUpdate();
			}
			s.closeAll(conn, ps, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
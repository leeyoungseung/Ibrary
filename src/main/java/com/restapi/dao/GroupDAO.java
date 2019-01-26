package com.restapi.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.restapi.model.Group;

public class GroupDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	static DataSource ds;

	public GroupDAO() {
		con = null;
		try {
			String id = "root";
			String pw = readDBInfo();
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3309/ibrary", id, pw);
			
			System.out.println("MySQL Connected。");
		} catch (SQLException e) {
			System.out.println("Fail MySQL Connected");
		} catch (Exception e) {
			System.out.println("Fail MySQL Connected < System > :" + e.getClass().getName());
		}
	}
	
	private String readDBInfo() {
		String str = "";
		try{
			File file = new File("C:\\Users\\user\\Documents\\myWorkSpaceJsp\\Ibrary\\dbinfo.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			str = br.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public List<Group> getGroups(int company_id) throws SQLException{
		String sql = "SELECT * FROM groups WHERE company_id=?";
		List<Group> list = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, company_id);
			rs = ps.executeQuery();
			return list = makeList(rs);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	private List<Group> makeList(ResultSet rs) throws SQLException {
		List<Group> list = new ArrayList<Group>();
		while (rs.next()) {
			Group dto = new Group();
			dto.setGroup_id(rs.getInt("group_id"));
			dto.setCompany_id(rs.getInt("company_id"));
			dto.setGroup_name(rs.getString("group_name"));
			dto.setGroup_introduction(rs.getString("group_introduction"));
			dto.setGroup_fanclub(rs.getString("group_fanclub"));
			dto.setGroup_start_day(rs.getDate("group_start_day"));
			list.add(dto);
		}
		return list;
	}

	public Group getGroup(int id) throws SQLException {
		String sql = "SELECT * FROM groups WHERE group_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			return makeList(rs).get(0);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	public Group createGroup(Group dto) throws SQLException {
		String sql = "INSERT INTO groups (company_id, group_name, group_introduction, group_fanclub, group_start_day) "
				+ "VALUES (?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getCompany_id());
			ps.setString(2, dto.getGroup_name());
			ps.setString(3, dto.getGroup_introduction());
			ps.setString(4, dto.getGroup_fanclub());
			java.sql.Date sqlDate = new java.sql.Date(dto.getGroup_start_day().getTime());
			ps.setDate(5, sqlDate);
			int res = ps.executeUpdate();
			if (res < 0) {
				System.out.println("Insert Query Fail!!");
				return null;
			}
			sql = "SELECT * FROM groups WHERE group_id=(SELECT MAX(group_id) FROM groups)";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return makeList(rs).get(0);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	public Group updateGroup(Group dto) throws SQLException {
		String sql = "UPDATE groups "
				+ "SET group_name=?,group_introduction=?,group_fanclub=? "
				+ "WHERE group_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getGroup_name());
			ps.setString(2, dto.getGroup_introduction());
			ps.setString(3, dto.getGroup_fanclub());
			ps.setInt(4, dto.getGroup_id());

			int res = ps.executeUpdate();
			if (res < 0) {
				System.out.println("UPDATE Query Fail!!");
				return null;
			}
			sql = "SELECT * FROM groups WHERE group_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getGroup_id());
			rs = ps.executeQuery();
			return makeList(rs).get(0);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	public List<Group> deleteGroup(int id) throws SQLException {
		String sql = "DELETE FROM groups WHERE group_id=?";
		List<Group> list = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if (res < 0) {
				System.out.println("DELETE Query Fail!!");
				return null;
			}
			sql = "SELECT * FROM groups";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list = makeList(rs);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}
}

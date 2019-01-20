package com.restapi.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.restapi.model.Company;

public class CompanyDAO {
	

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	static DataSource ds;

	public CompanyDAO() {
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

	public List<Company> getCompanies() throws SQLException{
		String sql = "SELECT * FROM companies";
		List<Company> list = null;
		try {
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

	private List<Company> makeList(ResultSet rs) throws SQLException {
		List<Company> list = new ArrayList<Company>();
		while (rs.next()) {
			Company dto = new Company();
			dto.setCompany_id(rs.getInt("company_id"));
			dto.setCompany_name(rs.getString("company_name"));
			dto.setCompany_representative(rs.getString("company_representative"));
			dto.setCompany_contract_info(rs.getString("company_contract_info"));
			dto.setCompany_address(rs.getString("company_address"));
			list.add(dto);
		}
		return list;
	}

	public Company getCompany(int id) throws SQLException {
		String sql = "SELECT * FROM companies WHERE company_id=?";
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

	public Company createCompany(Company dto) throws SQLException {
		String sql = "INSERT INTO companies (company_name, company_representative, company_contract_info, company_address) "
				+ "VALUES (?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCompany_name());
			ps.setString(2, dto.getCompany_representative());
			ps.setString(3, dto.getCompany_contract_info());
			ps.setString(4, dto.getCompany_address());
			int res = ps.executeUpdate();
			if (res < 0) {
				System.out.println("Insert Query Fail!!");
				return null;
			}
			sql = "SELECT * FROM companies WHERE company_id=(SELECT MAX(company_id) FROM companies)";
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

	public Company updateCompany(Company dto) throws SQLException {
		String sql = "UPDATE companies "
				+ "SET company_name=?,company_representative=?,company_contract_info=?,company_address=? "
				+ "WHERE company_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCompany_name());
			ps.setString(2, dto.getCompany_representative());
			ps.setString(3, dto.getCompany_contract_info());
			ps.setString(4, dto.getCompany_address());
			ps.setInt(5, dto.getCompany_id());
			int res = ps.executeUpdate();
			if (res < 0) {
				System.out.println("UPDATE Query Fail!!");
				return null;
			}
			sql = "SELECT * FROM companies WHERE company_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getCompany_id());
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

	public List<Company> deleteCompany(int id) throws SQLException {
		String sql = "DELETE FROM companies WHERE company_id=?";
		List<Company> list = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if (res < 0) {
				System.out.println("DELETE Query Fail!!");
				return null;
			}
			sql = "SELECT * FROM companies";
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

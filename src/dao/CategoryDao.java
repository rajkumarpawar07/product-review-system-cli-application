package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Customer;
import service.CategoryService;
import utility.DBConnection;

public class CategoryDao implements CategoryService{
	private  PreparedStatement ps;
	private String sql;
	@Override
	public int addCategory(Category c) throws Exception {
		int res=0;
		try {
		 Connection con = DBConnection.getConnection();
		  sql="insert into category(category_name) values (?)";
		ps = con.prepareStatement(sql);
		// ps.setInt(0, 0);
		ps.setString(1, c.getCategory_name());
		 res = ps.executeUpdate();
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateCategory(Category c) throws Exception {
		int res=0;
		 sql="UPDATE category SET category_name = ? WHERE (category_id = ?)";
		try {
		 Connection con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(2, c.getCategory_id());
			ps.setString(1, c.getCategory_name());
			 res = ps.executeUpdate();
			DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
			return res;
	}

	@Override
	public int deleteCategory(int id) throws Exception {
		int res=0;
		sql="DELETE FROM category WHERE (category_id = ?)";
		try {
		 Connection con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			//ps.setString(1, c.getCategory_name());
			res = ps.executeUpdate();
			DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
			return res;
	}

	@Override
	public Category getCategoryById(int id) throws Exception {
		Category category = new Category();
		sql="SELECT * from category WHERE (category_id = ?)";
		try {
		 Connection con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			//ps.setString(1, c.getCategory_name());
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				category.setCategory_id(rs.getInt(1));
				category.setCategory_name(rs.getString(2));
			}
			DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> getAllCategories() throws Exception {
		List<Category>  list= new ArrayList<Category>();
		sql="Select * from category";
		try {
		 Connection con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setInt(1, id);
			//ps.setString(1, c.getCategory_name());
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  Category category= new Category();
				category.setCategory_id(rs.getInt(1));
				category.setCategory_name(rs.getString(2));
				list.add(category);
			}
			DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}
	public Category isCategoryExistOrNot(Category c) throws Exception {
		//String c_name=c.getCategory_name();
		
		sql="select * from category where category_name=?";
		try {
		 Connection con = DBConnection.getConnection();
		 
			ps = con.prepareStatement(sql);
			//ps.setInt(1, id);
			ps.setString(1, c.getCategory_name());
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				c.setCategory_id(rs.getInt(1));
				c.setCategory_name(rs.getString(2));
				DBConnection.closeConnection(con);
				return c;
			}else {
				addCategory(c);
				return c;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return c;
			
			
		
	}

}

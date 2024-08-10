package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import service.ProductService;
import utility.DBConnection;

public class ProductDao implements ProductService {
	private PreparedStatement ps;
	private String sql;
	private Connection con = null;

	@Override
	public int addProoduct(Product p) throws Exception {
		int update = 0;
		sql = "INSERT INTO `e_com`.`product` (`p_name`, `price`, `  description`, `category_id`) VALUES (?,?,?, ?)";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setDouble(2, p.getPrice());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getCategoryId());
			update = ps.executeUpdate();
			DBConnection.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public int updateProduct(Product p) throws Exception {
		int res = 0;
		sql="UPDATE `e_com`.`product` SET `p_name` = ?, `price` = ?, `  description` = ? WHERE (`p_id` = ?)";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setDouble(2, p.getPrice());
			ps.setString(3, p.getDescription());
			//ps.setInt(4, p.getCategoryId());
			ps.setInt(4, p.getProductId());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(con);
		return res;
	}

	@Override
	public int deleteProduct(int id) throws Exception {
		int res = 0;
		sql = "DELETE FROM `e_com`.`product` WHERE (`p_id` = ?)";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Product getProductById(int id) throws Exception {
		Product product = null;
		sql = "select * from product where p_id =?";
		try{
			con = DBConnection.getConnection();
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		product= new Product();
		if (rs.next()) {
			product.setCategoryId(rs.getInt(5));
			product.setProductId(rs.getInt(1));
			product.setProductName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			product.setDescription(rs.getString(4));
		} else {
			System.out.println("Not Found record");
			throw new NullPointerException("Not Found Record !");
		}
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() throws Exception {
		List<Product> list = new ArrayList<Product>();
		try {
		sql = "select * from product";
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		// ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Product product = new Product();
			product.setCategoryId(rs.getInt(5));
			product.setProductId(rs.getInt(1));
			product.setProductName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			product.setDescription(rs.getString(4));
			list.add(product);
		}
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product searchProductByName(String name) throws Exception {
		Product product = null;
		try {
		sql = "select * from product where p_name =?";
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		System.out.println(name);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		  product=new Product();
		if (rs.next()) {
			product.setCategoryId(rs.getInt(5));
			product.setProductId(rs.getInt(1));
			product.setProductName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			product.setDescription(rs.getString(4));
		} else {
			System.out.println("Not Found record");
			throw new NullPointerException("Not Found Record !");
		}
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;

	}

	

}

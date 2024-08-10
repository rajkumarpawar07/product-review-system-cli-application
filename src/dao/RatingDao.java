package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Product;
import service.RatingService;
import utility.DBConnection;

public class RatingDao  implements RatingService{
	@Override
	public double calculateAverageRating(int productId) throws Exception {
	    String sql = "SELECT AVG(rating_value) AS average_rating FROM Rating WHERE p_id = ?";
	    double averageRating = 0.0;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, productId);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            averageRating = rs.getDouble("average_rating");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            DBConnection.closeConnection(con);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return averageRating;
	}
	@Override
	public Map<Integer, Integer> analyzeRatingsDistribution(int productId) throws Exception {
	    String sql = "SELECT rating_value, COUNT(*) AS count FROM Rating WHERE p_id = ? GROUP BY rating_value";
	    Map<Integer, Integer> ratingDistribution = new HashMap<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, productId);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            int ratingValue = rs.getInt("rating_value");
	            int count = rs.getInt("count");
	            ratingDistribution.put(ratingValue, count);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            DBConnection.closeConnection(con);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return ratingDistribution;
	}
	@Override
	public List<Product> getTopRatedProducts(int limit) throws Exception {
	    String sql = "SELECT p.p_id, p.p_name, p.price,  AVG(r.rating_value) AS average_rating " +
	                 "FROM Product p " +
	                 "LEFT JOIN Rating r ON p.p_id = r.p_id " +
	                 "GROUP BY p.p_id " +
	                 "ORDER BY average_rating DESC " +
	                 "LIMIT ?";
	    List<Product> topRatedProducts = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, limit);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            int productId = rs.getInt(1);
	            String productName = rs.getString(2);
	            double price = rs.getDouble(3);
	          //  String description = rs.getString("description");
	            double averageRating = rs.getDouble(4);
	            
	            Product product = new Product(productId, productName, price,"");
	            product.setAverageRating(averageRating);
	            topRatedProducts.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            DBConnection.closeConnection(con);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return topRatedProducts;
	}
	
}

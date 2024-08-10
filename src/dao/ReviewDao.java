package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Review;
import service.ReviewService;
import utility.DBConnection;

public class ReviewDao implements ReviewService {
	private PreparedStatement ps;
	private Connection con;
	private String sql;
	private int res;
	private ResultSet rs;
	private List<Review> list;

	@Override
	public int addReview(Review r) throws Exception {
		sql = "INSERT INTO review ( p_id, customer_id, review_text, rating) VALUES (?,?,?,?)";
		try {
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, r.getProductId());
		ps.setInt(2, r.getCustomer_id());
		ps.setString(3, r.getReview_text());
		ps.setInt(4, r.getRating());
		res = ps.executeUpdate();
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateReview(Review r) throws Exception {
		sql = "UPDATE e_com.review SET  review_text = ?, rating = ? WHERE (p_id = ? and customer_id=?)";
		try {
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);

		ps.setString(1, r.getReview_text());
		ps.setInt(2, r.getRating());
		ps.setInt(3, r.getProductId());
		ps.setInt(4, r.getCustomer_id());
		res = ps.executeUpdate();
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
	

	@Override
	public int removeReview(int cid,int rid) throws Exception {
		sql = "DELETE FROM review  WHERE (customer_id = ? and r_id=? )";
		try {
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);

		
		ps.setInt(1,cid);
		ps.setInt(2,rid);
		res = ps.executeUpdate();
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Review> getReviews(int pid) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getReviews() throws Exception {
		List<Review> list=  new ArrayList<Review>();
		sql="Select * from review";
		try {
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int rid=rs.getInt(1);
			int pid=rs.getInt(2);
			int cid=rs.getInt(3);
			String reviewText=rs.getString(4);
			int rat=rs.getInt(5);
			Review r= new Review(rid, pid, cid, reviewText, rat);
			list.add(r);
		}
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import service.CustomerService;
import utility.DBConnection;

public class CustomerDao  implements CustomerService{
	private PreparedStatement ps;
	private Connection con;
	String sql;
	int res;
	@Override
	public int addCustomer(Customer c) throws Exception {
		sql="INSERT INTO customer ( customer_name, customer_email, customer_mNo) VALUES (?,?,?)";
		try {
		con = DBConnection.getConnection();
		ps=con.prepareStatement(sql);
		ps.setString(1, c.getCustomer_name());
		ps.setString(2, c.getCustomer_email());
		ps.setLong(3, c.getCustomer_mNo());
		res = ps.executeUpdate();
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	@Override
	public int updateCustomer(Customer c) throws Exception {
		sql="UPDATE customer  SET  customer_name  = ?,  customer_email  = ?,  customer_mNo  = ?  WHERE ( customer_id  = ?)";
		try {
		con = DBConnection.getConnection();
		ps=con.prepareStatement(sql);
		ps.setString(1, c.getCustomer_name());
		ps.setString(2, c.getCustomer_email());
		ps.setLong(3, c.getCustomer_mNo());
		ps.setInt(4, c.getCustomer_id());
		res = ps.executeUpdate();
		DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}
	@Override
	public int deleteCustomer(int id) throws Exception {
	  sql="DELETE FROM customer WHERE (customer_id=?)";
	  try {
	  con = DBConnection.getConnection();
		ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		res = ps.executeUpdate();
		DBConnection.closeConnection(con);
	  }catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}
	@Override
	public Customer getCustomerById(int id) throws Exception {
		sql ="select * from customer where customer_id=?";
		Customer cust = new Customer();
		try {
		 con = DBConnection.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cust.setCustomer_id(rs.getInt(1));
				cust.setCustomer_name(rs.getString(2));
				cust.setCustomer_email(rs.getString(3));
				cust.setCustomer_mNo(rs.getLong(4));
			}
			rs.close();
			DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
			return cust;
	}
	@Override
	public List<Customer> getAllCustomers() throws Exception {
		List<Customer> list= new ArrayList<Customer>();
		sql ="select * from customer";
		try {
		 con = DBConnection.getConnection();
			ps=con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				Customer cust = new Customer();
				cust.setCustomer_id(rs.getInt(1));
				cust.setCustomer_name(rs.getString(2));
				cust.setCustomer_email(rs.getString(3));
				cust.setCustomer_mNo(rs.getLong(4));
				list.add(cust);
			}
			rs.close();
			DBConnection.closeConnection(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	


}

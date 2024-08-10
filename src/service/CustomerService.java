package service;

import java.util.List;

import model.Customer;

public interface CustomerService {
	public int addCustomer(Customer c) throws Exception;
	public int updateCustomer(Customer c) throws Exception;
	public int deleteCustomer(int id) throws Exception;
	public Customer getCustomerById(int id) throws Exception;
	public List<Customer> getAllCustomers()throws Exception;

}

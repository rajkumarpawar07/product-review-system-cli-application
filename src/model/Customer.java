package model;

public class Customer {
	private int customer_id;
	private String customer_name;
	private String customer_email;
	private Long customer_mNo;

	public Customer() {

	}

	public Customer(int customer_id, String customer_name, String customer_email, Long customer_mNo) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_mNo = customer_mNo;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public Long getCustomer_mNo() {
		return customer_mNo;
	}

	public void setCustomer_mNo(Long customer_mNo) {
		this.customer_mNo = customer_mNo;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_email="
				+ customer_email + ", customer_mNo=" + customer_mNo + "]";
	}

}

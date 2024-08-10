package model;

public class Product {
	private int productId;
	private String productName;
	private double price;
	private String description;
	private int categoryId;
	private double averageRating;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String productName, double price, String description, int categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
	}

	public Product(int productId, String productName, double price, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public double getAverageRating() {
		return averageRating;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", description=" + description + ", categoryId=" + categoryId + "]";
	}

}

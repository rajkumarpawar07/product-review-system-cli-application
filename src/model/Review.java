package model;

public class Review {
	private int review_id;
	private int productId;
	private int customer_id;
	private String review_text;
	private int rating;

	public Review(int review_id, int productId, int customer_id, String review_text, int rating) {
		super();
		this.review_id = review_id;
		this.productId = productId;
		this.customer_id = customer_id;
		this.review_text = review_text;
		this.rating = rating;
	}

	public Review() {
		// TODO Auto-generated constructor stub
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", productId=" + productId + ", customer_id=" + customer_id
				+ ", review_text=" + review_text + ", rating=" + rating + "]";
	}

}

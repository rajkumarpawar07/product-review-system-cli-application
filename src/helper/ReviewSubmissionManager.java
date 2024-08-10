package helper;

import java.util.List;
import java.util.Scanner;

import dao.CustomerDao;
import dao.ProductDao;
import dao.ReviewDao;
import model.Customer;
import model.Product;
import model.Review;

public class ReviewSubmissionManager {
	private int id;
	private static String res;
	private static ReviewDao dao = new ReviewDao();
	private static ProductDao pdao = new ProductDao();
	private static CustomerDao cdao = new CustomerDao();

	public static void reviewSubmission() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("Start Opertation Review Submission:");
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("Press 1 For Submit new reviews For products");
		System.out.println("Press 2 For	View review details");
		System.out.println("press 3 For Update review inFormation");
		System.out.println("press 4 For Delete reviews");

		int choise = sc.nextInt();

		switch (choise) {
		case 1:
			addNewReview(sc);
			break;
		case 2:
			viewReviewDetails();
			break;
		case 3:
			UpdateReviewByCustomer(sc);
			break;
		case 4:
			deleteReview(sc);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + choise);
		}

	}
	private static void deleteReview(Scanner sc) throws Exception {
		System.out.println("Enter (Customer )your Id for delete Review ");
		int cid = sc.nextInt();
		System.out.println("Enter review  Id for delete Review ");
		int rid = sc.nextInt();
		
		res=(dao.removeReview(cid,rid)>0)?"Remove Your Review ":"Review Not remove";
		System.out.println(res);
	
	}
	private static void UpdateReviewByCustomer(Scanner sc) throws Exception {
		System.out.println("Enter Product name For Update Review");
		sc.nextLine();
		String pname = sc.nextLine();
		System.out.println("Enter Customer id");
		int cid = sc.nextInt();
		Product product = pdao.searchProductByName(pname);
		if (product.getProductName() != null) {

			Customer cust = cdao.getCustomerById(cid);
			System.out.println(" i am in cust" + cust);
			if (cust.getCustomer_name() == null) {
				System.out.println("Create Your Account");
				cust = new Customer();
				System.out.println("Enter name ");
				cust.setCustomer_name(sc.nextLine());
				System.out.println("Enter Email");
				cust.setCustomer_email(sc.nextLine());
				System.out.println("Enter Mobile No");
				res = (cdao.addCustomer(cust) > 0) ? "Record Is insert " : "Record Not insert";
				System.out.println(res);
			}
			Review r = new Review();
			r.setCustomer_id(cid);
			r.setProductId(product.getProductId());
			System.out.println("Enter your Review Text For Product");
			sc.nextLine();
			String r_text = sc.nextLine();
			System.out.println("Enter Rating 1 to 5");
			int rate = sc.nextInt();

			r.setReview_text(r_text);
			r.setRating(rate);
			int op = dao.updateReview(r);
			System.out.println(op);
			res = (op > 0) ? "Review Updated " : "Review Not Updated";
			System.out.println(res);

		} else {
			System.out.println("Product not Found For Update Review");
		}

	}

	private static void viewReviewDetails() throws Exception {
		List<Review> list = dao.getReviews();
		System.out.println("+++++++++++++++++++++");

		for (Review r : list) {
			Product product = pdao.getProductById(r.getProductId());
			Customer customer = cdao.getCustomerById(r.getCustomer_id());
			System.out.print(
					"Product Name :-" + product.getProductName() + ", Description :-  " + product.getDescription());
			System.out.println(",Product Rating : " + r.getRating() + ", Product Review :- " + r.getReview_text()
					+ "Review By :- " + customer.getCustomer_name());
			System.out.println("________________________________");
		}

	}

	private static Review inputReviewData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Customer id");
		int customer_id = sc.nextInt();
		Review r = new Review();
		r.setCustomer_id(customer_id);
		System.out.println("Enter Rating 1 To 5 ");
		r.setRating(sc.nextInt());
		System.out.println("Enter Review Description");
		sc.nextLine();
		r.setReview_text(sc.nextLine());
		return r;
	}

	private static void addNewReview(Scanner sc) throws Exception {

		System.out.println("Enter product Name For add Review");
		sc.nextLine();
		String name = sc.nextLine();
		Review review = inputReviewData();

		Product product = pdao.searchProductByName(name);
		if (product.getProductId() > 0) {
			Customer cust = cdao.getCustomerById(review.getCustomer_id());
			// if customer exit or not
			// if exit than customer succefully add review
			// otherwise not exits custormer first create customer record and add review
			if (cust.getCustomer_name() == null) {
				System.out.println("Create Your Account");
				cust = new Customer();
				System.out.println("Enter name ");
				cust.setCustomer_name(sc.nextLine());
				System.out.println("Enter Email");
				cust.setCustomer_email(sc.nextLine());
				System.out.println("Enter Mobile No");
				res = (cdao.addCustomer(cust) > 0) ? "Record Is insert " : "Record Not insert";
				System.out.println(res);
			}
			review.setProductId(product.getProductId());
			res = (dao.addReview(review) > 0) ? "Review Added " : "Review Not Added";
			System.out.println(res);

		} else {
			System.out.println("Product Not Found For review");
		}

	}

}

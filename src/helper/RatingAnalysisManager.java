package helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.event.TreeSelectionEvent;

import dao.ProductDao;
import dao.RatingDao;
import model.Product;

public class RatingAnalysisManager {
	private static RatingDao dao = new RatingDao();

	public static void ratingAnalysis() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welecome To Rating Analysis");
		System.out.println("Press 1 Calculate average ratings for products");
		System.out.println("Press 2 Analyze ratings distribution (e.g., number of 5-star, 4-star, etc. ratings)");
		System.out.println("Press 3 View top-rated products");
		int choise = sc.nextInt();
		switch (choise) {
		case 1:
			calculateAverageRatingsProduct();
			break;
		case 2:
			analyzeRatingsDistribution(sc);
			break;
		case 3:
			viewTopProduct(sc);
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + choise);
		}
	}

	// Calculate average ratings for products
	private static void calculateAverageRatingsProduct() throws Exception {
		ProductDao pDao = new ProductDao();
		List<Product> list = pDao.getAllProducts();
		// Map< Double, Product> map= new TreeMap<Double, Product>();
		for (Product product : list) {
			if (product.getProductName() != null) {
				Double calculateAverageRating = dao.calculateAverageRating(product.getProductId());

				System.out.println(" [ " + product.getProductName().toUpperCase() + "  , ==> Average Rating"
						+ calculateAverageRating + " ]");

			} else {
				System.out.println("Product Not Found ...");
			}
		}

	}
  //Analyze ratings distribution (e.g., number of 5-star, 4-star, etc. ratings)
	private static void analyzeRatingsDistribution(Scanner sc) throws Exception {

		System.out.println("Enter Product Name for Analyze ratings distribution ");
		sc.nextLine();
		String name = sc.nextLine();
		
		ProductDao pDao = new ProductDao();
		Product product = pDao.searchProductByName(name);
		System.out.println(product);
		Map<Integer, Integer> map = dao.analyzeRatingsDistribution(product.getProductId());
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
		    int ratingValue = entry.getKey();
		    int count = entry.getValue();
		    System.out.println( ratingValue+" star ratings:  By "+count);
		}

	}
	//View top-rated products
	private static void viewTopProduct(Scanner sc)throws Exception{
		System.out.println("Enter the Rating For Search top Product ");
		int limit = sc.nextInt();
		List<Product> list = dao.getTopRatedProducts(limit);
		for(Product p:list) {
			System.out.println("[ Product name :"+p.getProductName()+", Product Price :"+p.getPrice()+", Average AverageRating :"+p.getAverageRating()+" ]");
		}
		  
		
	}

}

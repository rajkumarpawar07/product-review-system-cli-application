package service;

import java.util.List;
import java.util.Map;

import model.Product;

public interface RatingService {
	 /**
     * Calculate average rating for a specific product.
     */
    double calculateAverageRating(int productId) throws Exception;

    /**
     * Analyze ratings distribution for a specific product.
     */
    Map<Integer, Integer> analyzeRatingsDistribution(int productId) throws Exception;

    /**
     * Retrieve top rated products.
     */
    List<Product> getTopRatedProducts(int limit) throws Exception;
}

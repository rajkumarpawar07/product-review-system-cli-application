package service;

import java.util.List;

import model.Review;

public interface ReviewService {
	
	public int addReview(Review r)throws Exception;
	public int updateReview(Review r) throws Exception;
	public int removeReview(int cid,int rid) throws Exception;
	public List<Review> getReviews(int pid) throws Exception;
	public List<Review> getReviews() throws Exception;
	
	

}

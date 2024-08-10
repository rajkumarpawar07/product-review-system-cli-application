package service;

import java.util.List;

import model.Product;

public interface ProductService {
   public int addProoduct(Product p) throws Exception;
   public int updateProduct(Product p) throws Exception;
   public int deleteProduct(int id) throws Exception;
   public Product getProductById(int id) throws Exception;
   public List<Product> getAllProducts() throws Exception;
   
}

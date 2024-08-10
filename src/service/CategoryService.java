package service;

import java.util.List;

import model.Category;

public interface CategoryService {
   public int addCategory(Category c) throws Exception;
   public int updateCategory(Category c) throws Exception;
   public int deleteCategory(int id)throws Exception;
   public Category getCategoryById(int id)throws Exception;
   public List<Category> getAllCategories() throws Exception;
}

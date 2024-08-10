package helper;

import java.util.List;
import java.util.Scanner;

import dao.CategoryDao;
import model.Category;

public class CategoryOperationManager {
	public static void categoryOperation() throws Exception {
		
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("Category Operation is start ");
		System.out.println("++++++++++++++++++++++++++++++++");
		Category c = null;
		String res;
		// its object create for Access All Database method
		CategoryDao dao = new CategoryDao();
		// control Category database Operation
		Scanner sc = new Scanner(System.in);
		int op=0;
		do {
		System.out.println("Press 1 For Add Category ");
		System.out.println("Press 2 For Update Category");
		System.out.println("Press 3 For Delete Category");
		System.out.println("Press 4 For Get Category By Id");
		System.out.println("Press 5 For Get All Category");
		int chosie = sc.nextInt();
		System.out.println("++++++++++++++++++++++++++++++++");
		// main Category operation
		switch (chosie) {
		case 1:
			System.out.println("Enter Following Information for Add Category");
			c = inputCategory();
			// call dao method to add Category
			res = (dao.addCategory(c) > 0) ? "Category Successfully Add " : "Category Not Add ";
			System.out.println(res);
			break;

		case 2:
			// Update Category
			System.out.println("Enter Following Information for Update Category");
			System.out.println("Enter Id");
			int id =sc.nextInt();
			c = inputCategory();
			c.setCategory_id(id);
			
			int update = dao.updateCategory(c);
			res = (update > 0) ? "Category Updated .." : "Category not updated !";
			System.out.println(res);
			break;
		case 3:
			// delete CateGory
			System.out.println("Enter Category id For remove Category ");
			int delete = dao.deleteCategory(sc.nextInt());
			res = (delete > 0) ? "Remove Category " : "Category not Remove";
			System.out.println(res);
		case 4:
			System.out.println("Enter Category id For Get Category ");
			Category category = dao.getCategoryById(sc.nextInt());
			System.out.println("++++++++++++++++++++++++++++++++");
			System.out.println(
					"Category Id : " + category.getCategory_id() + "Cateogory Name " + category.getCategory_name());
			System.out.println("++++++++++++++++++++++++++++++++");
			break;
		case 5:
			List<Category> list = dao.getAllCategories();
			if (!list.isEmpty()) {
				System.out.println("++++++++++++++++++++++++++++++++++++");
				list.forEach(cat -> {
					
					System.out.println("Category Id : " + cat.getCategory_id() + "Cateogory Name "+ cat.getCategory_name());
					System.out.println("--------------------------------");

				});
				System.out.println("++++++++++++++++++++++++++++++++++");
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + chosie);
		}
		 System.out.println("++++++++++++++++++++++++++++++++++");
		 System.out.println("Press 1 For reoperation Above Operation ");
		 System.out.println("Press 0 For Exit Application");
		 op=sc.nextInt();
	}while(op>0);
	}

	private static Category inputCategory() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Category Name");
		//sc.nextLine();
		String name = sc.nextLine();
		Category cat = new Category();
		cat.setCategory_name(name);
		return cat;
	}

}

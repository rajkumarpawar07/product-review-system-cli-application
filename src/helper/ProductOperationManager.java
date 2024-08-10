package helper;

import model.Category;
import model.Product;
import java.util.*;

import dao.CategoryDao;
import dao.ProductDao;

public class ProductOperationManager {

	public static void productOperaction() throws Exception {
		ProductDao dao = new ProductDao();
		String res;
		int p_id;
		Product product;
		Category category;
		Scanner sc = new Scanner(System.in);
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("Start Product Operation");
		System.out.println("++++++++++++++++++++++++++++++++");
		int op = 0;
		do {
			System.out.println("Press 1 for Add Product");
			System.out.println("Press 2 for Update product");
			System.out.println("Press 3 for remove Product");
			System.out.println("Press 4 for Get Product By Id");
			System.out.println("Press 5 for Get All Product Id");
			int choise = sc.nextInt();
			switch (choise) {
			case 1:
				System.out.println("Fill Following Infromation");
				category = new Category();
				System.out.println("Enter Categary Name");
				sc.nextLine();
				category.setCategory_name(sc.nextLine());
				
				product = inputProduct();
				//System.out.println(product);

				CategoryDao cdao = new CategoryDao();
				category = cdao.isCategoryExistOrNot(category);
				    int category_id = category.getCategory_id();
				
				product.setCategoryId(category_id);
				System.out.println(product);
				res = (dao.addProoduct(product) > 0) ? "Product Successfully  Added" : "Product Not Added !";
				System.out.println(res);
				/*
				 * get input product Category and cheak it is exit or not
				 * System.out.println("Enter Category id ");
				 * category.setCategory_id(sc.nextInt());
				 * 
				 * System.out.println("Enter Categary Name"); // sc.nextLine();
				 * category.setCategory_name(sc.nextLine()); CategoryDao cdao = new
				 * CategoryDao(); category = cdao.isCategoryExistOrNot(category);
				 * product.setCategory_id(category.getCategory_id());
				 * 
				 * res = (dao.addProoduct(product) > 0) ? "Product Successfully  Added" :
				 * "Product Not Added !"; System.out.println(res);
				 */
				break;
			case 2:
				System.out.println("Enter product id For Update Prooduct");
				p_id = sc.nextInt();
				product = inputProduct();
				product.setProductId(p_id);
				res = (dao.updateProduct(product) > 0) ? "Product is Updated" : "Produc not Udated";
				System.out.println(res);
				break;
			case 3:
				System.out.println("Enter product id For Remove Prooduct");
				p_id = sc.nextInt();
				res = (dao.deleteProduct(p_id) > 0) ? "Product is Remove " : "Product is Not remove";
				break;
			case 4:
				System.out.println("Enter product id For Get Prooduct");
				p_id = sc.nextInt();
				Product p = dao.getProductById(p_id);
				category = new CategoryDao().getCategoryById(p.getCategoryId());
				System.out.println("+++++++++++++++++++++++++++++++++++++");
				System.out.print("P_id :" + p.getProductId() + ", P_Name :" + p.getProductName() + ", Price  :"
						+ p.getPrice() + ", Description :" + p.getDescription());
				System.out.println(
						", Category_id  : " + p.getCategoryId() + ", Category Name : " + category.getCategory_name());
				System.out.println("+++++++++++++++++++++++++++++++++++++");
				break;
			case 5:
				List<Product> list = dao.getAllProducts();
				System.out.println("+++++++++++++++++++++++++++++++++++++");
				for (Product ob : list) {
					category = new CategoryDao().getCategoryById(ob.getCategoryId());
					System.out.print("P_id :" + ob.getProductId() + ", P_Name :" + ob.getProductName() + ", Price  :"
							+ ob.getPrice() + ", Description :" + ob.getDescription());
					System.out.println(", Category_id  : " + ob.getCategoryId() + ", Category Name : "
							+ category.getCategory_name());

				}
				System.out.println("+++++++++++++++++++++++++++++++++++++");

				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choise);
			}
		} while (op > 0);

	}

	private static Product inputProduct() {
		Scanner sc = new Scanner(System.in);
		Product p = new Product();
//		System.out.println("Enter Category Id ");
//		p.setCategory_id(sc.nextInt());
		System.out.println("Enter Product Name ");
		//sc.nextLine();
		p.setProductName(sc.nextLine());
		System.out.println("Enter Product Price ");
		p.setPrice(sc.nextDouble());
		System.out.println("Enter Description ");
		sc.nextLine();
		p.setDescription(sc.nextLine());
		return p;

	}
}

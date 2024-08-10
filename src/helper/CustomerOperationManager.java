package helper;

import java.util.List;
import java.util.Scanner;

import dao.CustomerDao;
import model.Customer;

public class CustomerOperationManager {

	public static void customerOperation() throws Exception {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("+++++++++++++++++++++++++");
		System.out.println("Start customer Operation");
		System.out.println("+++++++++++++++++++++++++");
		int op=0;
		do {
		System.out.println("Press 1 for add Product Customer");
		System.out.println("Press 2 for update Customer");
		System.out.println("Press 3 for delete Customer");
		System.out.println(" Press 4 for Get Customer ");
		System.out.println("Press 5 for Get All Customer");
		int choise=sc.nextInt();
		CustomerDao dao= new CustomerDao();
		Customer cust ;
		String res;
		int id;
		switch (choise) {
		case 1:
			System.out.println("Enter Following Information for Customer Operation");
			cust = inputCustomer();
			 res=(dao.addCustomer(cust)>0)?"Record Is insert ": "Record Not insert";
			 System.out.println(res);
			 break;
		case 2:
			System.out.println("Enter Following Information for Update Customer");
			System.out.println("Enter Id For update Student");
			id = sc.nextInt();
			cust=inputCustomer();
			cust.setCustomer_id(id);
			
			 res=(dao.updateCustomer(cust)>0)?"Record Is update ": "Record Not update";
			 System.out.println(res);
			 break;
		case 3:
			System.out.println("Enter Id For Delete Student");
			 id = sc.nextInt();
			 res=(dao.deleteCustomer(id)>0)?"Record Is deleted ": "Record Not deleted";
			 System.out.println(res);
			 break;
		case 4:
			System.out.println("Enter Id For Get Student");
			 id = sc.nextInt();
			 cust = dao.getCustomerById(id);
			 System.out.println("+++++++++++++++++++++++++");
			 System.out.println(cust);
			 System.out.println("+++++++++++++++++++++++++");
			 break;
		case 5:
			System.out.println("++++++++++++ All Customer++++++++++++ ");
			List<Customer> list= dao.getAllCustomers();
			for (Customer ob : list) {
				System.out.println(ob);
				System.out.println("--------------------------");
			}
			System.out.println("++++++++++++++++++++++++++++++++++++");
			break;
			
		default:
			
			throw new IllegalArgumentException("Unexpected value: " + choise);
		}
		 System.out.println("++++++++++++++++++++++++++++++++++");
		 System.out.println("Press 1 For reoperation Above Operation ");
		 System.out.println("Press 0 For Exit Application");
		 op=sc.nextInt();
	}while(op>0);
	 
		
		
	}
	public static Customer inputCustomer() {
		Customer c= new Customer();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter name ");
		 c.setCustomer_name(sc.nextLine());
		 System.out.println("Enter Email");
		 c.setCustomer_email(sc.nextLine());
		 System.out.println("Enter Mobile No");
		 c.setCustomer_mNo(sc.nextLong());
		return c;
	}

}

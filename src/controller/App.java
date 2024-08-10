package controller;

import java.util.Scanner;

import helper.CategoryOperationManager;
import helper.CustomerOperationManager;
import helper.ProductOperationManager;
import helper.RatingAnalysisManager;
import helper.ReviewSubmissionManager;
import model.Category;

public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Welcome To my Application");
		System.out.println("+++++++++++++++++++++++++++");
		Scanner sc = new Scanner(System.in);
		int op=0;
		do {
			System.out.println("Press 1 For All Category Operation");
			System.out.println("Press 2 For All product Operation");
			System.out.println("Press 3 for All Customer Operaration ");
			System.out.println("Press 4 Review Submission:");
			System.out.println("Press 5 Rating Analysis");
			 int choise=sc.nextInt();
			
			 
			 switch (choise) {
			case 1: 
				CategoryOperationManager.categoryOperation();
				break;
			case 2:
				ProductOperationManager.productOperaction();
				break;
			case 3:
				CustomerOperationManager.customerOperation();
				break;
			case 4:
				ReviewSubmissionManager.reviewSubmission();
				break;
			case 5:
				 RatingAnalysisManager.ratingAnalysis();
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choise);
			}
			 
			 System.out.println("++++++++++++++++++++++++++++++++++");
			 System.out.println("Press 1 For reoperation Above Operation ");
			 System.out.println("Press 0 For Exit Application");
			 op=sc.nextInt();
		}while(op>0);
		System.out.println("Thank You For Visit My Application");
		 
	}

	
	

}

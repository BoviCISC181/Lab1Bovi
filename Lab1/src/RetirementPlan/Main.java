package RetirementPlan;

import org.apache.poi.ss.formula.functions.FinanceLib;
import java.util.Scanner;

/*
 * Ben Bovi, Lab 1
 * Retirement planning calculations.
 */
public class Main {
	public static void main(String[] args){
		
		//creating scanner object
		Scanner in = new Scanner(System.in);
		
		//innitialized the double
		double annual_return;
		
		//this will ask for input until it is valid (0.0 <= annual_return <= 0.03)
		do{
			System.out.println("Enter your expected annual return as a proportion of 1 (ex: '0.0271' is 2.71%): ");
			annual_return = in.nextDouble();
			if ((annual_return<0) || (annual_return>0.03)){
				System.out.println("error: input not within viable range");
			}
		}while((annual_return<0) || (annual_return>0.03));
		
		//divide by 12 for months...
		annual_return = annual_return/12;
		
		System.out.println("Enter your reqired monthly income during retirement: ");
		double required_income = in.nextDouble();
		
		System.out.println("Enter the number of years you plan to live in retirement: ");
		int years_retired = (in.nextByte()*12);
		
		System.out.println("Enter monthly SSI payment: ");
		double monthlySSI = in.nextDouble();
		
		//Present Value calculation using the finance jar
		//multiplied by -1 because pv method returns a negative for some reason
		double pv = FinanceLib.pv(annual_return, years_retired, (required_income-monthlySSI), 0, false)*-1;
		
		System.out.println("Enter the number of years you plan on working (to the nearest year): ");
		double years_to_work = (in.nextDouble()*12);
		
		//like the do loop for annual_return, this makes sure input is within the specified bounds.
		double annual_interest_on_investment;
		do{
			System.out.println("Enter annual rate of return before retirement (between 0 and 20 percent): ");
			annual_interest_on_investment = in.nextDouble();
			if ((annual_interest_on_investment<0) || (annual_interest_on_investment>0.2)){
				System.out.println("error: input not within viable range");
			}
		}while((annual_interest_on_investment<0) || (annual_interest_on_investment>0.2));
		//taking care of monthly instead of annual by dividing by 12
		annual_interest_on_investment = annual_interest_on_investment/12;
		
		//save_each_month multiplied by -1 to get rid of negative value
		double save_each_month = FinanceLib.pmt(annual_interest_on_investment, years_to_work, 0, pv, false);
		System.out.println("Save each month = $"+save_each_month*-1+"\nTotal to save = $"+pv);
		
		//closing the scanner
		in.close();
	}
}

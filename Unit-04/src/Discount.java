//(c) A+ Computer Science
//www.apluscompsci.com

//Name - Daniel Egorov
//Date - 18 Jan 2022
//Class - APCSA
//Lab  - Unit 4 Discount

import static java.lang.System.*;

import java.util.Scanner;

public class Discount {

  //instance variables and constructors could be used, but are not really needed

  //getDiscountedBill() will return final amount of the bill
  //			if the bill is >2000, the bill receives a 15% discount
  public static double getDiscountedBill(double bill) {
    if (bill > 2000) return bill * 0.85; else return bill;
  }
}

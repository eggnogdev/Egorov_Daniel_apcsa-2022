import javax.swing.ToolTipManager;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

public class TotalRowRunner {

  public static void main(String args[]) throws Exception {
    //add code here
    TotalRow runner = new TotalRow();
    System.out.print("Row totals are : ");
    System.out.println(TotalRow.getRowTotals(new int[][] { { 1, 2, 3 }, { 5, 5, 5, 5 } }));
    System.out.print("Row totals are : ");
    System.out.println(TotalRow.getRowTotals(new int[][] { { 1, 2, 3 }, { 5 }, { 1 }, { 2, 2 } }));
    System.out.print("Row totals are : ");
    System.out.println(
      TotalRow.getRowTotals(new int[][] { { 1, 2 }, { 5, 5 }, { 5, 5 }, { 4, 5, 6, 7 }, { 123124, 12312 } })
    );
  }
}

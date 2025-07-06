package submissions; //done 1
 
import java.util.Scanner;
class FraudDetect
{
	void isSuspecious(int[][] table,int W,int[] w)
	{
		int n = table.length; //NoOfWeights + 1
		
		for(int i=0;i<=W;i++)
			table[0][i] = 0; 
		
		for(int i=1;i<n;i++)//i is the row number , considering each weight at a time
		{
			for(int wgt=0;wgt<=W;wgt++)//wgt is each column maxAmount
			{
				if(w[i]>wgt) //if the considered Amount in each row exceeds the maximum in column then
					table[i][wgt] = table[i-1][wgt];
				else
					table[i][wgt] = Math.max(table[i-1][wgt],w[i]+table[i-1][wgt-w[i]]);
			}
		}
		
		
		//check if the amount is found or not
		if(table[n-1][W] == W) //if last row and column contains suspcious amount 
		{
			System.out.print("Yes, ");
			
			//print combinations
			System.out.print("because the subset ");
			int item = n-1; //row number
			int k = table[n-1][W]; //last row and column
			while(item>0 && k>0) 
			{
				if(table[item][k] != table[item-1][k])
				{
					System.out.print(w[item]+",");
					k = k - w[item];
				}
				item--;
			}
			System.out.println(" forms the target sum.");
		}
		else
			System.out.println("No Suspecious found !!");
	}
}
public class FraudDetection {
	public static void main(String[] args) 
	{
		FraudDetect fd = new FraudDetect();
		Scanner sc = new Scanner(System.in);
		int[][] table;
		int W; //W is the maximum weight to be considered
		int w[]; // This array is used to store each values of given weight
		
		System.out.print("Enter the number of transaction done : ");
		int NoOfWeights = sc.nextInt();
		w = new int[NoOfWeights+1]; 
		w[0] = 0; //first weight be default 0, when no weight is considered
		
		System.out.println("Enter the list of transaction : ");
		for(int i=1;i<=NoOfWeights;i++)
			w[i] = sc.nextInt();
		System.out.print("Enter the Suspecious amount to be found : ");
		W = sc.nextInt();
		
		table = new int[NoOfWeights+1][W+1]; //declare table
		fd.isSuspecious(table,W,w);
	}
}

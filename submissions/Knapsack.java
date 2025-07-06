package submissions; //done 1
import java.util.Scanner;

class KnapsackClass
{
	void KnapsackMethod(int[][] table,int W,int[] w,int[] v)
	{
		int n = table.length; //here n = NoOfItems + 1
		
		//by selecting no item we can put 0 in all columns
		for(int i=0;i<=W;i++)
			table[0][i] = 0; 
		
		for(int i=1;i<n;i++)//i is the row number , considering each weight at a time. Here n = NoOfItems+1
		{
			for(int wgt=0;wgt<=W;wgt++) //wgt is each column maxWeight
			{
				if(w[i]>wgt) //if the considered weight in each row exceeds the maximum in column then
					table[i][wgt] = table[i-1][wgt]; //choose upper value
				else
					table[i][wgt] = Math.max(table[i-1][wgt],v[i]+table[i-1][wgt-w[i]]); //find maximum
			}
		}
		
		//print combinations
		System.out.println("Maximum value is : "+table[n-1][W]);
		System.out.println("Combinations of most valuable items are : ");
		int item = n-1; //row number
		int k = W; //last row and column . here table is matrix of values not weights
		
		while(item>0 && k>0) 
		{
			if(table[item][k] != table[item-1][k])
			{
				System.out.println("item"+item+" : "+w[item]+"("+v[item]+") ");
				k = k - w[item];                    //dont change this because k holds weight not value
			}
			item--;
		}
	}
}
public class Knapsack {
	public static void main(String[] args) 
	{
		KnapsackClass ks = new KnapsackClass();
		Scanner sc = new Scanner(System.in);
		int[][] table;
		int W; //W is the maximum weight to be considered
		int w[]; // This array is used to store each weights
		int v[]; //This array is used to store values
		
		System.out.print("Enter the number of items given : ");
		int NoOfItems = sc.nextInt();
		w = new int[NoOfItems+1]; //because of indexing ,one extra index is needed
		v = new int[NoOfItems+1]; 
		w[0] = 0; //first weight be default 0, when no weight is considered
		v[0] = 0; //similarly value
		
		System.out.println("Enter the weights and values : ");
		for(int i=1;i<=NoOfItems;i++)
		{
			w[i] = sc.nextInt();
			v[i] = sc.nextInt();
		}
		System.out.print("Enter the Max Weight Limit : ");
		W = sc.nextInt();
		
		table = new int[NoOfItems+1][W+1]; //declare table
		ks.KnapsackMethod(table,W,w,v);
	}
}

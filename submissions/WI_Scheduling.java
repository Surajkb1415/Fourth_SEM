package submissions; //done 1
import java.util.Arrays;
import java.util.Scanner;

class WIS
{
	int P[];
	int M[];
	int j;
	
	int findOPT(int tasks[][])
	{
		int maxProfit = 0;
		int NumTask = tasks.length;
		P = new int[NumTask];
		M = new int[NumTask];
		
		//sort by finish time (1)
		Arrays.sort(tasks,(a,b)->Integer.compare(a[1],b[1]));  
		
		//calculate P[]
		for(j=0;j<NumTask;j++)                     
			P[j] = findP(tasks,j);
		
		//initialize M
		for(j=0;j<NumTask;j++) 
			M[j]=-1;
		
		//base case. First task profit is its own value
		M[0] = tasks[0][2];
	
		maxProfit = ComputeOPT(tasks,P,M,NumTask-1);
		return maxProfit;
	}
	int findP(int tasks[][],int index)
	{
		//using binary search to find p
		int low = 0, high = index-1,result = -1;
		while(low <= high)
		{
			int m = (low+high)/2;
			
			//Check if task m ends before current task starts
			if(tasks[m][1] <= tasks[index][0])
			{
				low = m + 1;
				result = m;
			}
			else
			{
				high = m - 1;
			}
		}
		return result;
	}
	int ComputeOPT(int tasks[][],int P[],int M[],int j) //recursion call
	{
		if(j<0)
			return 0;
		if(M[j] == -1)
			M[j] = Math.max(tasks[j][2]+ComputeOPT(tasks,P,M,P[j]),ComputeOPT(tasks,P,M,j-1));
		return M[j];
	}
}
public class WI_Scheduling 
{
	public static void main(String[] args) 
	{
		WIS wis = new WIS();
		Scanner sc = new Scanner(System.in);
		int n,maxProfit;
		int tasks[][];
		
		System.out.print("Enter the number of Tasks : ");
		n = sc.nextInt();
		tasks = new int[n][3];
		System.out.println("Enter the task (Start Finish Value) : ");
		for(int i=0;i<n;i++)
		{
			tasks[i][0] = sc.nextInt();
			tasks[i][1] = sc.nextInt();
			tasks[i][2] = sc.nextInt();
		}
		
		maxProfit = wis.findOPT(tasks);
		
		System.out.println("Maximum profit is "+maxProfit);
	}
}

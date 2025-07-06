package submissions; //done 1

import java.util.*;

class TSPclass
{
	int[][] parent; //used to store the next city for each of the state
	
	int TSPmethod(int[][] dist)
	{
		int n = dist.length;
		int[][] memo = new int[n][1<<n];
		parent = new int[n][1<<n];
		
		for(int i=0;i<n;i++)
			Arrays.fill(memo[i],-1);
		
		return totalDist(1,0,n,dist,memo);
	}
	int totalDist(int mask,int curr,int n,int[][] dist,int[][] memo)
	{
		if(mask == (1<<n)-1)
		{
			parent[curr][mask] = -1;
			return dist[curr][0];
		}
		
		if(memo[curr][mask] != -1)
			return memo[curr][mask];
		
		int ans = Integer.MAX_VALUE;
		int nextcity = -1;
		
		for(int i=0;i<n;i++)
		{
			if((mask & (1<<i)) == 0)
			{
				int newdist = dist[curr][i] + totalDist(mask|(1<<i),i,n,dist,memo);
				if(newdist < ans)
				{
					ans = newdist;
					nextcity = i;
				}
			}
		}
		parent[curr][mask] = nextcity;
		return memo[curr][mask] = ans;
	}
	List<Integer> getpath(int n)
	{
		List<Integer> path = new ArrayList<>();
		int mask = 1;
		int curr = 0;
		
		path.add(0);//starting city
		while(true)
		{
			int next = parent[curr][mask];
			if(next == -1)
				break;
			
			path.add(next);
			mask |= (1 << next);
			curr = next;
		}
		path.add(0);//return to start
		return path;
	}
}
public class TSPmain 
{
	public static void main(String[] args) 
	{
		TSPclass tsp = new TSPclass();
		Scanner sc = new Scanner(System.in);
		int n;
		
		System.out.print("Enter the number of cities : ");
		n = sc.nextInt();
		
		int distances[][] = new int[n][n];
		System.out.println("Enter the distance of cities in matrix : ");
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				distances[i][j] = sc.nextInt();
		
		int shortdistance = tsp.TSPmethod(distances);
		System.out.println("The minimum distance he can travel is "+shortdistance);
		
		List<Integer> route = tsp.getpath(n);
		System.out.print("Optimal delivery route : ");
		for(int i=0;i<route.size();i++)
		{
			System.out.print("City "+route.get(i));
			if(i != route.size()-1)
				System.out.print(" -> ");
		}
		
	}
}
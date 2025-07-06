package submissions; //done

import java.util.*;

class Pair implements Comparable<Pair>
{
	int v;
	int wt;
	int parent;
	
	Pair(int v,int wt,int parent)
	{
		this.v = v;
		this.wt = wt;
		this.parent = parent;
	}
	public int compareTo(Pair that)
	{
		return this.wt-that.wt;
	}
}
class PrimsClass
{
	int PrimsMST(int V,int E,int[][] edges)
	{
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		for(int i=0;i<V;i++)
			adj.add(new ArrayList<>());
		for(int i=0;i<edges.length;i++)
		{
			int u = edges[i][0];
			int v = edges[i][1];
			int wt = edges[i][2];
			adj.get(u).add(new Pair(v,wt,u));
			adj.get(v).add(new Pair(u,wt,v));
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(0,0,-1));
		int[] vis = new int[V];
		int totalWeight = 0;
		
		System.out.println("Edges in MST: ");
		while(!pq.isEmpty())
		{
			Pair node = pq.poll();
			int v = node.v;
			int wt = node.wt;
			int parent = node.parent;
			
			if(vis[v]==1)
				continue;
			
			vis[v] = 1;
			if(parent != -1)
				System.out.println(parent+" - "+v+"(weight: "+wt+")");
			totalWeight += wt;
			
			for(Pair it: adj.get(v))
			{
				if(vis[it.v]==0)
					pq.add(new Pair(it.v,it.wt,v));
			}
		}
		return totalWeight;
	}
}
public class PrimsMain 
{
	public static void main(String[] args) 
	{
		PrimsClass p = new PrimsClass();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of vertices : ");
		int V = sc.nextInt();
		System.out.print("Enter the number of edges : ");
		int E = sc.nextInt();
		
		int[][] edges = new int[E][3];
		
		System.out.println("Enter each edge in format : Source destination weight");
		for(int i=0;i<E;i++)
		{
			edges[i][0] = sc.nextInt();
			edges[i][1] = sc.nextInt();
			edges[i][2] = sc.nextInt();
		}
		int totalWeight = p.PrimsMST(V,E,edges);
		System.out.println("Total weight of MST : "+totalWeight);
	}
}

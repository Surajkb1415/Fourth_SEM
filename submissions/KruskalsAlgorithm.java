package submissions; //done

import java.util.*;
class KA
{
        int KruskalsMST(int V,int edges[][])
        {
        		//sorting array by weight column
                Arrays.sort(edges,Comparator.comparing(e->e[2])); 

                DSU dsu = new DSU(V); //initialize disjoint set union
                int cost = 0,count = 0;

                System.out.println("Edges in the MST : ");
                for(int[] e : edges)  //for each edges e[]
                {
                		//0 is source, 1 is destination, 2 is weight
                        int x = e[0],y = e[1],w = e[2]; 
                        
                        if(dsu.find(x)!=dsu.find(y)) //checking if root parent of x and y are not same 
                        {
                                dsu.union(x,y); //if parent of both are not same then there is no cycle
                                cost+=w;
                                System.out.println(x+" - "+y+" : weight = "+w);
                                if(++count == V-1)
                                        break;
                        }
                }
                return cost;
        }
}
class DSU
{
        int parent[],rank[];
        DSU(int n)
        {
                parent = new int[n];
                rank = new int[n];
                for(int i=0;i<n;i++)
                {
                        parent[i] = i;
                        rank[i] = 1;
                }
        }
        //find method returns the parent at the top level
        int find(int i)
        {
                if(parent[i]!=i)
                        parent[i]=find(parent[i]);
                return parent[i];
        }
        //union of two nodes done here
        void union(int x,int y)
        {
                int s1 = find(x);
                int s2 = find(y);
                if(s1!=s2)
                {
                        if(rank[s1]<rank[s2])
                                parent[s1] = s2;
                        else if(rank[s1]>rank[s2])
                                parent[s2] = s1;
                        else
                        {
                                parent[s2] = s1;
                                rank[s1]++;
                        }
                }
        }
}
class KruskalsAlgorithm
{
     public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        KA ka = new KA();
        int V,E;
        int edges[][];
        System.out.print("Enter number of cities (vertex) : ");
        V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        E = sc.nextInt();

        edges = new int[E][3];
        System.out.println("Enter the edges in format of : source destination weight");
        for(int i=0;i<E;i++)
        {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
                edges[i][2] = sc.nextInt();
        }
        int totalcost = ka.KruskalsMST(V,edges);
        System.out.println("Minimum cost is "+totalcost);
    }
}

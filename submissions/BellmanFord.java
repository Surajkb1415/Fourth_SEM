package submissions; //done 1
import java.util.*;

class BF
{
        int[]  bellmanford(int V,int edges[][],int src)
        {
                int dist[] = new int[V];
                Arrays.fill(dist,Integer.MAX_VALUE);
                dist[src] = 0;

                int u,v,w;

                for(int i=0;i<V;i++)
                {
                        for(int e[] : edges)
                        {
                                u = e[0]; v = e[1]; w = e[2];

                                if(dist[u]!=Integer.MAX_VALUE && dist[v] > dist[u] + w)
                                {
                                        if(i == V-1)
                                                return (new int[]{-1});

                                        dist[v] = dist[u] + w;
                                }
                        }
                }
                return dist;
        }
}
class BellmanFord
{
        public static void main(String[] args)
        {
                BF bf = new BF();
                Scanner sc = new Scanner(System.in);
                int V,E,src;
                int edges[][];
                int dist[];
                String destination;
                
                System.out.print("Enter the number of vertices : ");
                V = sc.nextInt();
                System.out.print("Enter the number of edges : ");
                E = sc.nextInt();

                edges = new int[E][3];
                System.out.println("Enter the edges (src,dest,weight) : ");
                for(int i=0;i<E;i++)
                {
                        edges[i][0] = sc.nextInt();
                        edges[i][1] = sc.nextInt();
                        edges[i][2] = sc.nextInt();
                }

                System.out.print("Enter the source : ");
                src = sc.nextInt();

                dist = bf.bellmanford(V,edges,src);

                //print
                if(dist[0] == -1)
                        System.out.println("Negative cycle dectected");
                else
                {
                        System.out.println("Shortest distance from are : from "+src);
                        for(int i=0;i<V;i++)
                        {
                        	destination = (dist[i]>=Integer.MAX_VALUE) ? "INF" : String.valueOf(dist[i]);
                        	System.out.println(" to -> "+i+" : "+destination);
                        }
                }

        }
}
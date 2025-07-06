package submissions;  //done

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Truck
{
	// Builds the adjacency list from the list of edges
    ArrayList<ArrayList<ArrayList<Integer>>> buildGraph(int[][] edges, int numLocations) 
    {
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < numLocations; i++) 
            adj.add(new ArrayList<>());

        for (int[] edge : edges) 
        {
            int u = edge[0];              //src
            int v = edge[1];              //dest
            int distance = edge[2];       //weight 

            ArrayList<Integer> e1 = new ArrayList<>();
            e1.add(v);
            e1.add(distance);
            adj.get(u).add(e1);
            
            ArrayList<Integer> e2 = new ArrayList<>();           //undirected graph. so also store the return edges
            e2.add(u);
            e2.add(distance);
            adj.get(v).add(e2);
        }
        return adj;
    }
    // Dijkstra’s algorithm to compute shortest distances and paths
    int[] dijkstra(int numLocations, int[][] edges, int source, int[] parent) 
    {
        ArrayList<ArrayList<ArrayList<Integer>>> adj = buildGraph(edges, numLocations);

        int[] distances = new int[numLocations];
        Arrays.fill(distances, Integer.MAX_VALUE);    //initialize the distance of each node from source to infinity
        distances[source] = 0;                        //distance from source to source is 0 

        Arrays.fill(parent, -1);  // Initialize parent array to -1 (indicating root)

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, source}); // {distance, node}

        while (!pq.isEmpty()) 
        {
            int[] current = pq.poll();
            int currDist = current[0];
            int u = current[1];

            for (ArrayList<Integer> neighbor : adj.get(u))   //neighbor will be pointing to each node stored in uth location in adj
            {
                int v = neighbor.get(0);
                int weight = neighbor.get(1);

                if (distances[v] > distances[u] + weight) 
                {
                    distances[v] = distances[u] + weight;
                    parent[v] = u;                       // Record parent
                    pq.offer(new int[]{distances[v], v});
                }
            }
        }

        return distances;
    }

    // Reconstructs and prints the path from source to destination
    void printPath(int dest, int[] parent) 
    {
        if (parent[dest] == -1) 
        {
            System.out.print(dest);
            return;
        }
        printPath(parent[dest], parent);
        System.out.print(" -> " + dest);
    }
}
public class TruckDijkstras
{
    public static void main(String[] args) 
    {
    	Truck t = new Truck();
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("Enter the no of locations(vertex) : ");
        int numLocations = sc.nextInt();
        System.out.print("Enter the no of routes : ");
        int routes = sc.nextInt();
        System.out.print("Enter the source : ");
        int source = sc.nextInt();

        int[][] edges = new int[routes][3];
        System.out.println("Enter the edges : Source Destination Weight");
        for(int i=0;i<routes;i++)
        {
        	edges[i][0] = sc.nextInt();           //src
        	edges[i][1] = sc.nextInt();           //dest
        	edges[i][2] = sc.nextInt();           //weight
        }

        int[] parent = new int[numLocations];     //parent of each vertex will be stored
        int[] shortestDistances = t.dijkstra(numLocations, edges, source, parent);

        System.out.println("Shortest distances and paths from location " + source + ":");
        for (int i = 0; i < shortestDistances.length; i++) 
        {
            System.out.print("To location " + i + " -> Distance: " + shortestDistances[i] + ", Path: ");
            t.printPath(i, parent);
            System.out.println();
        }
    }
}
package submissions;  //done
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class DFSTask
{
	public static void scheduleTasks(List<Integer>[] g, int[] inDeg) 
    {
        Stack<Integer> s = new Stack<>();
        boolean[] explored = new boolean[inDeg.length];
        
        for (int i = 0; i < inDeg.length; i++) 
        {
            if (inDeg[i] == 0) 
                s.push(i);
        }
        
        System.out.println("Task Execution Order:");
        while (!s.isEmpty()) 
        {
            int u = s.pop();
            if (explored[u]==false) 
            {
                explored[u] = true;
                System.out.println("Task " + u);
                
                for (int v : g[u]) 
                {
                    inDeg[v]--;
                    if (inDeg[v] == 0 && !explored[v]) 
                        s.push(v);
                }
            }
        }
    }
}
class TaskMain 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        DFSTask task = new DFSTask();
        
        System.out.print("Enter the number of tasks : ");
        int n = sc.nextInt();
        
        List<Integer>[] g = new List[n]; // 0-based indexing for tasks
        for (int i = 0; i < n; i++) 
            g[i] = new LinkedList<>();
        
        int[] inDeg = new int[n];
        
        System.out.print("Enter the number of dependencies : ");
        int d = sc.nextInt();
        
        System.out.println("Enter the dependencies (from to) :");
        for (int i = 0; i < d; i++) 
        {
            int t = sc.nextInt();
            int dep = sc.nextInt();
            g[t].add(dep);
            inDeg[dep]++;
        }
        
        task.scheduleTasks(g, inDeg);
        
    }
}

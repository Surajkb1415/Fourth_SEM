package submissions; //done 1
    
import java.util.*;
class NQueenClass
{
	List<Integer> nQueen(int n)
	{ 
		int[][] mat = new int[n][n];
		if(placeQueens(0,mat))
		{
			List<Integer> ans = new ArrayList<>();
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{
					if(mat[i][j] == 1)
						ans.add(j+1);
				}
			}
			return ans;
		}
		else
			return Collections.singletonList(-1);
	}
	boolean placeQueens(int row,int[][] mat)
	{
		int n = mat.length;
		
		//base case
		if(row == n) 
			return true; //finally stop at row_value = n for that column
		
		for(int i=0;i<n;i++) //check each column for a specific row
		{
			if(isSafe(mat,row,i))
			{
				mat[row][i] = 1;
				if(placeQueens(row+1,mat))
					return true;
				mat[row][i] = 0;
			}
		}
		return false;
	}
	boolean isSafe(int[][] mat,int row,int col)
	{
		int n=mat.length;
		
		for(int i=0;i<row;i++)           //check if any row has queens
			if(mat[i][col] == 1)
				return false;
		
		for(int i=row-1,j=col-1 ; i>=0 && j>=0 ; i--,j--) //check diagonal
			if(mat[i][j] == 1)
				return false;
		
		for(int i=row-1,j=col+1 ; i>=0 && j<n ; i--,j++) //check opposite diagonal
			if(mat[i][j] == 1)
				return false;
		
		return true;
	}
}
public class NQueenMain 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		NQueenClass nq = new NQueenClass();
		int n;
		System.out.print("Enter the number of servers : ");
		n = sc.nextInt();
		
		List<Integer> result = nq.nQueen(n);
		
		System.out.println("Place servers : ");
		if(result.size() == 1)
			System.out.println("No servers !!!!!");
		else
		{
			for(int i=0;i<n;i++)
				System.out.println("In Row :"+(i+1)+" place at Column : "+result.get(i));
			
			System.out.println("Final matrix Representation : ");
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{ 
					if(result.get(i) == (j+1))
						System.out.print(" 1 ");
					else
						System.out.print(" 0 ");
				}
				System.out.println();
			}
		}
	}
}

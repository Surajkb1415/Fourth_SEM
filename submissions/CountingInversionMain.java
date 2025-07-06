package submissions; //done
import java.util.Scanner;

class CountingInversionClass
{
	int merge_and_count(int B[],int C[],int A[])
	{
		int p = B.length,q = C.length;  //coping length of B[] and C[]
		int i=0 , j=0 , k=0;
		int count=0;
		while(i<p && j<q)
		{
			if(B[i] <= C[j])
			{
				A[k] = B[i];
				i++;
			}
			else
			{
				A[k] = C[j];
				j++;
				count += p-i;
			}
			k++;
		}
		
		while(i<p)
		{
			A[k] = B[i];
			k++;i++;
		}
		while(j<q)
		{
			A[k] = C[j];
			k++;j++;
		}
		return count;
	}
	int sort_and_count(int A[])
	{
		int n = A.length;
		int ra=0,rb=0,r=0;
		if(n>1)
		{
			int m = n/2,i,j,k; 
			int n1 = m;
			int n2 = n-m;
			int B[] = new int[n1];
			int C[] = new int[n2];
			
			for(i=0;i<n1;i++)         //A[0...n/2-1] copied to B
				B[i] = A[i];
			for(j=0;j<n2;j++)         //A[n/2...n-1] copied to C
				C[j] = A[j+m];
			
			ra = sort_and_count(B);
			rb = sort_and_count(C);
			r = merge_and_count(B,C,A);
		}
		return ra+rb+r;
	}
}

public class CountingInversionMain
{
	public static void main(String[] args) 
	{
		CountingInversionClass ci = new CountingInversionClass();
		Scanner sc = new Scanner(System.in);
		int A[],B[],C[];
		int n;
		int count1=0,count2=0,count3=0;
		System.out.print("Enter the number of songs in playlist : ");  //Number of songs
		n = sc.nextInt();
		A = new int[n];
		B = new int[n];
		C = new int[n];
		
		System.out.println("Enter the playlist of user 1 : ");
        for(int i=0;i<n;i++)
                A[i] = sc.nextInt();
        count1 += ci.sort_and_count(A); //user 1 counting inversion
        
        System.out.println("Enter the playlist of user 2 : ");
        for(int i=0;i<n;i++)
                B[i] = sc.nextInt();
        count2 += ci.sort_and_count(B); //user 2 counting inversion
        
        System.out.println("Enter the playlist of user 3 : ");
        for(int i=0;i<n;i++)
                C[i] = sc.nextInt();
        count3 += ci.sort_and_count(C); //user 3 counting inversion
		
        System.out.println("Counting inversions of user1 is "+count1);
        System.out.println("Counting inversions of user2 is "+count2);
        System.out.println("Counting inversions of user3 is "+count3);
        
        System.out.println();
        if(count1<count2)
        {
                if(count1<count3)
                        System.out.println("User 1 has minimum inversions");
                else
                        System.out.println("User 3 has minimum inversions");
        }
        else
        {
                if(count2<count3)
                        System.out.println("User 2 has minimum inversions");
                else
                        System.out.println("User 3 has minimum inversions");
        }
        System.out.println();
        
        System.out.println("Comparisions between : ");
        System.out.println("User1 and User2 is "+Math.abs(count1-count2));
        System.out.println("User2 and User3 is "+Math.abs(count2-count3));
        System.out.println("User1 and User3 is "+Math.abs(count1-count3));
	}
}
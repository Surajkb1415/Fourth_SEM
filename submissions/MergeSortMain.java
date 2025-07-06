package submissions;  //done
import java.util.Scanner;

class MergeSortClass
{
	void merge(int B[],int C[],int A[])
	{
		int p = B.length,q = C.length;  //coping length of B[] and C[]
		int i=0 , j=0 , k=0;
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
	}
	void mergesort(int A[])
	{
		int n = A.length;
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
			
			mergesort(B);
			mergesort(C);

			merge(B,C,A);
		}
	}
}
public class MergeSortMain
{
	public static void main(String[] args) 
	{
		MergeSortClass msc = new MergeSortClass();
	
		int arr[];
		int n[] = {100,200,300,500,1000,2000,3000,5000,6000,8000,10000,50000};
		double start,end;
		
		System.out.println("n\tMergeSort_time");
		//repeating loop
		for(int i=0;i<12;i++)
		{
			int size = n[i];
			arr = new int[size];
			for(int j=0;j<size;j++)
				arr[j] = (int)(Math.random()*size);
			
			start = System.nanoTime();
			msc.mergesort(arr);
			end = System.nanoTime();
			
			System.out.println(size+"\t"+(double)(end-start)/100000);
		}
		//repeating loop
	}
}


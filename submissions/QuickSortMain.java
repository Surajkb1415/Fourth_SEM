package submissions;  //done
import java.util.Scanner;

class QuickSortClassNew
{
	void swap(int arr[],int a,int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	int pivotfun(int arr[],int low,int high)
	{
		int pivot = arr[low];  //set pivot to first element
		int i = low+1;
		int j = high;
		
		while(i<=j)
		{
			while(i<=high && arr[i]<pivot)
				i++;
			while(j>=low && arr[j]>pivot)
				j--;
			if(i<j)
			swap(arr,i,j);
		}
		swap(arr,low,j);
		return j;
	}
	void quicksort(int arr[],int low,int high)
	{
		if(low<high)
		{
			int pi = pivotfun(arr,low,high);
			this.quicksort(arr,low,pi-1);    //left of pivot
			this.quicksort(arr,pi+1,high);   //right of pivot
		}
	}
}
public class QuickSortMain 
{
	public static void main(String[] args) 
	{
		int arr[];
		QuickSortClassNew qsc = new QuickSortClassNew();
		Scanner sobj = new Scanner(System.in);
		int n;
		System.out.println("Enter the size of array : ");
		n = sobj.nextInt();
		arr = new int[n];
		System.out.println("Enter the elements : ");
		for(int i=0;i<n;i++)
			arr[i] = sobj.nextInt();
		
		qsc.quicksort(arr,0,n-1);
		
		System.out.println("Output: ");
		for(int i=0;i<n;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
}

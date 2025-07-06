package submissions; //DONE (but if possible change string to char for names)

import java.util.*;
class GaleShapely 
{
	boolean wpreference(int[] preflist,int newman,int currentman)
	{
		for(int i : preflist)
		{
			if(i == newman)
				return true; //true if new man is selected
			if(i == currentman)
				return false;  //false if current man is selected
		}
		return false;
	}
	int[] GS_matching(int[][] prefMen,int[][] prefWomen,int N)
	{
		boolean freemen[] = new boolean[N];
		int womenPartner[] = new int[N];
		
		for(int x=0;x<N;x++)
		{
			womenPartner[x] = -1;
			freemen[x] = true;
		}
		
		int count = N;
		while(count>0)
		{
			int m;
			int w,m1;
			for(m=0;m<N;m++)
			{
				if(freemen[m])
					break;
			}
			for(int i=0;i<N && freemen[m];i++)
			{
				w = prefMen[m][i];
				if(womenPartner[w] == -1)
				{
					womenPartner[w] = m;
					freemen[m] = false;
					count--;
				}
				else
				{
					m1 = womenPartner[w];
					if(wpreference(prefWomen[w],m,m1))
					{
						womenPartner[w] = m;
						freemen[m] = false;
						freemen[m1] = true;
					}
				}
			}
		}
		return womenPartner;
	}
}

public class GaleShapelyMain 
{
	public static void main(String[] args) 
	{
		int N;
		String c;
		
		GaleShapely gs = new GaleShapely();
		Scanner sobj = new Scanner(System.in);
		HashMap<String,Integer> menName = new HashMap<>();
		HashMap<String,Integer> womenName = new HashMap<>();
		
		System.out.print("Enter the number of men or women : ");
		N = sobj.nextInt();
		
		int prefMen[][] = new int[N][N];
		int prefWomen[][] = new int[N][N];
		String mName[] = new String[N];                       //to store the manes like A,B,C... while giving input
		String wName[] = new String[N];
		
		System.out.println("Enter the men names (A,B,C,...) : ");
		for(int i=0;i<N;i++)
		{
			mName[i] = sobj.next();
			menName.put(mName[i],i);
		}
		System.out.println("Enter the women names (W,X,Y,..): ");
		for(int i=0;i<N;i++)
		{
			wName[i] = sobj.next();
			womenName.put(wName[i],i);
		}
		
		System.out.println("Enter the preference of men : ");
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				c = sobj.next();
				prefMen[i][j] = womenName.get(c);
			}
		}
		System.out.println("Enter the preference of women : ");
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				c = sobj.next();
				prefWomen[i][j] = menName.get(c);
			}
		}
		
		int wPart[] = gs.GS_matching(prefMen,prefWomen,N);	
		
		//output
		System.out.println("Stable Matching : ");
		for(int i=0;i<N;i++)
			System.out.println(" Man "+mName[wPart[i]]+" is engaged to woman "+wName[i]);
	}
}


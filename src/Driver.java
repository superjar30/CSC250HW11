import java.util.Random;

public class Driver 
{
	public static void main(String[] args) throws InterruptedException 
	{
		int[] ar = new int[100];
		
		Random r = new Random();
		for(int i = 0; i < ar.length; i++)
		{
			int random = r.nextInt(100);
			ar[i] = random;
		}
		
		for(int s : ar)
		{
			System.out.println(s);
		}
		
		System.out.println("\n\n\n\n");
		Driver.mergeSort(ar, 0, ar.length - 1);
		
		for(int s : ar)
		{
			System.out.println(s);
		}
	}
	
	
	static void mergeSort(int[] ar, int begin, int end) throws InterruptedException
	{
		WorkerBee wb1;
		WorkerBee wb2;
		if(begin != end)
		{
			int begin1 = begin;
			int end1 = begin + ((end - begin)/2);
			int begin2 = end1 + 1;
			int end2 = end;
			wb1 = new WorkerBee(ar, begin1, end1);
			wb2 = new WorkerBee(ar, begin2, end2);
			wb1.start();
			wb2.start();
			wb1.join();
			wb2.join();
			merge(ar, begin1, end1, begin2, end2);
		}
	}
	
	static void merge(int[] ar, int begin1, int end1, int begin2, int end2)
	{
		int[] temp = new int[end2 - begin1 + 1];
		int pos1 = begin1;
		int pos2 = begin2;
		for(int i = 0; i < temp.length; i++)
		{
			if(pos1 <= end1 && pos2 <= end2)
			{
				if(ar[pos1] < ar[pos2])
				{
					temp[i] = ar[pos1];
					pos1++;
				}
				else
				{
					temp[i] = ar[pos2];
					pos2++;
				}
			}
			else
			{
				//either pos1 or pos2 is off the end of their list and the other guy is the 
				//default winner
				if(pos1 > end1)
				{
					temp[i] = ar[pos2];
					pos2++;
				}
				else
				{
					temp[i] = ar[pos1];
					pos1++;
				}
			}
		} //end of for loop
		
		//copy temp back over ar from begin1 to end2
		int posInTemp = 0;
		for(int i = begin1; i <= end2; i++)
		{
			ar[i] = temp[posInTemp];
			posInTemp++;
		}
	}
	
	
	
	
}
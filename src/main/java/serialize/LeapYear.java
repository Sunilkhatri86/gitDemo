package serialize;

import java.util.Random;

import org.testng.annotations.Test;

public class LeapYear {

	@Test
	public void leapYear()
	{
		
		int num =2024;
		if(num%4==0)
		{
			System.out.println("Leap year");
		}
		else
		{
			System.out.println("Not a Leap year");
		}
	}
	
	@Test
	public void randon()
	{
	      //  char[] characters = "abcdefghijklmnopqrstuvwxyz".toCharArray(); 

	        char[] characters = "43453454353444".toCharArray(); 
	        
	        long currentTimeMillis = System.currentTimeMillis(); 
	        System.out.println("Sunillll"+currentTimeMillis);
	        System.out.println(characters.length);
	        int index = (int) (currentTimeMillis % characters.length); 
	       System.out.println( characters[index]); 
	       

	        Random random = new Random();
	        random.nextInt();
	}
}

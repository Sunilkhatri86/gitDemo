package serialize;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class Vowels {

	@Test
	public void vowels()
	{
		String str ="Sunilii";
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		char [] strArray=str.toCharArray();
		
	
		for (int i = 0; i < strArray.length; i++) {
			{		
				if (strArray[i] == 'a' || strArray[i] == 'e' || strArray[i] == 'i' || strArray[i] == 'o' || strArray[i] == 'u')
					{
					
					if(map.containsKey(strArray[i]))
					{
						map.put(strArray[i], map.get(strArray[i])+1);
					}
					
					else
					{
						map.put(strArray[i], 1);
					}
						
					System.out.println(strArray[i] + " is vowel");
					}
					else
					{
						System.out.println(strArray[i] + " is not vowel");
					}
	
}
			
		}
		

		System.out.println(map);
	}

}
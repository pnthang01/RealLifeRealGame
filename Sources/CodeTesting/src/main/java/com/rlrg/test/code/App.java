package com.rlrg.test.code;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String str1 = "skjdflsjdflsjdflsjdflksdjfsldjfslsdfsdfsdsdfsdfdsfdsfsdfsdfsdfdjfsldkfjsdjf||||||";
	private static final String str2 = "slkdjflskjdflksjdflksdjflksdjfldssdfsdfsdfsdfdsfdsfdsfsdfdsfdsfjflsdjfsfsdfdsfdsf";
	
	public static void testPerformance(){
		long start = System.nanoTime();
		String finalStr = str1 + str2 + str1 + str2;
		System.out.println("Takes " + (System.nanoTime() - start));
		//
		long start1 = System.nanoTime();
		String finalStr1 = new StringBuilder(str1).append(str2).append(str1).append(str2).toString();
		System.out.println("Takes " + (System.nanoTime() - start1));
	}
	
    public static void main( String[] args )
    {
    	System.out.println(Math.ceil((double)10/4));
        //testPerformance();
    }
}

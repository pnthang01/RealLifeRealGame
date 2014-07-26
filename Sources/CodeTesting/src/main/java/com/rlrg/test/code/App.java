package com.rlrg.test.code;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.apache.commons.lang.RandomStringUtils;

import com.rlrg.dataserver.base.controller.BaseUtils;
import com.rlrg.dataserver.task.entity.enums.DifficultyLevel;

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
    	System.out.println(DifficultyLevel.HARD);
    	System.out.println(Math.ceil((double)10/4));
    	System.out.println(RandomStringUtils.random(8, true, true));
    	System.out.println(RandomStringUtils.random(8, "Trí thức"));
    	String s = Normalizer.normalize("Đ", Form.NFD);
    	String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        System.out.println(temp.replaceAll("[^\\p{ASCII}]", ""));
    	System.out.println(s);
    	//
    	System.out.println(BaseUtils.md5("123456"));
    }
}

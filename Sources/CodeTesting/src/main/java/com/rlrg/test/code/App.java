package com.rlrg.test.code;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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
	
    public static void main( String[] args ) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
//    	System.out.println(DifficultyLevel.HARD);
//    	System.out.println(Math.ceil((double)10/4));
//    	System.out.println(RandomStringUtils.random(8, true, true));
//    	System.out.println(RandomStringUtils.random(8, "Trí thức"));
//    	String s = Normalizer.normalize("Đ", Form.NFD);
//    	String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
//        System.out.println(temp.replaceAll("[^\\p{ASCII}]", ""));
//    	System.out.println(s);
//    	//
//    	System.out.println(BaseUtils.md5("123456"));
//    	String stringToEncrypt = "test.jpg";
//    	System.out.println(stringToEncrypt.hashCode());
//    	MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//    	messageDigest.update(stringToEncrypt.getBytes());
//    	String encryptedString = new String(messageDigest.digest());
//    	System.out.println(encryptedString);
//    	
//    	Random generator = new Random(System.currentTimeMillis());
//    	System.out.println(generator.nextInt());
    	String test = "Bạn đã tạo 3 nhiệm vụ tăng thể lực, chúc mừng bạn!";
    	String question = new String(test.getBytes(), "UTF-8"); 
    	System.out.println(question);
    	//
    	String string = "abc\u5639\u563b";
    	System.out.println(string);
        byte[] utf8 = string.getBytes("UTF-8");

        // Convert from UTF-8 to Unicode
        string = new String(utf8, "UTF-8");
        System.out.println(string);
    }
}

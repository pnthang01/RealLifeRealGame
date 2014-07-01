package com.rlrg.test.code;
import java.util.Formatter;
import java.util.List;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.CategoryWebServiceReader;
public class RestTesting {
	
	public static final String SERVER_URI = "http://localhost:8080/data/";
	
	public static void testGetAllCategories(){
        RestTemplate restTemplate = new RestTemplate();
        //
        //String finalUrl = SERVER_URI + "category/getAllCategories?pageNumber=1";
        String finalUrl = SERVER_URI + "category/getCategoriesByStatus?status=1&pageNumber=1";
        String json = restTemplate.getForObject(finalUrl, String.class);
        System.out.println(json);
	}
	
	public static void testGetAllCategoriesCase2(){
        RestTemplate restTemplate = new RestTemplate();
        //
        //String finalUrl = SERVER_URI + "category/getAllCategories?pageNumber=1";
        String finalUrl = SERVER_URI + "category/getCategoriesByStatus?status={status}&pageNumber={pageNumber}";
        String test = String.format(SERVER_URI + "category/getCategoriesByStatus?status={}&pageNumber={}", 1,1);
        System.out.println(test);
        String json = restTemplate.getForObject(finalUrl, String.class, 1, 1);
        System.out.println(json);
	}
	
	public static void testFormating(){
		String test = "?{}&sdfsdfsdfs={}";
		String finalStr = new Formatter().format(test, 1,1).toString();
		System.out.println(finalStr);
	}
	
	public static void testReader(){
		CategoryWebServiceReader cateReader = new CategoryWebServiceReader();
		try {
			ResultList<CategoryDTO>  result = cateReader.getCategoriesByStatus(true, 1);
			for(CategoryDTO dto : result.getList()){
				System.out.println(dto.getCode());
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConvertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testGetAllCategories();
//		testGetAllCategoriesCase2();
//		testFormating();
		testReader();
	}

}

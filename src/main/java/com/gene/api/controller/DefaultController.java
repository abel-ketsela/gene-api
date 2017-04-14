////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.json.JSONException;
import com.gene.api.service.DataLoader;

@RestController
public class DefaultController {
	
	@Autowired
	DataLoader dataLoader;
	
	@RequestMapping (method=RequestMethod.GET, value="/default")
	public String hello()
	{
			return "Hello World";

	}
	
	@RequestMapping (method=RequestMethod.GET, value="/loaddata")
	public String loadData() throws RestClientException, JSONException
	{
		try 
		{
			return dataLoader.loadGeneData();
			
		} 
		catch (RestClientException | JSONException e) {
			throw e;
		}
	}
	

	
}

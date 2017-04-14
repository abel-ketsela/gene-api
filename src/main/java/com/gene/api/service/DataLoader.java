////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.service;

import org.json.JSONException;
import org.springframework.web.client.RestClientException;

import com.gene.api.model.Gene;

public interface DataLoader {
	public String loadGeneData() throws RestClientException, JSONException;
	public void loadVariantData(Gene gene) throws RestClientException, JSONException;

}

////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.service.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gene.api.dao.GeneDAO;
import com.gene.api.model.Gene;
import com.gene.api.model.Variant;
import com.gene.api.service.DataLoader;

@Service("dataLoader")
public class DataLoaderImpl implements DataLoader  {

	@Autowired
	GeneDAO geneDao;
	
	@Value("${com.gene.api.datasource}")
	private String dataSourcePath;
	
	@Value("${com.gene.api.gene-path}")
	private String genePath;
	
	@Value("${com.gene.api.variant-path}")
	private String variantPath;
	
	
	@Override
	public String loadGeneData() throws RestClientException, JSONException {
		
		final String uri = dataSourcePath + genePath;
	     
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		JSONArray jsonarr = new JSONArray(result);
		
		geneDao.deleteAll(); // clean for previous data
		
		Gene gene;
		int counter=0;
		for (int i=0;i<jsonarr.length();i++)
		{
			JSONObject geneJson=jsonarr.getJSONObject(i);
			
			if (geneJson.getBoolean("oncogene")==true && 
					geneJson.get("entrezGeneId")!= null ){ // check for error data
			
				gene =new Gene();
				gene.setHugoSymbol(geneJson.getString("hugoSymbol"));
				JSONArray aliases =geneJson.getJSONArray("geneAliases");

				for (int j=0; j<aliases.length();j++)
				{
					gene.addAliases(aliases.getString(j));
				}
				
				gene.setEntrezGeneId(geneJson.getInt("entrezGeneId"));
				gene.setOncoGene(geneJson.getBoolean("oncogene"));
				gene.setTsg(geneJson.getBoolean("tsg"));
				
				loadVariantData(gene); // call for variant load
				
				
				geneDao.save(gene);
				counter++;
				 
				
			}
				
		}
		
		return "Previous Data Cleaned, and "+counter+" Gene Data Imported";

	}

	@Override
	public void loadVariantData(Gene gene) throws RestClientException, JSONException {
		
		final String uri = dataSourcePath+genePath+"/"+ gene.getEntrezGeneId()+variantPath;
	     
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		JSONArray jsonarr = new JSONArray(result);

		
		for (int i=0;i<jsonarr.length();i++)
		{
			JSONObject variantJson=jsonarr.getJSONObject(i);
				
				Variant variant =new Variant();
				
				variant.setAlteration(variantJson.getString("alteration"));
				variant.setConsequenceTerm(variantJson.getJSONObject("consequence")
														.getString("term"));
				
				variant.setGenerallyTruncating(variantJson.getJSONObject("consequence")
														.getBoolean("isGenerallyTruncating"));
				
				gene.addVariant(variant);
				
		}
	
	}
	
	
	

}

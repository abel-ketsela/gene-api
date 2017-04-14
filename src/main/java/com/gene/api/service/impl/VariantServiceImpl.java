////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gene.api.dao.VariantDAO;
import com.gene.api.model.Variant;
import com.gene.api.service.VariantService;


@Service("variantService")
public class VariantServiceImpl implements VariantService   {

	@Autowired
	VariantDAO variantDao;
	
	@Override
	public List<Variant> getVariantByGene(Integer geneId) {
		return variantDao.findByGeneEntrezGeneId(geneId);
	}

	@Override
	public Variant getVariant(Long variantId) {
		return variantDao.findOne(variantId);
	}

	@Override
	public List<Variant> getVariantByGenePaginated(Integer geneId, Integer start, Integer limit) {
		
		List<Variant> variants =this.getVariantByGene(geneId);
		
		if ((start+limit)<=variants.size())
		{
			return variants.subList(start, start+limit);
		}
		else 
		{
			return null;
		}
	}

	
	
	
	

}

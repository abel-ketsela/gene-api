////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.service;

import java.util.List;

import com.gene.api.model.Variant;

public interface VariantService {
	
	public Variant getVariant(Long variantId);
	public List<Variant> getVariantByGene(Integer geneId);
	public List<Variant> getVariantByGenePaginated(Integer geneId,Integer start,Integer limit);
}

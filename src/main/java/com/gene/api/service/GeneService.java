////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.service;

import java.util.List;

import com.gene.api.model.Gene;

public interface GeneService {
	
	public List<Gene> getAllGene();
	public List<Gene> getAllGenePaginated(int start, int limit);
	public Gene getGene(Integer geneId);
	
}

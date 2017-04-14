////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gene.api.dao.GeneDAO;
import com.gene.api.model.Gene;
import com.gene.api.service.GeneService;

@Service("geneService")
public class GeneServiceImpl implements  GeneService  {

	@Autowired
	GeneDAO geneDao;

	@Override
	public List<Gene> getAllGene() {

		List<Gene> genes =new ArrayList<>();
		geneDao.findAll()
				.forEach(gene->genes.add(gene));
		
		return genes;
	}

	@Override
	public Gene getGene(Integer geneId) {
		
		return geneDao.findOne(geneId);
	}

	@Override
	public List<Gene> getAllGenePaginated(int start, int limit) {
		
		
		List<Gene> genes =this.getAllGene();
		
		if ((start+limit)<=genes.size())
		{
			return genes.subList(start, start+limit);
		}
		else 
		{
			return null;
		}
	}
	
	

}

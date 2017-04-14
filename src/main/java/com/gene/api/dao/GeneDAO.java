////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.gene.api.model.Gene;

public interface GeneDAO extends CrudRepository<Gene,Integer>{

	
}

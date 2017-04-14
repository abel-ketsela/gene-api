////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.gene.api.model.Variant;

public interface VariantDAO extends CrudRepository<Variant,Long> {
	
	public List<Variant> findByGeneEntrezGeneId(Integer geneId);

}

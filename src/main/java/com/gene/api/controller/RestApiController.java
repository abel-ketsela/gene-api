////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////


package com.gene.api.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gene.api.model.Gene;
import com.gene.api.model.Variant;
import com.gene.api.service.GeneService;
import com.gene.api.service.VariantService;


@RestController
@RequestMapping (method=RequestMethod.GET,value="/api")
public class RestApiController {
	
	@Autowired
	GeneService geneService;
	
	@Autowired
	VariantService variantService;
	
	@RequestMapping ("/genes")
	public  List<Gene> getAllGene(@RequestParam(required = false, value = "start") Integer start,
									@RequestParam(required = false, value = "limit") Integer limit)
	{
		List<Gene> genes;
				
		if (start!=null && limit!=null)
		{
			genes=geneService.getAllGenePaginated(start, limit);

		}
		else
		{
			genes=geneService.getAllGene();

		}
		
		for (Gene gene : genes)
		{
			
			gene.add(linkTo(methodOn(RestApiController.class).getGene(gene.getEntrezGeneId())).withSelfRel());
		}
		
		return genes;
	}
	
	
	@RequestMapping ("/genes/{geneId}")
	public HttpEntity<Gene> getGene(@PathVariable Integer geneId)
	{
		Gene gene=geneService.getGene(geneId);
		

		if (gene!=null)
		{
			
		    Link variantsLink = linkTo(methodOn(RestApiController.class)
					.getGene(geneId)).slash(new String("variants")).withRel("variants");
			
			gene.add(variantsLink);
			
			return new ResponseEntity<Gene>(gene, HttpStatus.OK); 
		}
		else
		{
			return new ResponseEntity<Gene>(gene, HttpStatus.NOT_FOUND); 
		}
			
	}
	
	@RequestMapping ("/genes/{geneId}/variants")
	public List<Variant> getAllVariantByGene(@PathVariable Integer geneId, 
												@RequestParam(required = false, value = "start") Integer start,
													@RequestParam(required = false, value = "limit") Integer limit)
	{
		
		List<Variant> variants;
		if (start!=null && limit!=null)
		{
			variants=variantService.getVariantByGenePaginated(geneId, start, limit);
		}
		else
		{
			variants=variantService.getVariantByGene(geneId);

		}
		
		for (Variant variant : variants)
		{
			
			variant.add(linkTo(methodOn(RestApiController.class)
					.getVariantById(variant.getGene().getEntrezGeneId(),variant.getVId()))
					.withSelfRel());
			
		}
		
		
		return variants;
	}
	
	@RequestMapping ("/genes/{geneId}/variants/{variantId}")
	public Variant getVariantById(@PathVariable Integer geneId , @PathVariable Long variantId)
	{
		return variantService.getVariant(variantId);
	}
	

}

////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Variant extends ResourceSupport {
	
	@Id
	@GeneratedValue
	private Long id;
	private String alteration;
	private String consequenceTerm;
	private boolean isGenerallyTruncating;
	
	@ManyToOne
	@JoinColumn(name="entrezGeneId")
	@JsonIgnore
	private Gene gene;
	
	
	public Variant() {
	}
	
	public Long getVId() {
		return id;
	}
	public void setVId(Long id) {
		this.id = id;
	}
	public String getAlteration() {
		return alteration;
	}
	public void setAlteration(String alteration) {
		this.alteration = alteration;
	}
	public String getConsequenceTerm() {
		return consequenceTerm;
	}
	public void setConsequenceTerm(String consequenceTerm) {
		this.consequenceTerm = consequenceTerm;
	}
	public boolean isGenerallyTruncating() {
		return isGenerallyTruncating;
	}
	public void setGenerallyTruncating(boolean isGenerallyTruncating) {
		this.isGenerallyTruncating = isGenerallyTruncating;
	}
	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}
	

}

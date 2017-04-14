////////////////////////////////////////////////////////////////
//	Author : Abel Yitayew
//  Email: abel.yitayew@gmail.com
//  LinkedIn: https://www.linkedin.com/in/abel-yitayew/
////////////////////////////////////////////////////////////////

package com.gene.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Gene extends ResourceSupport {
	
	@Id
	private Integer entrezGeneId;
	private String hugoSymbol;
	
	@ElementCollection
	private List<String> aliases =new ArrayList<>();
	private boolean oncoGene;
	private boolean tsg;
	
	@JsonIgnore
	@OneToMany(mappedBy="gene",cascade=CascadeType.ALL)
	private List<Variant> variants=new ArrayList<>();
	
	
	
	public Gene() {
	}


	public String getHugoSymbol() {
		return hugoSymbol;
	}

	public void setHugoSymbol(String hugoSymbol) {
		this.hugoSymbol = hugoSymbol;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void addAliases(String alias) {
		this.aliases.add(alias);
	}

	public Integer getEntrezGeneId() {
		return entrezGeneId;
	}

	public void setEntrezGeneId(Integer entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public boolean isOncoGene() {
		return oncoGene;
	}

	public void setOncoGene(boolean oncoGene) {
		this.oncoGene = oncoGene;
	}

	public boolean isTsg() {
		return tsg;
	}

	public void setTsg(boolean tsg) {
		this.tsg = tsg;
	}

	public List<Variant> getVariants() {
		return variants;
	}

	public void addVariant(Variant variant) {
		variant.setGene(this);
		this.variants.add(variant);
	}
	
}

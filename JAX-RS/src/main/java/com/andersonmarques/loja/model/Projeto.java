package com.andersonmarques.loja.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

//Serializa em XML com JAXB
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

	private Long id;
	private String nome;
	private int anoInicio;

	public Projeto() {}
	
	public Projeto(Long id, String nome, int anoInicio) {
		this.id = id;
		this.nome = nome;
		this.anoInicio = anoInicio;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public int getAnoInicio() {
		return anoInicio;
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJSON() {
		return new Gson().toJson(this);
	}
}

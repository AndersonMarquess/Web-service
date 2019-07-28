package com.andersonmarques.estoquews.models.exceptions;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

// Necessário para acessar os campos(atributos) privados.
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class InformacaoFault {
	private Date dataErro;
	private String mensagemErro;

	// JAX-B lança DatabindingException se não houver um construtor vazio.
	public InformacaoFault() {
	}

	public InformacaoFault(String mensagemErro) {
		this(new Date(), mensagemErro);
	}

	public InformacaoFault(Date dataErro, String mensagemErro) {
		this.dataErro = dataErro;
		this.mensagemErro = mensagemErro;
	}

}
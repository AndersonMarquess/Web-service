package com.andersonmarques.estoquews.models.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "AutenticacaoFault")
public class AutenticacaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public AutenticacaoException(String mensagem) {
		super(mensagem);
	}

	// Retorna as informações no details da resposta.
	public InformacaoFault getFaultInfo() {
		return new InformacaoFault("Token inválido");
	}
}
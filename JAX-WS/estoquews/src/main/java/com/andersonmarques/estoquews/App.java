package com.andersonmarques.estoquews;

import javax.xml.ws.Endpoint;

import com.andersonmarques.estoquews.ws.EstoqueWS;

public class App {
	/*
	 * Simple Object Access Protocol (SOAP), espera receber request do tipo POST.
	 *
	 * Estrutura do XML de uma mensagem SOAP:
	 * Envelope, Header (Opcional), Body e fecha envelope.
	 * 
	 * Descrição do serviço: 
	 * http://localhost:8080/estoquews?wsdl
	 */
	public static void main(String[] args) throws Exception {
		publicarServicoEstoque();

		System.out.println("passou pela main");
	}

	private static void publicarServicoEstoque() {
		EstoqueWS estoqueService = new EstoqueWS();
		String url = "http://localhost:8080/estoquews";
		
		Endpoint.publish(url, estoqueService);
	}
}

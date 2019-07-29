package com.andersonmarques.estoquews;

import javax.xml.ws.Endpoint;

import com.andersonmarques.estoquews.ws.EstoqueWS;
import com.andersonmarques.estoquews.ws.RelatorioService;

public class App {
	/*
	 * Simple Object Access Protocol (SOAP), espera receber request do tipo POST.
	 *
	 * Estrutura do XML de uma mensagem SOAP: Envelope, Header (Opcional), Body e
	 * fecha envelope.
	 * 
	 * Descrição do serviço: http://localhost:8080/estoquews?wsdl
	 * Detalhes da definição do xml: http://localhost:8080/estoquews?xsd
	 */
	public static void main(String[] args) {
		publicarServicoEstoque();
		publicarServicoRelatorio();

		System.out.println("passou pela main");
	}

	private static void publicarServicoRelatorio() {
		RelatorioService relatorio = new RelatorioService();
		String url = "http://localhost:8080/relatorio";

		Endpoint.publish(url, relatorio);
	}

	private static void publicarServicoEstoque() {
		EstoqueWS estoqueService = new EstoqueWS();
		String url = "http://localhost:8080/estoquews";

		Endpoint.publish(url, estoqueService);
	}
}

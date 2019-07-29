package com.andersonmarques.estoquews.ws;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class RelatorioService {

	// Oneway -> Método que não retorna nenhuma resposta para o cliente
	@Oneway
	@WebMethod(operationName = "gerarRelatorioSemRetorno")
	public void gerarRelatorioSemRetorno(@WebParam(name = "info") String info) {
		System.out.println("Recebeu: " + info);
	}
}

package com.andersonmarques.estoquews.cliente.web;

import com.andersonmarques.estoquews.cliente.EstoqueWS;
import com.andersonmarques.estoquews.cliente.EstoqueWS_Service;
import com.andersonmarques.estoquews.cliente.Filtro;
import com.andersonmarques.estoquews.cliente.Filtros;

public class TesteServicoWeb {

	/*
	 * Cliente criado com wsimport:
	 * wsimport -s src -p com.andersonmarques.estoquews.cliente http://localhost:8080/estoque-web/EstoqueWS?wsdl
	 */
	public static void main(String[] args) {
		EstoqueWS estoqueWS = new EstoqueWS_Service().getEstoqueWSImplPort();
		
		Filtro filtro = new Filtro();
		filtro.setTipo("CELULAR");
		Filtros filtros = new Filtros();
		filtros.getFiltro().add(filtro);

		System.out.println(estoqueWS.todosOsItens(filtros));
	}

}

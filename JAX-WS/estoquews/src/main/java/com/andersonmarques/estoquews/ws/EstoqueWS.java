package com.andersonmarques.estoquews.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.andersonmarques.estoquews.models.item.ItemDao;
import com.andersonmarques.estoquews.models.item.ListaItens;

/* Anotação para criar o serviço web */
@WebService
public class EstoqueWS {

	private ItemDao itemDAO = new ItemDao();

	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens() {
		System.out.println("Buscando todos os itens");
		return new ListaItens(this.itemDAO.todosItens());
	}
}
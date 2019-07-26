package com.andersonmarques.estoquews.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.andersonmarques.estoquews.models.item.Filtro;
import com.andersonmarques.estoquews.models.item.Filtros;
import com.andersonmarques.estoquews.models.item.Item;
import com.andersonmarques.estoquews.models.item.ItemDao;

/* Anotação para criar o serviço web */
@WebService
public class EstoqueWS {

	private ItemDao itemDAO = new ItemDao();

	@WebMethod(operationName = "todosOsItens")
	@RequestWrapper(localName = "listaItens")
	// Involucro geral
	@ResponseWrapper(localName = "itens")
	// Involucro do elemento
	@WebResult(name = "item")
	public List<Item> getItens(@WebParam(name = "filtros") Filtros filtros) {
		System.out.println("Buscando todos os itens com filtro");

		List<Filtro> lista = filtros.getLista();
		ArrayList<Item> itensFiltrados = this.itemDAO.todosItens(lista);

		return itensFiltrados;
	}
}
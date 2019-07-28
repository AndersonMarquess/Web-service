package com.andersonmarques.estoquews.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.andersonmarques.estoquews.models.exceptions.AutenticacaoException;
import com.andersonmarques.estoquews.models.item.Filtro;
import com.andersonmarques.estoquews.models.item.Filtros;
import com.andersonmarques.estoquews.models.item.Item;
import com.andersonmarques.estoquews.models.item.ItemDao;
import com.andersonmarques.estoquews.models.item.ItemValidador;
import com.andersonmarques.estoquews.models.usuario.TokenDao;
import com.andersonmarques.estoquews.models.usuario.TokenUsuario;

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

	// Passa os dados do token no header do SOAP.
	@WebMethod(operationName = "cadastrarItem")
	@WebResult(name = "item")
	public Item cadastrarItem(@WebParam(name = "token", header = true) TokenUsuario token,
			@WebParam(name = "item") Item item) throws AutenticacaoException {
		System.out.println("Cadastrando item: " + item + " Token:" + token);
		
		boolean isTokenValid = new TokenDao().ehValido(token);
		if (!isTokenValid) {
			throw new AutenticacaoException("Erro de autenticação");
		}

		new ItemValidador(item).validate();

		this.itemDAO.cadastrar(item);
		return item;
	}
}
package com.andersonmarques.estoquews.ws;

import java.util.List;

import javax.jws.WebService;

import com.andersonmarques.estoquews.models.item.Item;
import com.andersonmarques.estoquews.models.item.ItemDao;

/* Anotação para criar o serviço web */
@WebService
public class EstoqueWS {
	
	private ItemDao itemDAO = new ItemDao();

	public List<Item> getItens() {
		System.out.println("Buscando todos os itens");
		return this.itemDAO.todosItens();
	}
}
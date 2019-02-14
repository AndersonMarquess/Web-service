package com.andersonmarques.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andersonmarques.loja.dao.CarrinhoDAO;
import com.andersonmarques.loja.model.Carrinho;

@Path("carrinho")
public class CarrinhoResource {
	
	@GET
	/*Informa que o método irá produzir um XML de retorno*/
	@Produces(MediaType.APPLICATION_XML)
	public String buscar() {
		Carrinho carrinho = new CarrinhoDAO().busca(1l);
		return carrinho.toXML();
	}
}

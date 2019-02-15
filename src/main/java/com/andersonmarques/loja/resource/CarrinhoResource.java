package com.andersonmarques.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andersonmarques.loja.dao.CarrinhoDAO;
import com.andersonmarques.loja.model.Carrinho;
import com.thoughtworks.xstream.XStream;

@Path("carrinho")
public class CarrinhoResource {
	
	@GET 
	//Informa que o método irá produzir um XML de retorno
	@Produces(MediaType.APPLICATION_XML)
	@Path("{id}") // carrinho/1
	public String buscar(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
	}
	
	@POST
    @Produces(MediaType.APPLICATION_XML)
    public String adicionar(String conteudo) {
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        new CarrinhoDAO().adiciona(carrinho);
        return "<status>sucesso</status>";
    }
}

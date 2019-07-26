package com.andersonmarques.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.andersonmarques.loja.dao.CarrinhoDAO;
import com.andersonmarques.loja.model.Carrinho;
import com.andersonmarques.loja.model.Produto;
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
	//Informa o tipo de dado aceito
	@Consumes(MediaType.APPLICATION_XML)
    public Response adicionar(String conteudo) {
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        new CarrinhoDAO().adiciona(carrinho);
        
        /*URI que será retornada*/
        URI uri = URI.create("/carrinho/" + carrinho.getId());
        
        /*possui o código de resposta HTTP*/
        return Response.created(uri).build();
    }
	
	@Path("{id}/produto/{produtoId}")
	@DELETE
	public Response removerProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		
		return Response.ok().build();
	}
	
	@Path("{id}/produto/{produtoId}/quantidade")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response alterarProduto(String conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = (Produto) new XStream().fromXML(conteudo);
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
		
	}
}

package com.andersonmarques.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andersonmarques.loja.dao.ProjetoDAO;
import com.andersonmarques.loja.model.Projeto;
import com.thoughtworks.xstream.XStream;

@Path("projeto")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscar(@PathParam("id") long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toXML();
	}
	
	@POST
    @Produces(MediaType.APPLICATION_XML)
    public String adicionar(String conteudo) {
        Projeto carrinho = (Projeto) new XStream().fromXML(conteudo);
        new ProjetoDAO().adiciona(carrinho);
        return "<status>sucesso</status>";
    }
}

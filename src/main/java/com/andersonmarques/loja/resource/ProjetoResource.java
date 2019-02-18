package com.andersonmarques.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Consumes(MediaType.APPLICATION_XML)
    public Response adicionar(String conteudo) {
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        new ProjetoDAO().adiciona(projeto);
        
        URI uri = URI.create("/projeto/"+projeto.getId());
		return Response.created(uri).build();
    }
}

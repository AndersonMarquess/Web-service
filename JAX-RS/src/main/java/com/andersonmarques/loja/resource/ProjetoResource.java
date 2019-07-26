package com.andersonmarques.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.andersonmarques.loja.dao.ProjetoDAO;
import com.andersonmarques.loja.model.Projeto;

@Path("projeto")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Projeto buscar(@PathParam("id") long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adicionar(Projeto projeto) {
        new ProjetoDAO().adiciona(projeto);
        
        URI uri = URI.create("/projeto/"+projeto.getId());
		return Response.created(uri).build();
    }
	
	@Path("{id}")
	@DELETE
	public Response remover(@PathParam("id") long id) {
		new ProjetoDAO().remove(id);
		return Response.ok().build();
	}
}

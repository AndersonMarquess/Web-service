package com.andersonmarques.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andersonmarques.loja.dao.ProjetoDAO;
import com.andersonmarques.loja.model.Projeto;

@Path("projeto")
public class ProjetoResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String buscar() {
		Projeto projeto = new ProjetoDAO().busca(1l);
		return projeto.toXML();
	}
}

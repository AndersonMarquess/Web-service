package com.andersonmarques.loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andersonmarques.loja.model.Projeto;
import com.thoughtworks.xstream.XStream;

public class ProjetoTest {

	private HttpServer servidor;
	private Client cliente;
	private WebTarget alvoRaiz;

	@Before
	public void subirServidorProjeto() {
		servidor = ServidorMain.subirServidor("8081");
		cliente = ClientBuilder.newClient();
		alvoRaiz = cliente.target("http://localhost:8081");
	}

	@After
	public void desligarServidorProjeto() {
		if (servidor.isStarted())
			servidor.shutdown();
	}

	@Test
	public void recuperaProjetoComSucesso() {
		String resultado = alvoRaiz.path("/projeto/1").request().get(String.class);
		Projeto projeto = (Projeto) new XStream().fromXML(resultado);
		assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void adicionarProjetoComSucesso() {	
		Projeto projeto = new Projeto(3l, "Estudo JAX-RS", 2019);
		String xml = new XStream().toXML(projeto);
		Entity<String> entityXML = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response resposta = alvoRaiz.path("/projeto").request().post(entityXML);
		assertEquals(201, resposta.getStatus());
		
		String enderecoResposta = resposta.getHeaderString("Location");
		String conteudoRequest = cliente.target(enderecoResposta).request().get(String.class);
		assertTrue(conteudoRequest.contains(projeto.getNome()));
	}
}

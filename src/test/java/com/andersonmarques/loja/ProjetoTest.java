package com.andersonmarques.loja;

import static org.junit.Assert.assertEquals;

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

	@Before
	public void subirServidorProjeto() {
		servidor = ServidorMain.subirServidor("8081");
	}

	@After
	public void desligarServidorProjeto() {
		if (servidor.isStarted())
			servidor.shutdown();
	}

	@Test
	public void recuperaProjetoComSucesso() {
		Client cliente = ClientBuilder.newClient();
		WebTarget alvoRaiz = cliente.target("http://localhost:8081");
		String resultado = alvoRaiz.path("/projeto/1").request().get(String.class);
		Projeto projeto = (Projeto) new XStream().fromXML(resultado);
		assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void adicionarProjetoComSucesso() {
		Client cliente = ClientBuilder.newClient();
		WebTarget alvoRaiz = cliente.target("http://localhost:8081");
		
		Projeto projeto = new Projeto(3l, "Estudo JAX-RS", 2019);
		String xml = new XStream().toXML(projeto);
		Entity<String> entityXML = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response resposta = alvoRaiz.path("/projeto").request().post(entityXML);
		
		assertEquals("<status>sucesso</status>", resposta.readEntity(String.class));
	}
}

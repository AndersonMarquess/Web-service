package com.andersonmarques.loja;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteTeste {

	private HttpServer servidor;

	@Before
	public void subirServidor() {
		servidor = ServidorMain.subirServidor();
	}

	@After
	public void desligarServidor() {
		if (servidor.isStarted())
			servidor.shutdown();
	}

	@Test
	public void testaConexaoComSucessoAteServidor() {
		Client client = ClientBuilder.newClient();
		WebTarget targetRaiz = client.target("http://localhost:8080");

		/* Faz uma requisição get e retorna uma string */
		String resultado = targetRaiz.path("/carrinho").request().get(String.class);
		assertTrue(resultado.contains("<rua>Rua Vergueiro 3185"));
	}
}

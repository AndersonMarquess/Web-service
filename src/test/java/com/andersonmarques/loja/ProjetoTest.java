package com.andersonmarques.loja;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class ProjetoTest {

	@Test
	public void recuperaProjetoComSucesso() {
		Client cliente = ClientBuilder.newClient();
		WebTarget alvoRaiz = cliente.target("http://localhost:8080");
		String resultado = alvoRaiz.path("/projeto").request().get(String.class);
		assertTrue(resultado.contains("Minha loja"));
	}
}

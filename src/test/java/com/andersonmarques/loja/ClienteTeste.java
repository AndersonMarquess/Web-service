package com.andersonmarques.loja;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class ClienteTeste {
	
	@Test
	public void testaConexaoComSucessoAteServidor() {
		Client client = ClientBuilder.newClient();
		WebTarget targetRaiz = client.target("http://www.mocky.io");

		/*Faz uma requisição get e retorna uma string*/
		String resultado = targetRaiz.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		assertTrue(resultado.contains("<rua>Rua Vergueiro 3185"));
	}
}

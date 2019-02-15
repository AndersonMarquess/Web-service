package com.andersonmarques.loja;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andersonmarques.loja.model.Carrinho;
import com.thoughtworks.xstream.XStream;

public class ClienteTeste {

	private HttpServer servidor;

	@Before
	public void subirServidor() {
		servidor = ServidorMain.subirServidor("8081");
	}

	@After
	public void desligarServidor() {
		if (servidor.isStarted())
			servidor.shutdown();
	}

	@Test
	public void lancaExecptionAoAcessarURLInexistente() {
		assertThrows(NotFoundException.class, () -> {
			Client client = ClientBuilder.newClient();
				client.target("http://localhost:8081")
					  .path("/carrinho")
					  .request().get(String.class);
		});
	}
	
	@Test
	public void recuperarCarrinhoPadraoNoServidorComSucesso() {
		Client client = ClientBuilder.newClient();
		WebTarget targetRaiz = client.target("http://localhost:8081");

		/* Faz uma requisição get e retorna uma string */
		String resultado = targetRaiz.path("/carrinho/1").request().get(String.class);
		Carrinho carrinho = (Carrinho)new XStream().fromXML(resultado);
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
}

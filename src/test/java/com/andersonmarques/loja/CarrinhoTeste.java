package com.andersonmarques.loja;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.NotFoundException;
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

import com.andersonmarques.loja.model.Carrinho;
import com.andersonmarques.loja.model.Produto;
import com.thoughtworks.xstream.XStream;

public class CarrinhoTeste {

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
					  .path("/urlInexistente")
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
	
	@Test
	public void adicionarCarrinhoComSucesso() {
		Client cliente = ClientBuilder.newClient();
		Carrinho carrinho = new Carrinho();
	 	carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();
        
        //Transforma a string em uma entity para fazer o post
        Entity<String> entityXML = Entity.entity(xml, MediaType.APPLICATION_XML);
		WebTarget target = cliente.target("http://localhost:8081");
		Response resposta = target.path("/carrinho").request().post(entityXML);
		
		//Transforma o conteúdo da resposta em String
		assertEquals("<status>sucesso</status>", resposta.readEntity(String.class));
	}
}

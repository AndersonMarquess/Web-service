package com.andersonmarques.loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
	private Client client;
	private WebTarget targetRaiz;

	@Before
	public void subirServidor() {
		servidor = ServidorMain.subirServidor("8081");
		client = ClientBuilder.newClient(ServidorMain.configurarClient(this.getClass().getName()));
		targetRaiz = client.target("http://localhost:8081");;
	}

	@After
	public void desligarServidor() {
		if (servidor.isStarted())
			servidor.shutdown();
	}

	@Test
	public void lancaExecptionAoAcessarURLInexistente() {
		assertThrows(NotFoundException.class, () -> {
				targetRaiz.path("/urlInexistente").request().get(String.class);
		});
	}
	
	@Test
	public void recuperarCarrinhoPadraoNoServidorComSucesso() {
		/* Faz uma requisição get e retorna uma string */
		String resultado = targetRaiz.path("/carrinho/1").request().get(String.class);
		Carrinho carrinho = (Carrinho)new XStream().fromXML(resultado);
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
	
	@Test
	public void adicionarCarrinhoRecebeStatusCode201FazGETNoEnderecoResposta() {
		Carrinho carrinho = new Carrinho();
	 	carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");

        //Transforma a string em uma entity para fazer o post
        Entity<String> entityXML = Entity.entity(carrinho.toXML(), MediaType.APPLICATION_XML);
        
        Response respostaPost = targetRaiz.path("/carrinho").request().post(entityXML);
        
        //Verifica o status code do request
        assertEquals(201, respostaPost.getStatus());
        
        //Pega o endereço de resposta
        String enderecoResposta = respostaPost.getHeaderString("Location");
        String conteudoResposta = client.target(enderecoResposta).request().get(String.class);
        assertTrue(conteudoResposta.contains("Tablet"));
	}
	
	@Test
	public void removerItemDoCarrinhoComStatusCode200() {
		String carrinhoResposta = targetRaiz.path("/carrinho/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(carrinhoResposta);
		
		long idProduto = carrinho.getProdutos().stream().findFirst().get().getId();
		
		Response resposta = targetRaiz.path("/carrinho/1/produto/" + idProduto).request().delete();
		assertEquals(200, resposta.getStatus());
	}
	
	@Test
	public void atualizarRecursoComStatusCode200() {
		String conteudoResposota = targetRaiz.path("/carrinho/1/").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudoResposota);
		
		Produto produto = carrinho.getProdutos().stream().filter(p -> p.getQuantidade() >= 2).findFirst().get();
		produto.setQuantidade(1);
		
		String produtoAtualizadoXML = new XStream().toXML(produto);
		Entity<String> entity = Entity.entity(produtoAtualizadoXML, MediaType.APPLICATION_XML);
		Response resposta = targetRaiz.path("/carrinho/1/produto/" + produto.getId()+"/quantidade").request().put(entity);
		
		assertEquals(200, resposta.getStatus());
	}
}






















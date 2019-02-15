package com.andersonmarques.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServidorMain {
	
	public static void main(String[] args) throws IOException {
		HttpServer server = subirServidor();
		System.out.println("Servidor rodando - aperte qualquer tecla para desligar.");
		System.in.read();
		server.shutdown();
	}

	public static HttpServer subirServidor() {
		URI uriRaiz = URI.create("http://localhost:8080/");
		/*Configura o grizzly para buscar os recursos como jax-rs e o utiliza como serviço*/
		ResourceConfig config = new ResourceConfig().packages("com.andersonmarques.loja");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uriRaiz, config);
		return server;
	}
}

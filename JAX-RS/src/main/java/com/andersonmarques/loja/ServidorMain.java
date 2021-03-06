package com.andersonmarques.loja;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;
import org.glassfish.jersey.server.ResourceConfig;

public class ServidorMain {
	
	public static void main(String[] args) throws IOException {
		HttpServer server = subirServidor("8080");
		System.out.println("Servidor rodando - aperte qualquer tecla para desligar.");
		System.in.read();
		server.shutdown();
	}

	/**
	 * @param porta na qual o servidor será iniciado. Ex: 8080
	 * @return
	 */
	public static HttpServer subirServidor(String porta) {
		URI uriRaiz = URI.create("http://localhost:"+porta+"/");
		/*Configura o grizzly para buscar os recursos como jax-rs e, o utiliza como serviço*/
		ResourceConfig config = new ResourceConfig().packages("com.andersonmarques.loja");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uriRaiz, config);
		return server;
	}
	
	/**
	 * @author Julio Cesar
	 * @return {ClientConfig}
	 */
	public static ClientConfig configurarClient(String nomeClasse) {
		ClientConfig clientConfig = new ClientConfig();
		Logger logger = Logger.getLogger(nomeClasse);
		LoggingFeature loggingFeature = new LoggingFeature(logger, Level.INFO, Verbosity.PAYLOAD_ANY, null);
		clientConfig.register(loggingFeature);
		return clientConfig;
	}
}

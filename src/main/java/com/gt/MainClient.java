package com.gt;


import com.gt.server.WebSocketClient;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

@ClientEndpoint
public class MainClient {

	private static CountDownLatch latch;
	private static final String URI_PATH = "ws://localhost:8080/ws/server";
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		latch = new CountDownLatch(1);
		ClientManager client = ClientManager.createClient();
		try {
			client.connectToServer(WebSocketClient.class, new URI(URI_PATH));
			latch.await();

		} catch (DeploymentException | URISyntaxException | InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Interrupted while processing", e);
		}

	}

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
		try {
			session.getBasicRemote().sendText("start");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			logger.info("Received ...." + message);
			return bufferRead.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
		latch.countDown();
	}
}

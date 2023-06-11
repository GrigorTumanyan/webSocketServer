package com.gt.server;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/server")
public class WebSocketServer {

	private static final Logger logger = Logger.getLogger(WebSocketServer.class.getName());
	private static final Random random = new Random();
	private static final Set<String> connectedIPs = new HashSet<>();

	@OnOpen
	public void onOpen(Session session) throws IOException {
		String ipAddress = session.getRequestParameterMap().get("IP").get(0);
		if (!connectedIPs.contains(ipAddress)) {
			connectedIPs.add(ipAddress);
			logger.info(String.format("New connection from IP: %s", ipAddress));
		} else {
			session.close();
			logger.warning(String.format("Duplicate connection attempt from IP: %s. Connection closed.", ipAddress));
		}
	}

	@OnClose
	public void onClose(Session session) {
		String ipAddress = session.getRequestParameterMap().get("IP").get(0);
		connectedIPs.remove(ipAddress);
		logger.info(String.format("Connection closed from IP: %s", ipAddress));
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		session.getBasicRemote().sendText(generateResponse(message));
		logger.info(String.format("Response sent to IP: %s", session.getRequestParameterMap().get("IP").get(0)));
	}

	private String generateResponse(String requestMessage) {
		String uniqueNumber = " \"unique number\": " + generateUniqueNumber() + "}";
		String jsonRequest = "\n {\"requestMessage\": " + requestMessage + ",";
		return jsonRequest.concat("\n" + uniqueNumber);
	}

	private BigInteger generateUniqueNumber() {
		return new BigInteger(128, random);
	}
}
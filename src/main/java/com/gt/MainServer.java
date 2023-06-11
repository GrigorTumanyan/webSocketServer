package com.gt;

import com.gt.server.WebSocketServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import org.glassfish.tyrus.server.Server;

public class MainServer {

	private static final Logger LOGGER = Logger.getLogger(MainServer.class.getName());

	public static void main(String[] args) {
		runServer();
	}

	public static void runServer() {
		Server server = new Server("localhost", 8080, "/ws", WebSocketServer.class);
		try {
			server.start();
			LOGGER.info("Server was running");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please press a key to stop the server.");
			reader.readLine();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			server.stop();
		}
	}
}

# WebSocket Server and Client

This repository contains a WebSocket server and client implementation using Java. The server allows clients to connect
and exchange messages over a WebSocket connection. The client can send requests to the server and receive responses.

## Features

- Establishes a WebSocket server that listens on the **localhost** address and port **8080**.
- Accepts incoming connections from clients and handles their requests.
- Generates a unique number as a response to client requests.
- Supports multiple client connections simultaneously.
- Provides a WebSocket client that can connect to the server and send/receive messages.
- Demonstrates basic usage of the Tyrus WebSocket library.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven (for building and managing dependencies)

## Usage

1. Clone the repository:

| git clone | https://github.com/GrigorTumanyan/webSocketServer.git |
|-----------|-------------------------------------------|

2. Navigate to the project directory:

   <span style="color: red;">cd project-directory</span>


3. Build the project using Maven:

   <span style="color: red;">mvn clean package</span>


4. Run the WebSocket server:

   <span style="color: red;">java -cp target/classes com.gt.MainServer
   </span>


5. In a separate terminal, run the WebSocket client:

   <span style="color: red;">java -cp target/classes com.gt.MainClient
   </span>


6. Interact with the client by entering text messages in the client terminal. The client will send the messages to the server, which will respond with a generated unique number.

7. To stop the server, press any key in the server terminal.


## Libraries Used
- **org.glassfish.tyrus.server.Server:** Used to create and start the WebSocket server.
- **org.glassfish.tyrus.client.ClientManager:** Used to create and manage WebSocket client connections.


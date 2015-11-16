package websockets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.DeploymentException;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.tyrus.client.ClientManager;
import websockets.web.ElizaClientEndpoint;

public class ElizaClient {
	private static final Logger LOGGER = Grizzly.logger(ElizaClient.class);
	public static CountDownLatch LATCH = new CountDownLatch(1);

	public static void main(String[] args) {
		runClient();
	}

	public static void runClient() {
		ClientManager client = ClientManager.createClient();
		try {
			client.connectToServer(ElizaClientEndpoint.class, new URI("ws://localhost:8025/websockets/eliza"));
			LATCH.await();
		} catch (DeploymentException | IOException | URISyntaxException | InterruptedException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
}

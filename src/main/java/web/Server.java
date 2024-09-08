package web;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {


	public Server() {
		

		install(new GsonModule());

		mount(new StaticAssetModule());
	}

	public static void main(String[] args) {
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}

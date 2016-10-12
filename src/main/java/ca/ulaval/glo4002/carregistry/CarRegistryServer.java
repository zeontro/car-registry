package ca.ulaval.glo4002.carregistry;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;
import ca.ulaval.glo4002.carregistry.persistence.InMemoryCarRegistry;

public class CarRegistryServer {
	  public static void main(String[] args) {
	        new CarRegistryServer().run();
	    }
	 
	    public void run() {
	    	prefillDatabase();
	        startServer();
	    }

		private void prefillDatabase() {
			CarRegistry carRegistry = new InMemoryCarRegistry();
			carRegistry.insert(new CarOwner("John Doe"));
			carRegistry.insert(new CarOwner("Jane Doe"));
		}

		private void startServer() {
			int httpPort = 9595;
	 
	        Server server = new Server(httpPort);
	        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
	        configurerJersey(servletContextHandler);
			try {
				server.start();
				server.join();
			} catch (Exception e) {
				e.printStackTrace(); // Une des rare fois qu'on peut!
			} finally {
				server.destroy();
			}
		}
	 
	    private void configurerJersey(ServletContextHandler servletContextHandler) {
	        ServletContainer container = new ServletContainer(new ResourceConfig().packages("ca.ulaval.glo4002.carregistry.rest"));
	        ServletHolder jerseyServletHolder = new ServletHolder(container);
	        servletContextHandler.addServlet(jerseyServletHolder, "/*");
	    }
}

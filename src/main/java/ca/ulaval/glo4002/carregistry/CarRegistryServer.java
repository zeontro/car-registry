package ca.ulaval.glo4002.carregistry;

import java.util.EnumSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;
import ca.ulaval.glo4002.carregistry.persistence.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.carregistry.persistence.EntityManagerProvider;
import ca.ulaval.glo4002.carregistry.persistence.HibernateCarRegistry;
import ca.ulaval.glo4002.carregistry.rest.filters.EntityManagerContextFilter;

public class CarRegistryServer {
	public static void main(String[] args) {
		new CarRegistryServer().run();
	}

	public void run() {
		prefillDatabase();
		startServer();
	}

	private void prefillDatabase() {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManagerProvider.setEntityManager(entityManager);

		CarRegistry carRegistry = new HibernateCarRegistry();
		carRegistry.insert(new CarOwner("John Doe"));
		carRegistry.insert(new CarOwner("Jane Doe"));

		EntityManagerProvider.clearEntityManager();
		entityManager.close();
	}

	private void startServer() {
		int httpPort = 9595;

		Server server = new Server(httpPort);
		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
		servletContextHandler.addFilter(EntityManagerContextFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
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
		ServletContainer container = new ServletContainer(
				new ResourceConfig().packages("ca.ulaval.glo4002.carregistry.rest"));
		ServletHolder jerseyServletHolder = new ServletHolder(container);
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
	}
}

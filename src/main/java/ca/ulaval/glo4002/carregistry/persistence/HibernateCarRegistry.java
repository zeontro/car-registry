package ca.ulaval.glo4002.carregistry.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;

public class HibernateCarRegistry implements CarRegistry {
	
	private EntityManager entityManager;

	public HibernateCarRegistry() {
		this.entityManager = new EntityManagerProvider().getEntityManager();
	}

	@Override
	public CarOwner findOwner(int ownerId) {
		return entityManager.find(CarOwner.class, ownerId);
	}

	@Override
	public void insert(CarOwner owner) {
		entityManager.getTransaction().begin();
		entityManager.persist(owner);
		entityManager.getTransaction().commit(); // in try/finally ideally
	}

	@Override
	public void update(CarOwner owner) {
		entityManager.getTransaction().begin();
		entityManager.persist(owner);
		entityManager.getTransaction().commit(); // in try/finally ideally
	}

	@Override
	public Collection<CarOwner> findAllOwners() {
		return entityManager.createQuery("select o from CarOwner o", CarOwner.class).getResultList();
	}

}

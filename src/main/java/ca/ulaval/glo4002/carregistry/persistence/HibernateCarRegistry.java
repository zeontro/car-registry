package ca.ulaval.glo4002.carregistry.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;

public class HibernateCarRegistry implements CarRegistry {
	private EntityManager entityManager;
	private Map<Integer, CarOwner> owners = new HashMap<>();

	public HibernateCarRegistry() {
		this.setEntityManager(new EntityManagerProvider().getEntityManager());
	}

	@Override
	public CarOwner findOwner(int ownerId) {
		return entityManager.find(null, ownerId);
	}

	@Override
	public void insert(CarOwner owner) {
		entityManager.persist(owner);
	}

	@Override
	public void update(CarOwner owner) {
		if (!entityManager.contains(owner)) {
			this.insert(owner);
		}
	}

	@Override
	public Collection<CarOwner> findAllOwners() {

		return owners.values();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

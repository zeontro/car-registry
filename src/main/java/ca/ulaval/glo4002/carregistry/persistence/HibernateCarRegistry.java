package ca.ulaval.glo4002.carregistry.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;

public class HibernateCarRegistry implements CarRegistry {
	private EntityManager entityManager;

	public HibernateCarRegistry() {
		this.setEntityManager(new EntityManagerProvider().getEntityManager());
	}

	@Override
	public CarOwner findOwner(int ownerId) {
		entityManager.find(null, ownerId);
		return null;
	}

	@Override
	public void insert(CarOwner owner) {

	}

	@Override
	public void update(CarOwner owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CarOwner> findAllOwners() {
		// TODO Auto-generated method stub
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

package ca.ulaval.glo4002.carregistry.domain;

import java.util.Collection;

public interface CarRegistry {

	CarOwner findOwner(int ownerId);

	void insert(CarOwner owner);

	void update(CarOwner owner);

	Collection<CarOwner> findAllOwners();

}

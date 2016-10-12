package ca.ulaval.glo4002.carregistry.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ca.ulaval.glo4002.carregistry.domain.Car;
import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;

public class InMemoryCarRegistry implements CarRegistry {
	private static final int MAX_ID = 9999;
	
	private static Random idGenerator = new Random();
	private static Map<Integer, CarOwner> owners = new HashMap<>();

	public CarOwner findOwner(int ownerId) {
		return owners.get(ownerId);
	}
	
	public void insert(CarOwner owner) {
		int id = idGenerator.nextInt(MAX_ID);
		owner.setId(id);
		owners.put(id, owner);
	}

	public void update(CarOwner owner) {
		for(Car car : owner.getCars()) {
			if(car.getId() == 0) {
				car.setId(idGenerator.nextInt(MAX_ID));
			}
		}
		
		owners.put(owner.getId(), owner);
	}

	@Override
	public Collection<CarOwner> findAllOwners() {
		return owners.values();
	}

}

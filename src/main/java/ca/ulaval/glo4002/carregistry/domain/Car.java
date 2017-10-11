package ca.ulaval.glo4002.carregistry.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Car")
public class Car {
	@Id
	private int id;
	private String plate;

	public Car(String plate) {
		this.plate = plate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

}

package ca.ulaval.glo4002.carregistry.domain;

public class CarFactory {

	public Car createNewCar(String carPlate) {
		return new Car(carPlate);
	}

}

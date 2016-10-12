package ca.ulaval.glo4002.carregistry.services;

import java.util.stream.Collectors;

import ca.ulaval.glo4002.carregistry.domain.Car;
import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.services.dto.CarDto;
import ca.ulaval.glo4002.carregistry.services.dto.CarOwnerDto;

public class CarOwnerAssember {

	public CarOwnerDto toDto(CarOwner carOwner) {
		CarOwnerDto carOwnerDto = new CarOwnerDto();
		carOwnerDto.id = carOwner.getId();
		carOwnerDto.name = carOwner.getName();
		carOwnerDto.cars = carOwner.getCars().stream().map(this::toDto).collect(Collectors.toList());
		return carOwnerDto;
	}

	public CarDto toDto(Car car) {
		CarDto carDto = new CarDto();
		carDto.id = car.getId();
		carDto.plate = car.getPlate();
		return carDto;
	}

}

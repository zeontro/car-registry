package ca.ulaval.glo4002.carregistry.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class CarOwner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@OneToMany
	@JoinColumn(name = "owner")
	@Cascade(value={CascadeType.ALL})
	private List<Car> cars = new LinkedList<>();
	
	public CarOwner() {
		// for hibernate
	}
	
	public CarOwner(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Car> getCars() {
		return cars;
	}

	public void addCar(Car car) {
		cars.add(car);
	}
	
	public String getName() {
		return name;
	}

}

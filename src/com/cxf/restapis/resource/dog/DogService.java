package com.cxf.restapis.resource.dog;

public class DogService {

	public DogModel getDog(Long id) {
		return new DogDAO().getDog(id);
	}
	
	public DogModel updateDog(DogModel dog) {
		return new DogDAO().updateDog(dog);
	}
}

package com.cxf.restapis.resource.dog;

public class DogDAO {
	
	public DogModel getDog(Long id) {
		System.out.println("get dog " + id);
		DogModel dog = new DogModel();
		dog.setDogNumber(id);
		dog.setDogName("MyDog");
		dog.setNickName("MyDogNickName");
		dog.setDogAge(2);
		return dog;
	}
	
	public DogModel updateDog(DogModel dog) {
		dog.setDogName("updatedDog");
		return dog;
	}
	
	
	

}

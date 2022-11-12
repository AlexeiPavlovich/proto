package com.example.protobuf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

import com.example.models.Address;
import com.example.models.Car;
import com.example.models.CarBodyStyle;
import com.example.models.Credentials;
import com.example.models.Dealer;
import com.example.models.EmailCredentials;
import com.example.models.Person;
import com.example.models.Television;
import com.google.protobuf.Int32Value;

public class PersonDemo {
	public static void main(String[] args) throws IOException {
		Person sam = Person.newBuilder().setName("Sam").setAge(Int32Value.newBuilder().setValue(10).build()).build();
		System.out.println(sam.toString());

		Path path = Path.of("sam.ser");
		Files.write(path, sam.toByteArray());

		byte[] bytes = Files.readAllBytes(path);

		Person newSam = Person.parseFrom(bytes);

		System.out.println(newSam.toString());

		Address address = Address.newBuilder().setPostbox(123).setStreet("Brener").setCity("Netanya").build();
		Car kia = Car.newBuilder().setModel("Kia").setYear(2022).setModel("Sporatge").setBodyStyle(CarBodyStyle.SUV).build();
		Car shevroltet = Car.newBuilder().setModel("Shevrolet").setYear(2017).setBodyStyle(CarBodyStyle.SUV).setModel("Trax").build();

		Person person = Person.newBuilder().setName("Alexei").setAge(Int32Value.newBuilder().setValue(43).build()).setAddress(address).addAllCar(Arrays.asList(kia,shevroltet)).build();
		System.out.println(person);
		
		Dealer dealer =Dealer.newBuilder().putAllModel(Map.of(2022,kia,2017,shevroltet)).build();
		
		System.out.println(dealer);
		
		System.out.println(dealer.getModelOrThrow(2017).getBodyStyleValue());
		
		
		Person person1 = Person.newBuilder().build();
		System.out.println("City:"+person1.getAddress().getCity());
	
		
		EmailCredentials emailCredentials=EmailCredentials.newBuilder().setEmail("a@gmail.com").setPassword("123").build();
		Credentials credentials=Credentials.newBuilder().setEmailMode(emailCredentials).build();
		
		System.out.println(credentials);
		
		
		
		//Television television=Television.newBuilder().setBrand("Sony").setYear(2022).build();
		
		path = Path.of("tv-v1.ser");
		//Files.write(path, television.toByteArray());
		
		
		bytes = Files.readAllBytes(path);

		System.out.println(Television.parseFrom(bytes).toString());

	}
}

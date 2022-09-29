package com.example.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	@Override
	public Person process(Person item) throws Exception {
		final String firstName  = item.getFirstName().toUpperCase();
		final String lastName  = item.getLastName().toUpperCase();
		Person transformedPerson = new Person(firstName,lastName);
		return transformedPerson;
	}

}

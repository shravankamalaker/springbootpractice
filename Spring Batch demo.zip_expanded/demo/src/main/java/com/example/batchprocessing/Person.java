package com.example.batchprocessing;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class Person {
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
}

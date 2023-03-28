package com.kaliburr.crud;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

@Document(collection="students")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
	@Id
	private ObjectId id;
	public String framework;
	public String language;
	public String fullname;
	public Integer ID;
}

package com.kaliburr.crud;
import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepo extends MongoRepository<Student,ObjectId> {
	Optional<Student> findStudentByID(Integer ID);
	Optional<Student> deleteStudentByID(Integer ID);
	List<Student> findAllStudentByfullname(String name);
}

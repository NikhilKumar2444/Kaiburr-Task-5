package com.kaliburr.crud;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class StudentService {
	@Autowired
	private StudentRepo studentRepo;
	public List<Student> allStudents(){
		return studentRepo.findAll();
	}
	
	public Optional<Student> oneStudent(Integer ID) {
		return studentRepo.findStudentByID(ID);
	}
	
	public void delete(Integer ID){
		studentRepo.deleteStudentByID(ID);
	}
	public List<Student> allNameStu(String Name){
		return studentRepo.findAllStudentByfullname(Name);
	}
	public void updateStu(Student stu,Student student) {
	
		
	}
}

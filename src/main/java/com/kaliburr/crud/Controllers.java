package com.kaliburr.crud;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/students")
@CrossOrigin
public class Controllers {
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private StudentService studentService;
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(studentService.allStudents(),HttpStatus.OK);
	}
	@GetMapping("/{ID}")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable Integer ID){
		ResponseEntity<Optional<Student>> re1= new ResponseEntity<Optional<Student>>(studentService.oneStudent(ID),HttpStatus.OK);
		if(re1.getBody().isPresent()) {
			return re1;
		}
		return new ResponseEntity<Optional<Student>>(studentService.oneStudent(ID),HttpStatus.NOT_FOUND);
	}

	@PostMapping("/register")
	public ResponseEntity<Optional<Student>> createStudent(@RequestBody Student student){
		Integer chk=student.ID;
		String namee=student.fullname;
		String lang=student.language;
		String frameW=student.framework;
		if(chk==null || namee==null || lang==null || frameW==null){
			ResponseEntity<Optional<Student>> ts=new ResponseEntity<Optional<Student>>(studentService.oneStudent(chk),HttpStatus.METHOD_NOT_ALLOWED);
			return ts;
		}
		ResponseEntity<Optional<Student>> r1=new ResponseEntity<Optional<Student>>(studentService.oneStudent(chk),HttpStatus.OK);
		if(r1.getBody().isEmpty()) {
			Student save=this.studentRepo.save(student);
			ResponseEntity<Optional<Student>> r2=new ResponseEntity<Optional<Student>>(studentService.oneStudent(chk),HttpStatus.OK);
			return r2;
		}
		return new ResponseEntity<Optional<Student>>(studentService.oneStudent(chk),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/delete/{ID}")
	public ResponseEntity<Optional<Student>> deleteStu(@PathVariable Integer ID){
		ResponseEntity<Optional<Student>> ds=new ResponseEntity<Optional<Student>>(studentService.oneStudent(ID),HttpStatus.OK);
		if(ds.getBody().isPresent()) {
			studentService.delete(ID);
			return ds;
		}
		return new ResponseEntity<Optional<Student>>(studentService.oneStudent(ID),HttpStatus.NOT_FOUND);
	}
	@GetMapping("/name/{Name}")
	public ResponseEntity<List<Student>>getNameStu(@PathVariable String Name){
		ResponseEntity<List<Student>> ll1= new ResponseEntity<List<Student>>(studentService.allNameStu(Name),HttpStatus.OK);
		if(ll1.getBody().isEmpty()) {
			return new ResponseEntity<List<Student>>(studentService.allNameStu(Name),HttpStatus.NOT_FOUND);
		}
		return ll1;
	}
	@PutMapping("/edit/{ID}")
	public ResponseEntity<Student> editStudent(@PathVariable Integer ID,@RequestBody Student student){
		if(student.ID==null || student.framework==null || student.fullname==null || student.language==null||ID!=student.ID){
			return ResponseEntity.badRequest().build();
		}
		Optional<Student> OS=studentRepo.findStudentByID(ID);
		if(OS.isPresent()) {
			Student stu=OS.get();

			studentService.delete(ID);
			stu=student;
			this.studentRepo.save(stu);
			return ResponseEntity.ok(stu);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}

package com.example.jpqandnativesql;

import com.example.jpqandnativesql.models.Student;
import com.example.jpqandnativesql.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class JpqandnativesqlApplicationTests {

	@Autowired
	StudentRepository studentRepository;

	@Test
	void testSelectAll() {

		List<Student> students = studentRepository.findAllStudents(PageRequest.of(0,1, Sort.by(Sort.Direction.ASC, "id")));

		students.forEach(student -> System.out.println("Student name is ====> " + student.getId()));

	}

	@Test
	public void testFindPartialData() {

		List<Object[]> students = studentRepository.findAllStudentsPartialData();

		for(Object[] student : students) {
			System.out.println("Student fname is ====> " + student[0]);
			System.out.println("Student lname is ====> " + student[1]);
		}

	}

	@Test
	public void testFindAllStudentsByFirstName() {
		List<Student> students = studentRepository.findAllStudentsByFirstName("Rotariu");
		System.out.println(students);
		//students.forEach(student -> System.out.println("Student name is ====> " + student.getFirstName()));
	}

	@Test
	public void testFindAllStudntByPriceRange() {

		List<Student> students = studentRepository.findAllStudentsByPriceRange(10, 20);

		System.out.println(students);
	}

	@Test
	@Transactional
	public void deleteStudentByName() {

		studentRepository.deleteStudentsByFirstName("Rotariu");

	}

	@Test
	public void testNQFindAll() {

		List<Student> students = studentRepository.findAllStudentsNativeQuery();

		students.forEach(student -> System.out.println("Student name ====> " + student.getFirstName()));
	}

	@Test
	public void testNQFindByFname() {

		List<Student> students = studentRepository.findAllStudentsByFirstName("Rotariu");

		System.out.println(students);

	}
}

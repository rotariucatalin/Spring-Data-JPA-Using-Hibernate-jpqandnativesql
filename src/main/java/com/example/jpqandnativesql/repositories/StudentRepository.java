package com.example.jpqandnativesql.repositories;

import com.example.jpqandnativesql.models.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("from Student")
    List<Student> findAllStudents(Pageable pageable);

    @Query("select firstName, lastName FROM Student")
    List<Object[]> findAllStudentsPartialData();

    @Query("from Student WHERE firstName = :firstName")
    List<Student> findAllStudentsByFirstName(@Param("firstName") String firstName);

    @Query("from Student WHERE score>=:min and score<=:max ")
    List<Student> findAllStudentsByPriceRange(@Param("min") int min, @Param("max") int max);

    @Modifying
    @Query("delete from Student where firstName = :firstName")
    void deleteStudentsByFirstName(@Param("firstName") String firstName);

    @Query( nativeQuery = true, value = "select * from student" )
    List<Student> findAllStudentsNativeQuery();

    @Query( value = "select * from student where fname = :fname", nativeQuery = true)
    List<Student> findByFirstNameNQ(@Param("fname") String fname);

}

package project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project1.models.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //Optional<List<Student>> findByLastName(String lastName);
}

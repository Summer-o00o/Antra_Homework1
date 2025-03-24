package project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project1.models.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}

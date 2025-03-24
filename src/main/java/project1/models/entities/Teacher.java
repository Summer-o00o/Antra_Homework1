package project1.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(length = 100, name = "first_name")
    private String firstName;

    @Column(length = 100, name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "teacher")
    private List<StudentTeacherLink> studentTeacherLinks = new ArrayList<>();

    @Column(length = 50)
    private String username;

    @Column(length = 50, name = "user_role")
    private String userRole;

    @Column(length = 50, name = "user_password")
    private String userPassword;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<StudentTeacherLink> getStudentTeacherLinks() {
        return studentTeacherLinks;
    }

    public void setStudentTeacherLinks(List<StudentTeacherLink> studentTeacherLinks) {
        this.studentTeacherLinks = studentTeacherLinks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

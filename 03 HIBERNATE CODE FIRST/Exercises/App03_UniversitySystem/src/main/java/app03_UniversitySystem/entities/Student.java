package app03_UniversitySystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="students")
public class Student extends BasePerson {

    private String averageGrade;
    private String attendance;


    public Student() {
    }

    public Student(String fistName, String lastName, String phoneNumber, String averageGrade, String attendance) {
        super(fistName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    @Column(name="average_grade")
    public String getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name="attendance")
    public String getAttendance() {
        return this.attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}

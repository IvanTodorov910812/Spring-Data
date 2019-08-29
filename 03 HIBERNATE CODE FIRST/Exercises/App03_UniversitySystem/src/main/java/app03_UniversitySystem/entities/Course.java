package app03_UniversitySystem.entities;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table( name ="courses")
public class Course extends BaseEntity {
    //    •	Course (id, name, description, start date, end date, credits)
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate end_date;
    private Integer credits;
    private Set<Student> student;
    private Teacher teacher;

    public Course() {
    }
    @Column(name="name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description",columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="start_date",columnDefinition = "DATE")
    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name="end_date",columnDefinition = "DATE")
    public LocalDate getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    @Column(name="credits")
    public Integer getCredits() {
        return this.credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @ManyToMany
    @JoinTable(name = "courses_students",
    joinColumns = @JoinColumn(name="course_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="student_id",referencedColumnName = "id"))
    public Set<Student> getStudent() {
        return this.student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

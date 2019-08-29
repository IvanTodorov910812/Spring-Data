package app04_HospitalDatabase.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="diagnoses")
public class Diagnose {
    private int id;
    private String name;
    private String comment;
    private Patient patient;


    public Diagnose() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name",nullable=false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="comment",columnDefinition = "TEXT")
    public String getComment() {
        return this.comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}

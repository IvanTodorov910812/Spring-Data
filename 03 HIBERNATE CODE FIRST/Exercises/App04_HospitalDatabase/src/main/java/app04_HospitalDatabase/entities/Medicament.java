package app04_HospitalDatabase.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="medicament")
public class Medicament {
    private int id;
    private String name;
    private Set<Patient> patient;

    public Medicament() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "medicaments",targetEntity = Patient.class)
    public Set<Patient> getPatient() {
        return this.patient;
    }

    public void setPatient(Set<Patient> patient) {
        this.patient = patient;
    }
}

package app04_HospitalDatabase.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="patients")
public class Patient {
    private int id;
    // first name, last name, address, email, date of birth, picture, information whether he has medical insurance or not.
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    private byte[] picture;
    private boolean hasMedicalInsurance;
    private Set<Visitation> visitions;
    private Set<Diagnose> diagnoses;
    private Set<Medicament> medicaments;
    public Patient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="first_name",nullable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name",nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="address",nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="date_of_birth",nullable = false,columnDefinition = "DATE")
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name="picture",columnDefinition = "BLOB")
    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name="is_hs_medical_insurance",nullable = false)
    public boolean isHasMedicalInsurance() {
        return this.hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
    @OneToMany(mappedBy = "patient",targetEntity =Visitation.class )
    public Set<Visitation> getVisitions() {
        return this.visitions;
    }

    public void setVisitions(Set<Visitation> visitions) {
        this.visitions = visitions;
    }
    @OneToMany(mappedBy = "patient",targetEntity =Diagnose.class )
    public Set<Diagnose> getDiagnoses() {
        return this.diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @ManyToMany
    @JoinTable(name="patients_medicaments",
            joinColumns=@JoinColumn(name="patient_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="medicament_id",referencedColumnName = "id") )
    public Set<Medicament> getMedicaments() {
        return this.medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}

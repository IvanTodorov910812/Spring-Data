package app01_GringottsDatabase.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class Wizard {
    /**
     *
     * •	id – Primary Key (number in range [1, 231-1].
     * •	first_name – Text field with max length of 50 symbols.
     * •	last_name - Text field with max length of 60 symbols. Required
     * •	notes – Text field with max length of 1000 symbols
     * •	age – Non-negative number. Required
     * •	magic_wand_creator – Text field with max length of 100 symbols
     * •	magic_wand_size – Number in range [1, 215-1]
     * •	deposit_group - Text field with max length of 20 symbols
     * •	deposit_start_date – Date and time field
     * •	deposit_amount – Floating point number field
     * •	deposit_interest - Floating point number field
     * •	deposit_charge - Floating point number field
     * •	deposit_expiration_date – Date and time field
     * •	is_deposit_expired – Boolean field
     */
    private int id;
    private String firstName;
    private String lastName;
    private String text;
    private int age;
    private String wizardWandCreator;
    private int magigWandSize;
    private String depositGroup;
    private Date depositStartDate;
    private BigDecimal depositAmount;
    private BigDecimal depositInterest;
    private BigDecimal depositCharge;
    private Date depositExpirationDate;
    private Boolean isDepositExpired;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="first_name",length=50)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name",length=60,nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name="notes",columnDefinition = "TEXT(1000)")
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name="age",nullable = false,columnDefinition = "INT UNSIGNED")
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
          this.age = age;
    }

    @Column(name="magic_wand_creator",length=100)
    public String getWizardWandCreator() {
        return this.wizardWandCreator;
    }

    public void setWizardWandCreator(String wizardWandCreator) {
        this.wizardWandCreator = wizardWandCreator;
    }

    @Column(name="magic_wand_size")
    public int getMagigWandSize() {
        return this.magigWandSize;
    }

    public void setMagigWandSize(int magigWandSize) {
        this.magigWandSize = magigWandSize;
    }

    @Column(name="deposit_group",length = 20)
    public String getDepositGroup() {
        return this.depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    @Column(name="deposit_start_date",columnDefinition = "DATETIME")
    public Date getDepositStartDate() {
        return this.depositStartDate;
    }

    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    @Column(name="deposit_amount")
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Column(name="deposit_interest")
    public BigDecimal getDepositInterest() {
        return this.depositInterest;
    }

    public void setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
    }

    @Column(name="deposit_charge")
    public BigDecimal getDepositCharge() {
        return this.depositCharge;
    }

    public void setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
    }

    @Column(name="deposit_expiration_date",columnDefinition = "DATETIME")
    public Date getDepositExpirationDate() {
        return this.depositExpirationDate;
    }

    public void setDepositExpirationDate(Date depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    @Column(name="isDepositExpired")
    public Boolean getDepositExpired() {
        return this.isDepositExpired;
    }

    public void setDepositExpired(Boolean depositExpired) {
        isDepositExpired = depositExpired;
    }

    public Wizard() {
    }

}

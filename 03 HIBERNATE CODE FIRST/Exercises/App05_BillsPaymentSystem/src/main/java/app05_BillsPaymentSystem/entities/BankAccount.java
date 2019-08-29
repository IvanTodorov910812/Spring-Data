package app05_BillsPaymentSystem.entities;

import javax.persistence.*;

@Entity
@Table(name="bank_account")
public class BankAccount extends BaseBillingDetail{
    private String bankName;
    private String SWIFTcode;

    public BankAccount() {
    }
    @Column(name="bank_name")
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name="SWIFT_code")
    public String getSWIFTcode() {
        return this.SWIFTcode;
    }


    public void setSWIFTcode(String SWIFTcode) {
        this.SWIFTcode = SWIFTcode;
    }

}

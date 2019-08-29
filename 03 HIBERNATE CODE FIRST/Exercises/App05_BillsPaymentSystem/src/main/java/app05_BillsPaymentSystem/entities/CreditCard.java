package app05_BillsPaymentSystem.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="credit_card")
public class CreditCard extends BaseBillingDetail{
    private String cardType;
    private LocalDate  expirationMonth;
    private LocalDate  expirationYear;

    public CreditCard() {
    }

    @Column(name="card_type")
    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name="expiration_month")
    public LocalDate getExpirationMonth() {
        return this.expirationMonth;
    }

    public void setExpirationMonth(LocalDate expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name="expiration_year")
    public LocalDate getExpirationYear() {
        return this.expirationYear;
    }

    public void setExpirationYear(LocalDate expirationYear) {
        this.expirationYear = expirationYear;
    }


}

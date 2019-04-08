package no.gorandalum.esmethods.state;

import java.math.BigDecimal;

public class Account {

    private String number;
    private BigDecimal balance;

    public Account() {}

    public Account(String number) {
        this.number = number;
        this.balance = BigDecimal.ZERO;
    }

    public void updateBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

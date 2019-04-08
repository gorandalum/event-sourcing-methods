package no.gorandalum.esmethods.events;

import no.gorandalum.esmethods.state.State;

import java.math.BigDecimal;

public class TransactionRegisteredEvent implements Event {

    private String customerSsn;
    private String accountNumber;
    private BigDecimal amount;

    public TransactionRegisteredEvent() {}

    public TransactionRegisteredEvent(String customerSsn, String accountNumber, BigDecimal amount) {
        this.customerSsn = customerSsn;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public void updateState(State state) {
        state.findCustomer(customerSsn).findAccount(accountNumber).updateBalance(amount);
    }

    public String getCustomerSsn() {
        return customerSsn;
    }

    public void setCustomerSsn(String customerSsn) {
        this.customerSsn = customerSsn;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

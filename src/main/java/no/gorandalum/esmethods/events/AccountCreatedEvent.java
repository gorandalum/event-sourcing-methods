package no.gorandalum.esmethods.events;

import no.gorandalum.esmethods.state.Account;
import no.gorandalum.esmethods.state.State;

public class AccountCreatedEvent implements Event {

    private String customerSsn;
    private String number;

    public AccountCreatedEvent() {}

    public AccountCreatedEvent(String customerSsn, String number) {
        this.customerSsn = customerSsn;
        this.number = number;
    }

    @Override
    public void updateState(State state) {
        state.findCustomer(customerSsn).addAccount(new Account(number));
    }

    public String getCustomerSsn() {
        return customerSsn;
    }

    public void setCustomerSsn(String customerSsn) {
        this.customerSsn = customerSsn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

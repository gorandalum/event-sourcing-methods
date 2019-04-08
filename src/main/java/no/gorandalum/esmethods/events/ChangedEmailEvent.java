package no.gorandalum.esmethods.events;

import no.gorandalum.esmethods.state.State;

public class ChangedEmailEvent implements Event {

    private String customerSsn;
    private String email;

    public ChangedEmailEvent() {}

    public ChangedEmailEvent(String customerSsn, String email) {
        this.customerSsn = customerSsn;
        this.email = email;
    }

    @Override
    public void updateState(State state) {
        state.findCustomer(customerSsn).setEmail(email);
    }

    public String getCustomerSsn() {
        return customerSsn;
    }

    public void setCustomerSsn(String customerSsn) {
        this.customerSsn = customerSsn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

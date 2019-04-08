package no.gorandalum.esmethods.events;

import no.gorandalum.esmethods.state.Customer;
import no.gorandalum.esmethods.state.State;

public class CustomerCreatedEvent implements Event {

    private String ssn;
    private String name;
    private String email;

    public CustomerCreatedEvent() {}

    public CustomerCreatedEvent(String ssn, String name, String email) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
    }

    @Override
    public void updateState(State state) {
        state.addCustomer(new Customer(ssn, name, email));
    }

    public String getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package no.gorandalum.esmethods.state;

import java.util.ArrayList;
import java.util.List;

public class State {

    private List<Customer> customers = new ArrayList<>();

    public State() {};

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String ssn) {
        return customers.stream()
                .filter(c -> c.getSsn().equals(ssn))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}

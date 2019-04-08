package no.gorandalum.esmethods.state;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String ssn;
    private String name;
    private String email;
    private List<Account> accounts = new ArrayList<>();

    public Customer() {}

    public Customer(String ssn, String name, String email) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String number) {
        return accounts.stream()
                .filter(c -> c.getNumber().equals(number))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

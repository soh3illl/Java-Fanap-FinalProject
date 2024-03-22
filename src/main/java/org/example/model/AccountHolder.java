package org.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class AccountHolder extends User{

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.PERSIST)
    private List<BankAccount> bankAccounts;

    public AccountHolder(String username, String password, String firstName, String lastName, Long nationalCode) {
        super(username, password, firstName, lastName, nationalCode);
    }

    public AccountHolder() {
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}

package org.pmp.budgeto.domain.account;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.pmp.budgeto.common.tools.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * helper to init data on Budget object
 */
@Component
public class AccountHelper {

    @Autowired
    private DateTools dateTools;

    @Autowired
    private AccountRepository accountRepository;

    public void init() throws Exception {

        accountRepository.deleteAll();

        Account object = new Account().setName("account1").setNote("a first account");
        accountRepository.save(object);

        object = new Account().setName("account2").setNote("a second account");
        accountRepository.save(object);

        object = new Account().setName("account3").setNote("a third account with operations")
                .addOperations(new Operation(dateTools).setLabel("operation 1").setDate("2015-02-26"))
                .addOperations(new Operation(dateTools).setLabel("ope2").setDate("2015-02-27"));
        accountRepository.save(object);
    }

    public Account findByName(List<Account> objects, String name) {
        for (Account object : objects) {
            if (object.getName().equals(name)) {
                return object;
            }
        }
        Assert.fail("account " + name + " not ");
        return null;
    }

    public void controlAccount1(Account object) {
        Assertions.assertThat(object.getName()).isEqualTo("account1");
        Assertions.assertThat(object.getNote()).isEqualTo("a first account");
        Assertions.assertThat(object.getOperations()).hasSize(0);
    }

    public void controlAccount2(Account object) {
        Assertions.assertThat(object.getName()).isEqualTo("account2");
        Assertions.assertThat(object.getNote()).isEqualTo("a second account");
        Assertions.assertThat(object.getOperations()).hasSize(0);
    }

    public long nbAccounts() throws Exception {

        return accountRepository.count();
    }

}

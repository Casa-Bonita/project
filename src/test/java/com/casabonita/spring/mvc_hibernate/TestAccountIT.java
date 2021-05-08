package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@ContextConfiguration
        (
                {
                        "file:src/main/webapp/WEB-INF/applicationContext.xml"
                }
        )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAccountIT {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testSave(){

        int testContractId = 1;
        Contract contract = contractDAO.getContract(testContractId);

        String testNumber = "TestNumber";

        Account account = new Account();

        account.setNumber(testNumber);
        account.setAccountContract(contract);

        contract.setAccount(account);

        accountDAO.saveAccount(account);

        Request request = new Request(dataSource,
                "SELECT * FROM account WHERE id = 8");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(8)
                .column("account_number").hasValues(testNumber)
                .column("contract_id").hasValues(testContractId);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testGetById(){

        int id = 1;
        String accountNumber = "62.01.001";
        int contractId = 1;

        Account account = accountDAO.getAccount(id);

        assertThat(id).isEqualTo(account.getId());
        assertThat(accountNumber).isEqualTo(account.getNumber());
        assertThat(contractId).isEqualTo(account.getAccountContract().getId());

        // проверка без getAccount через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM account WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("account_number").hasValues(accountNumber)
                .column("contract_id").hasValues(contractId);

    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testDeleteById(){

        int id = 1;
        accountDAO.deleteAccount(id);

        // проверка через request удаления строки
        Request requestOne = new Request(dataSource,
                "SELECT * FROM account WHERE id = 1");

        Assertions.assertThat(requestOne)
                .isEmpty();

        // проверка через request всех оставшихся после удаления строк таблицы
        Request requestAll = new Request(dataSource,
                "SELECT * FROM account");

        Assertions.assertThat(requestAll)
                .column("id").hasValues(2, 3, 4, 5, 6, 7)
                .column("account_number").hasValues("62.01.002", "62.01.003", "62.01.004", "62.01.005", "62.01.006", "62.01.007")
                .column("contract_id").hasValues(2, 3, 4, 5, 6, 7);

        //проверка через getAllAccounts
        List<Account> accountList = accountDAO.getAllAccounts();

        assertThat(accountList).extracting(x -> x.getId()).contains(2, 3, 4, 5, 6, 7);
        assertThat(accountList).extracting(x -> x.getNumber()).contains("62.01.002", "62.01.003", "62.01.004", "62.01.005", "62.01.006", "62.01.007");
        assertThat(accountList).extracting(x -> x.getAccountContract().getId()).contains(2, 3, 4, 5, 6, 7);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testUpdate(){

        String accountNumber = "TestNumber";

        int id = 1;
        Account accountExpected = accountDAO.getAccount(id);

        accountExpected.setNumber(accountNumber);

        accountDAO.saveAccount(accountExpected);

        Account accountActual = accountDAO.getAccount(id);

        // тест без использования getAccount
        Request request = new Request(dataSource,
                "SELECT * FROM account WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(accountExpected.getId())
                .column("account_number").hasValues(accountNumber)
                .column("contract_id").hasValues(accountExpected.getAccountContract().getId());

        // тест с использованием getAccount
        assertThat(accountExpected.getId()).isEqualTo(accountActual.getId());
        assertThat(accountNumber).isEqualTo(accountActual.getNumber());
        assertThat(accountExpected.getAccountContract().getId()).isEqualTo(accountActual.getAccountContract().getId());

    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testGetAll(){

        List<Account> accountList = accountDAO.getAllAccounts();

        assertThat(accountList).extracting(x -> x.getId()).contains(1, 2, 3, 4, 5, 6, 7);
        assertThat(accountList).extracting(x -> x.getNumber()).contains("62.01.001", "62.01.002", "62.01.003", "62.01.004", "62.01.005", "62.01.006", "62.01.007");
        assertThat(accountList).extracting(x -> x.getAccountContract().getId()).contains(1, 2, 3, 4, 5, 6, 7);
    }
}

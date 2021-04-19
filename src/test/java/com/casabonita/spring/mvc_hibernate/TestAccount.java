package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Request;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;


@ActiveProfiles("test")
@ContextConfiguration
        (
                {
                        "file:src/main/webapp/WEB-INF/applicationContext.xml"
                }
        )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAccount {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private DataSource dataSource;

    // Нужно ли проверять сохранение списка платежей или только счёт?
    @Test
    @Sql("/scripts/payment_init.sql")
    public void testSave(){

        Contract contract = contractDAO.getContract(1);

        List<Payment> paymentList = paymentDAO.getAllPayments();

        String testNumber = "TestNumber";

        Account account = new Account();

        account.setNumber(testNumber);
        account.setAccountContract(contract);
        account.setPaymentsList(paymentList);

        accountDAO.saveAccount(account);

        Request request = new Request(dataSource,
                "SELECT * FROM account");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("account_number").hasValues(testNumber)
                .column("contract_id").hasValues(contract.getId());
    }

    // Надо ли извлекать списки и сравнивать их?
    // TODO занести данные в contract_init, payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testGetById(){

        String accountNumber = "TestNumber";
        int contractId = 1;
        List<Payment> paymentList = paymentDAO.getAllPayments();

        Account account = accountDAO.getAccount(1);

        assert accountNumber == account.getNumber();
        assert contractId == account.getAccountContract().getId();

        Assert.assertEquals(paymentList, account.getPaymentsList());
    }

    // Надо ли извлекать списки и сравнивать их?
    // TODO занести данные в contract_init, payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testDeleteById(){

        String accountNumber = "TestNumber";
        int contractId = 1;
        List<Payment> paymentList = paymentDAO.getAllPayments();

        accountDAO.deleteAccount(1);

        Account account = accountDAO.getAccount(1);

        assert accountNumber.equals(account.getNumber());
        assert contractId == account.getAccountContract().getId();

        Assert.assertEquals(paymentList, account.getPaymentsList());
    }

    // Надо ли извлекать списки и сравнивать их?
    // TODO занести данные в contract_init, payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testUpdate(){

        String accountNumber = "TestNumber";
        int contractId = 1;
        List<Payment> paymentList = paymentDAO.getAllPayments();

        Account accountExpected = accountDAO.getAccount(1);

        accountExpected.setNumber("TestNumber_2");

        accountDAO.saveAccount(accountExpected);

        Account accountActual = accountDAO.getAccount(1);

        assert accountNumber == accountActual.getNumber();
        assert contractId == accountActual.getAccountContract().getId();

        Assert.assertEquals(paymentList, accountActual.getPaymentsList());
    }

    // нужно ли указывать список платежей paymentList
    // TODO занести данные в contract_init, payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    public void testGetAll(){

        List<Account> accountList = accountDAO.getAllAccounts();

        assertThat(accountList).extracting("id", "number", "accountContract")
                .contains(tuple(0, "62.01.001", 0),
                        tuple(1, "62.01.002", 1),
                        tuple(2, "62.01.003", 2),
                        tuple(3, "62.01.004", 3),
                        tuple(4, "62.01.005", 4),
                        tuple(5, "62.01.006", 5),
                        tuple(6, "62.01.007", 6));
    }

}

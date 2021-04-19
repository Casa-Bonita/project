package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
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
public class TestPayment {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql("/scripts/account_init.sql")
    public void testSave() throws ParseException {

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Account account = accountDAO.getAccount(1);

        int testAmount = 100;
        Date testDate = sdf.parse(d);
        String testPurpose = "TestPurpose";

        Payment payment = new Payment();

        payment.setAccount(account);
        payment.setAmount(testAmount);
        payment.setDate(testDate);
        payment.setPurpose(testPurpose);

        paymentDAO.savePayment(payment);

        Request request = new Request(dataSource,
                "SELECT * FROM account_data");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("payment").hasValues(testAmount)
                .column("payment_date").hasValues(d)
                .column("payment_purpose").hasValues(testPurpose);
    }

    // TODO занести данные в payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testGetById() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int accountId = 1;
        int amount = 100;
        Date date = sdf.parse(d);
        String purpose = "TestPurpose";

        Payment payment = paymentDAO.getPayment(1);

        assert accountId == payment.getAccount().getId();
        assert amount == payment.getAmount();
        assert date.equals(payment.getDate());
        assert purpose.equals(payment.getPurpose());
    }

    // TODO занести данные в payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testDeleteById() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int accountId = 1;
        int amount = 100;
        Date date = sdf.parse(d);
        String purpose = "TestPurpose";

        paymentDAO.deletePayment(1);

        Payment payment = paymentDAO.getPayment(1);

        assert accountId == payment.getAccount().getId();
        assert amount == payment.getAmount();
        assert date.equals(payment.getDate());
        assert purpose.equals(payment.getPurpose());
    }

    // TODO занести данные в payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testUpdate() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int accountId = 1;
        int amount = 100;
        Date date = sdf.parse(d);
        String purpose = "TestPurpose";

        Payment paymentExpected = paymentDAO.getPayment(1);

        paymentExpected.setAmount(200);

        paymentDAO.savePayment(paymentExpected);

        Payment paymentActual = paymentDAO.getPayment(1);

        assert accountId == paymentActual.getAccount().getId();
        assert amount == paymentActual.getAmount();
        assert date.equals(paymentActual.getDate());
        assert purpose.equals(paymentActual.getPurpose());
    }

    // TODO занести данные в payment_init, запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/payment_init.sql")
    public void testGetAll() throws ParseException{

        // TODO указать даты из payment_init
        String d1 = "1995-01-11";
        String d2 = "2006-05-03";
        String d3 = "2014-03-18";
        String d4 = "2008-12-23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Payment> paymentList = paymentDAO.getAllPayments();

        // TODO занести данные из payment_init
        assertThat(paymentList).extracting("id", "account", "amount", "date", "purpose")
                .contains(tuple(0, 0, 100, sdf.parse(d1), "payment_purpose_1"),
                        tuple(1, 1, 200, sdf.parse(d2), "payment_purpose_2"),
                        tuple(2, 2, 300, sdf.parse(d3), "payment_purpose_3"),
                        tuple(3, 3, 400, sdf.parse(d4), "payment_purpose_4"));
    }

}

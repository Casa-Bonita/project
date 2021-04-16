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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private PaymentDAO paymentDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testSave() throws ParseException {

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Account account = accountDAO.getAccount(1);

        int testAmount = 100;
        Date testDate = sdf.parse(d);
        String testPurpose = "TestPurpose";

        Payment paymentActual = new Payment();

        paymentActual.setAccount(account);
        paymentActual.setAmount(testAmount);
        paymentActual.setDate(testDate);
        paymentActual.setPurpose(testPurpose);

        paymentDAO.savePayment(paymentActual);

        Request request = new Request(dataSource,
                "SELECT * FROM account_data");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("payment").hasValues(100);

    }
//
//    @Test
//    public void TestGetById() throws ParseException{
//
//        String d = "2021-01-01";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(d);
//
//        account = new Account();
//        account.setId(0);
//        account.setNumber("62.01.001");
//        account.setAccountContract(null);
//
//        paymentActual = new Payment();
//        paymentActual.setAccount(account);
//        paymentActual.setAmount(100);
//        paymentActual.setDate(date);
//        paymentActual.setPurpose("TestPurpose");
//
//        account.addPaymentToAccount(paymentActual);
//
//        accountDAO.saveAccount(account);
//        paymentDAO.savePayment(paymentActual);
//
//        Request request = new Request(dataSource,
//                "SELECT * FROM account_data INNER JOIN account ON account_data.account_id = account.id WHERE account_data.id = 0");
//
//        Assertions.assertThat(request)
//                .hasFieldOrPropertyWithValue("payment", 100)
//                .hasFieldOrPropertyWithValue("payment_date", date)
//                .hasFieldOrPropertyWithValue("payment_purpose", "TestPurpose");
//
//    }
//
//    @Test
//    public void TestDeleteById() throws ParseException{
//
//        String d1 = "2021-01-01";
//        String d2 = "2021-15-04";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1 = sdf.parse(d1);
//        Date date2 = sdf.parse(d2);
//
//        account = new Account();
//        account.setId(0);
//        account.setNumber("62.01.001");
//        account.setAccountContract(null);
//
//        paymentActual1 = new Payment();
//        paymentActual1.setAccount(account);
//        paymentActual1.setAmount(100);
//        paymentActual1.setDate(date1);
//        paymentActual1.setPurpose("TestPurpose_1");
//
//        paymentActual2 = new Payment();
//        paymentActual2.setAccount(account);
//        paymentActual2.setAmount(2000);
//        paymentActual2.setDate(date2);
//        paymentActual2.setPurpose("TestPurpose_2");
//
//        account.addPaymentToAccount(paymentActual1);
//        account.addPaymentToAccount(paymentActual2);
//
//        accountDAO.saveAccount(account);
//        paymentDAO.savePayment(paymentActual1);
//        paymentDAO.savePayment(paymentActual2);
//
//        paymentDAO.deletePayment(0);
//
//        Request request = new Request(dataSource,
//                "SELECT * FROM account_data INNER JOIN account ON account_data.account_id = account.id WHERE account_data.id = 1");
//
//        Assertions.assertThat(request)
//                .hasFieldOrPropertyWithValue("payment", 2000)
//                .hasFieldOrPropertyWithValue("payment_date", date2)
//                .hasFieldOrPropertyWithValue("payment_purpose", "TestPurpose");
//
//    }
//
//    @Test
//    public void TestUpdate() throws ParseException{
//
//        String d = "2021-01-01";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(d);
//
//        account = new Account();
//        account.setId(0);
//        account.setNumber("62.01.001");
//        account.setAccountContract(null);
//
//        paymentActual = new Payment();
//        paymentActual.setAccount(account);
//        paymentActual.setAmount(100);
//        paymentActual.setDate(date);
//        paymentActual.setPurpose("TestPurpose");
//
//        account.addPaymentToAccount(paymentActual);
//
//        accountDAO.saveAccount(account);
//        paymentDAO.savePayment(paymentActual);
//
//        paymentActual.setPurpose("TestPurpose_Changed");
//
//        paymentDAO.savePayment(paymentActual);
//
//        Request request = new Request(dataSource,
//                "SELECT * FROM account_data INNER JOIN account ON account_data.account_id = account.id");
//
//        Assertions.assertThat(request)
//                .hasFieldOrPropertyWithValue("payment", 100)
//                .hasFieldOrPropertyWithValue("payment_date", date)
//                .hasFieldOrPropertyWithValue("payment_purpose", "TestPurpose_Changed");
//
//    }
//
//    @Test
//    public void TestGetAll() throws ParseException{
//
//        String d1 = "2021-01-01";
//        String d2 = "2021-15-04";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1 = sdf.parse(d1);
//        Date date2 = sdf.parse(d2);
//
//        account = new Account();
//        account.setId(0);
//        account.setNumber("62.01.001");
//        account.setAccountContract(null);
//
//        paymentActual1 = new Payment();
//        paymentActual1.setAccount(account);
//        paymentActual1.setAmount(100);
//        paymentActual1.setDate(date1);
//        paymentActual1.setPurpose("TestPurpose_1");
//
//        paymentActual2 = new Payment();
//        paymentActual2.setAccount(account);
//        paymentActual2.setAmount(2000);
//        paymentActual2.setDate(date2);
//        paymentActual2.setPurpose("TestPurpose_2");
//
//        account.addPaymentToAccount(paymentActual1);
//        account.addPaymentToAccount(paymentActual2);
//
//        accountDAO.saveAccount(account);
//        paymentDAO.savePayment(paymentActual1);
//        paymentDAO.savePayment(paymentActual2);
//
//        paymentList = paymentDAO.getAllPayments();
//
//        Assertions.assertThat(paymentList).extracting("id", "account_id", "payment", "payment_date", "payment_purpose")
//                .contains(tuple(0, 0, 100, date1, "TestPurpose_1"),
//                        tuple(1, 0, 2000, date2, "TestPurpose_2"));
//
//    }

}

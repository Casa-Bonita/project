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

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ContextConfiguration
        (
                {
                        "file:src/main/webapp/WEB-INF/applicationContext.xml"
                }
        )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPaymentIT {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql"})
    public void testSave() throws ParseException {

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int id = 1;
        Account account = accountDAO.getAccount(id);

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
                .column("id").hasValues(id)
                .column("account_id").hasValues(id)
                .column("payment").hasValues(testAmount)
                .column("payment_date").hasValues(d)
                .column("payment_purpose").hasValues(testPurpose);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testGetById() throws ParseException{

        String d = "2021-03-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int id = 1;
        int accountId = 1;
        int amount = 1000;
        Date date = sdf.parse(d);
        String purpose = "Oplata za elektroenergiu za fevral 2021";

        Payment payment = paymentDAO.getPayment(id);

        assertThat(id).isEqualTo(payment.getId());
        assertThat(accountId).isEqualTo(payment.getAccount().getId());
        assertThat(amount).isEqualTo(payment.getAmount());
        assertThat(date).isEqualTo(payment.getDate());
        assertThat(purpose).isEqualTo(payment.getPurpose());

        // тест без использования getPayment
        Request request = new Request(dataSource,
                "SELECT * FROM account_data WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("account_id").hasValues(accountId)
                .column("payment").hasValues(amount)
                .column("payment_date").hasValues(d)
                .column("payment_purpose").hasValues(purpose);

    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testDeleteById() throws ParseException{

        int id = 1;
        paymentDAO.deletePayment(id);

        List<Payment> paymentList = paymentDAO.getAllPayments();

        assertThat(paymentList)
                .isNotEmpty()
                .hasSize(13);

        String d1 = "2021-03-01";
        String d2 = "2021-02-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(paymentList).extracting(x -> x.getId()).contains(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        assertThat(paymentList).extracting(x -> x.getAccount().getId()).contains(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7);
        assertThat(paymentList).extracting(x -> x.getAmount()).contains(1100, 2200, 2100, 1500, 1700, 3000, 2900, 2400, 2500, 3500, 3400, 2000, 1900);
        assertThat(paymentList).extracting(x -> sdf.parse(x.getDate().toString())).contains(sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2));
        assertThat(paymentList).extracting(x -> x.getPurpose()).contains("Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021");
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testUpdate(){

        String purpose = "Test";

        int id = 1;
        Payment paymentExpected = paymentDAO.getPayment(id);

        paymentExpected.setPurpose(purpose);

        paymentDAO.savePayment(paymentExpected);

        Payment paymentActual = paymentDAO.getPayment(id);

        assertThat(paymentExpected.getId()).isEqualTo(paymentActual.getId());
        assertThat(paymentExpected.getAccount().getId()).isEqualTo(paymentActual.getAccount().getId());
        assertThat(paymentExpected.getAmount()).isEqualTo(paymentActual.getAmount());
        assertThat(paymentExpected.getDate()).isEqualTo(paymentActual.getDate());
        assertThat(purpose).isEqualTo(paymentActual.getPurpose());
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testGetAll() throws ParseException{

        List<Payment> paymentList = paymentDAO.getAllPayments();

        String d1 = "2021-03-01";
        String d2 = "2021-02-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(paymentList).extracting(x -> x.getId()).contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        assertThat(paymentList).extracting(x -> x.getAccount().getId()).contains(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7);
        assertThat(paymentList).extracting(x -> x.getAmount()).contains(1000, 1100, 2200, 2100, 1500, 1700, 3000, 2900, 2400, 2500, 3500, 3400, 2000, 1900);
        assertThat(paymentList).extracting(x -> sdf.parse(x.getDate().toString())).contains( sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2));
        assertThat(paymentList).extracting(x -> x.getPurpose()).contains("Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021", "Oplata za elektroenergiu za fevral 2021", "Oplata za elektroenergiu za yanvar 2021");

    }
}

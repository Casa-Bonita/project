package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ContextConfiguration
        (
                {
                        "file:src/main/webapp/WEB-INF/applicationContext.xml"
                }
        )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPayment {

    private Payment paymentBefore1;
    private Payment paymentBefore2;
    private List<Payment> paymentList;
    private Account account;

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void createPayment() throws ParseException{
        String d1 = "2021-01-01";
        String d2 = "2021-04-10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(d1);
        Date date2 = sdf.parse(d2);

//        Account account = new Account();
//        account.setNumber("62.01.001");
//        account.setAccountContract(null);

        paymentBefore1 = new Payment();
        paymentBefore1.setAccount(null);
        paymentBefore1.setAmount(100);
        paymentBefore1.setDate(date1);
        paymentBefore1.setPurpose("TestPurpose_1");

        paymentBefore2 = new Payment();
        paymentBefore2.setAccount(null);
        paymentBefore2.setAmount(2000);
        paymentBefore2.setDate(date2);
        paymentBefore2.setPurpose("TestPurpose_2");

//        account.addPaymentToAccount(paymentBefore1);
//        account.addPaymentToAccount(paymentBefore2);

        paymentDAO.savePayment(paymentBefore1);
        paymentDAO.savePayment(paymentBefore2);

    }

    @Before
    public void fillPaymentList(){
        paymentList = jdbcTemplate.query(
                "SELECT payment, payment_date, payment_purpose FROM account_data",
                new RowMapper<Payment>() {
                    @Override
                    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {

                        Payment paymentAfter = new Payment();

                        paymentAfter.setAccount(null);
                        paymentAfter.setAmount(resultSet.getInt("payment"));
                        paymentAfter.setDate(resultSet.getDate("payment_date"));
                        paymentAfter.setPurpose(resultSet.getString("payment_purpose"));

                        return paymentAfter;
                    }
                }
        );
    }

    @After
    public void clearPaymentList(){

        paymentList.clear();
    }


    @Test
    @Transactional
    public void testInsert()  {

        assertFalse(paymentList.isEmpty());

        assertEquals(paymentBefore1.getAccount(), paymentList.get(0).getAccount());
        assertEquals(paymentBefore1.getAmount(), paymentList.get(0).getAmount());
        assertEquals(paymentBefore1.getDate(), paymentList.get(0).getDate());
        assertEquals(paymentBefore1.getPurpose(), paymentList.get(0).getPurpose());

    }
}

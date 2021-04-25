package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
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
public class TestContract {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private RenterDAO renterDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql",
            "/scripts/reading_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testSave() throws ParseException{

        int id = 1;

        String d1 = "2021-01-01";
        String d2 = "2021-01-15";
        String d3 = "2021-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String testNumber = "TestNumber";
        Date testContractDate = sdf.parse(d1);
        int testFare = 100;
        Date testStartDate = sdf.parse(d2);
        Date testFinishDate = sdf.parse(d3);
        int testPaymentDay = 25;
        Place place = placeDAO.getPlace(id);
        Renter renter = renterDAO.getRenter(id);
        Account account = accountDAO.getAccount(id);

        Contract contract = new Contract();

        contract.setNumber(testNumber);
        contract.setDate(testContractDate);
        contract.setFare(testFare);
        contract.setStartDate(testStartDate);
        contract.setFinishDate(testFinishDate);
        contract.setPaymentDay(testPaymentDay);
        contract.setContractPlace(place);
        contract.setRenter(renter);
        contract.setAccount(account);

        contractDAO.saveContract(contract);

        Request request = new Request(dataSource,
                "SELECT * FROM contract WHERE id = 8");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(8)
                .column("number").hasValues(testNumber)
                .column("contract_date").hasValues(d1)
                .column("fare").hasValues(testFare)
                .column("start_date").hasValues(d2)
                .column("finish_date").hasValues(d3)
                .column("payment_day").hasValues(testPaymentDay)
                .column("place_id").hasValues(place.getId()) // правильно ли вызывать getId() ?
                .column("renter_id").hasValues(renter.getId()); // правильно ли вызывать getId() ?
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql",
            "/scripts/reading_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testGetById() throws ParseException{

        String d1 = "2019-01-01";
        String d2 = "2019-01-01";
        String d3 = "2021-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int id = 1;
        String number = "100R";
        Date contractDate = sdf.parse(d1);
        int fare = 1000;
        Date startDate = sdf.parse(d2);
        Date finishDate = sdf.parse(d3);
        int paymentDay = 5;
        int contractPlace = 1;
        int renter = 1;

        // проверка через getContract
        Contract contract = contractDAO.getContract(id);

        assertThat(id).isEqualTo(contract.getId());
        assertThat(number).isEqualTo(contract.getNumber());
        assertThat(contractDate).isEqualTo(contract.getDate());
        assertThat(fare).isEqualTo(contract.getFare());
        assertThat(startDate).isEqualTo(contract.getStartDate());
        assertThat(finishDate).isEqualTo(contract.getFinishDate());
        assertThat(paymentDay).isEqualTo(contract.getPaymentDay());
        assertThat(contractPlace).isEqualTo(contract.getContractPlace().getId());
        assertThat(renter).isEqualTo(contract.getRenter().getId());

        // проверка без getContract через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM contract WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("number").hasValues(number)
                .column("contract_date").hasValues(d1)
                .column("fare").hasValues(fare)
                .column("start_date").hasValues(d2)
                .column("finish_date").hasValues(d3)
                .column("payment_day").hasValues(paymentDay)
                .column("place_id").hasValues(contractPlace)
                .column("renter_id").hasValues(renter);

    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql",
            "/scripts/reading_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testDeleteById() throws ParseException{

        String d1 = "2019-01-01";
        String d2 = "2019-01-01";
        String d3 = "2021-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int id = 1;
        contractDAO.deleteContract(id);

        // проверка через request удаления строки
        Request requestOne = new Request(dataSource,
                "SELECT * FROM contract WHERE id = 1");

        Assertions.assertThat(requestOne)
                .isEmpty();

        // проверка через request всех оставшихся после удаления строк таблицы
        Request requestAll = new Request(dataSource,
                "SELECT * FROM contract");

        Assertions.assertThat(requestAll)
                .column("id").hasValues(2, 3, 4, 5, 6, 7)
                .column("number").hasValues("101R", "102R", "103L", "104O", "105O", "106M")
                .column("contract_date").hasValues(d1, d1, d1, d1, d1, d1)
                .column("fare").hasValues(2000, 3000, 500, 1500, 2500, 3500)
                .column("start_date").hasValues(d2, d2, d2, d2, d2, d2)
                .column("finish_date").hasValues(d3, d3, d3, d3, d3, d3)
                .column("payment_day").hasValues(5, 5, 4, 3, 3, 2)
                .column("place_id").hasValues(2, 3, 4, 5, 6, 7)
                .column("renter_id").hasValues(1, 1, 2, 3, 3, 4);

        //проверка через getAllContracts
        List<Contract> contractList = contractDAO.getAllContracts();

        assertThat(contractList).extracting(x -> x.getId()).contains(2, 3, 4, 5, 6, 7);
        assertThat(contractList).extracting(x -> x.getNumber()).contains("101R", "102R", "103L", "104O", "105O", "106M");
        assertThat(contractList).extracting(x -> sdf.parse(x.getDate().toString())).contains(sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1));
        assertThat(contractList).extracting(x -> x.getFare()).contains(2000, 3000, 500, 1500, 2500, 3500);
        assertThat(contractList).extracting(x -> sdf.parse(x.getStartDate().toString())).contains(sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2));
        assertThat(contractList).extracting(x -> sdf.parse(x.getFinishDate().toString())).contains(sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3));
        assertThat(contractList).extracting(x -> x.getPaymentDay()).contains(5, 5, 4, 3, 3, 2);
        assertThat(contractList).extracting(x -> x.getContractPlace().getId()).contains(2, 3, 4, 5, 6, 7);
        assertThat(contractList).extracting(x -> x.getRenter().getId()).contains(1, 1, 2, 3, 3, 4);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql",
            "/scripts/reading_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testUpdate() throws ParseException{

        String number = "TestNumber";

        int id = 1;
        Contract contractExpected = contractDAO.getContract(id);

        contractExpected.setNumber(number);

        contractDAO.saveContract(contractExpected);

        Contract contractActual = contractDAO.getContract(id);

        assertThat(contractExpected.getId()).isEqualTo(contractActual.getId());
        assertThat(number).isEqualTo(contractActual.getNumber());
        assertThat(contractExpected.getDate()).isEqualTo(contractActual.getDate());
        assertThat(contractExpected.getFare()).isEqualTo(contractActual.getFare());
        assertThat(contractExpected.getStartDate()).isEqualTo(contractActual.getStartDate());
        assertThat(contractExpected.getFinishDate()).isEqualTo(contractActual.getFinishDate());
        assertThat(contractExpected.getPaymentDay()).isEqualTo(contractActual.getPaymentDay());
        assertThat(contractExpected.getContractPlace().getId()).isEqualTo(contractActual.getContractPlace().getId());
        assertThat(contractExpected.getRenter().getId()).isEqualTo(contractActual.getRenter().getId());

    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql",
            "/scripts/reading_init.sql", "/scripts/account_init.sql", "/scripts/payment_init.sql"})
    public void testGetAll() throws ParseException{

        List<Contract> contractList = contractDAO.getAllContracts();

        String d1 = "2019-01-01";
        String d2 = "2019-01-01";
        String d3 = "2021-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(contractList).extracting(x -> x.getId()).contains(1, 2, 3, 4, 5, 6, 7);
        assertThat(contractList).extracting(x -> x.getNumber()).contains("100R", "101R", "102R", "103L", "104O", "105O", "106M");
        assertThat(contractList).extracting(x -> sdf.parse(x.getDate().toString())).contains(sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1), sdf.parse(d1));
        assertThat(contractList).extracting(x -> x.getFare()).contains(1000, 2000, 3000, 500, 1500, 2500, 3500);
        assertThat(contractList).extracting(x -> sdf.parse(x.getStartDate().toString())).contains(sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2), sdf.parse(d2));
        assertThat(contractList).extracting(x -> sdf.parse(x.getFinishDate().toString())).contains(sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3), sdf.parse(d3));
        assertThat(contractList).extracting(x -> x.getPaymentDay()).contains(5, 5, 5, 4, 3, 3, 2);
        assertThat(contractList).extracting(x -> x.getContractPlace().getId()).contains(1, 2, 3, 4, 5, 6, 7);
        assertThat(contractList).extracting(x -> x.getRenter().getId()).contains(1, 1, 1, 2, 3, 3, 4);

    }
}
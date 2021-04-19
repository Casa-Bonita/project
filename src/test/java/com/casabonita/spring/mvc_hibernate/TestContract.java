package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
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
import static org.assertj.core.api.Assertions.tuple;

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

    // TODO занести данные в account_init, place_init, renter_init и запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/place_init.sql")
    @Sql("/scripts/renter_init.sql")
    public void testSave() throws ParseException{

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
        Place place = placeDAO.getPlace(1);
        Renter renter = renterDAO.getRenter(1);
        Account account = accountDAO.getAccount(1);

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
                "SELECT * FROM contract");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("number").hasValues(testNumber)
                .column("date").hasValues(d1)
                .column("fare").hasValues(testFare)
                .column("startDate").hasValues(d2) // почему тут String, а не Date
                .column("finishDate").hasValues(d3)
                .column("paymentDay").hasValues(testPaymentDay)
                .column("contractPlace").hasValues(place)
                .column("renter").hasValues(renter)
                .column("account").hasValues(account);
    }

    // TODO занести данные в account_init, place_init, renter_init и запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/place_init.sql")
    @Sql("/scripts/renter_init.sql")
    public void testGetById() throws ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = "2021-01-01";
        String d2 = "2021-01-15";
        String d3 = "2021-12-31";

        String testNumber = "TestNumber";
        Date testContractDate = sdf.parse(d1);
        int testFare = 100;
        Date testStartDate = sdf.parse(d2);
        Date testFinishDate = sdf.parse(d3);
        int testPaymentDay = 25;
        int testContractPlace = placeDAO.getPlace(1).getId();
        int testRenter = renterDAO.getRenter(1).getId();
        int testAccount = accountDAO.getAccount(1).getId();

        Contract contract = contractDAO.getContract(1);

        assert testNumber.equals(contract.getNumber());
        assert testContractDate.equals(contract.getDate());
        assert testFare == contract.getFare();
        assert testStartDate.equals(contract.getStartDate());
        assert testFinishDate.equals(contract.getFinishDate());
        assert testPaymentDay == contract.getPaymentDay();
        assert testContractPlace == contract.getContractPlace().getId();
        assert testRenter == contract.getRenter().getId();
        assert testAccount == contract.getAccount().getId();
    }

    // TODO занести данные в account_init, place_init, renter_init и запустить тест
    @Test
    @Sql("/scripts/account_init.sql")
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/place_init.sql")
    @Sql("/scripts/renter_init.sql")
    public void testDeleteById() throws ParseException{

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
        Place place = placeDAO.getPlace(1);
        Renter renter = renterDAO.getRenter(1);
        Account account = accountDAO.getAccount(1);

        contractDAO.deleteContract(1);

        Contract contract = contractDAO.getContract(1);

        assert testNumber.equals(contract.getNumber());
        assert testContractDate.equals(contract.getDate());
        assert testFare == contract.getFare();
        assert testStartDate.equals(contract.getStartDate());
        assert testFinishDate.equals(contract.getFinishDate());
        assert testPaymentDay == contract.getPaymentDay();
        assert place.equals(contract.getContractPlace());
        assert renter.equals(contract.getRenter());
        assert account.equals(contract.getAccount());
    }

    // TODO занести данные в contract_init и запустить тест
    @Test
    @Sql("/scripts/contract_init.sql")

    public void testUpdate() throws ParseException{

        Contract contractExpected = contractDAO.getContract(1);

        contractExpected.setNumber("Test");

        contractDAO.saveContract(contractExpected);

        Contract contractActual = contractDAO.getContract(1);

        assert contractExpected.getNumber().equals(contractActual.getNumber());
        assert contractExpected.getDate().equals(contractActual.getDate());
        assert contractExpected.getFare() == contractActual.getFare();
        assert contractExpected.getStartDate().equals(contractActual.getStartDate());
        assert contractExpected.getFinishDate().equals(contractActual.getFinishDate());
        assert contractExpected.getPaymentDay() == contractActual.getPaymentDay();
        assert contractExpected.getContractPlace().equals(contractActual.getContractPlace());
        assert contractExpected.getRenter().equals(contractActual.getRenter());
        assert contractExpected.getAccount().equals(contractActual.getAccount());

    }

    // TODO занести данные в тест и в contract_init, запустить тест
    @Test
    @Sql("/scripts/contract_init.sql")
    public void testGetAll() throws ParseException{
        String d1 = "2021-01-01";
        String d2 = "2021-01-15";
        String d3 = "2021-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Contract> contractList = contractDAO.getAllContracts();

        assertThat(contractList).extracting("id", "number", "date", "fare", "startDate", "finishDate", "paymentDay", "contractPlace", "renter", "account")
                .contains(tuple(0, "Romashka", "1076318010548", "6318308609", sdf.parse(d1), "443117, Samarskaya oblast, gorod Samara, Orshanskij pereulok, 9", "Prohorov Vladimir Stepanovich", "Yablochkin Vasilij Petrovich", "+7(495)123-45-67"),
                        tuple(1, "Luytik", "1064027042991", "4027073395", sdf.parse(d2), "248002, Kaluzhskaya oblast, gorod Kaluga, ulica Saltykova-Shchedrina, 76", "Shumakov Grigorij Anatolevich", "Goncharov Eduard Sergeevich", "+7(495)123-67-45"),
                        tuple(2, "Oduvanchik", "1145476032668", "5406775985", sdf.parse(d3), "656056, Altajskij kraj, gorod Barnaul, ploshchad im V.N.Bavarina, dom 2, ofis 910", "Trufanov Anton Yurevich", "Arhipova Nadezhda Viktorovna", "+7(495)123-45-89"),
                        tuple(3, "Margaritka", "1086168005550", "6168024958", sdf.parse(d3), "344015, Rostovskaya oblast, gorod Rostov-na-Donu, ulica Eremenko, 58/9", "Pavlickaya Natalya Yakovlevna", "Boldyreva Svetlana Aleksandrovna", "+7(495)123-67-45"));

    }
}

// Тестирование через JdbcTemplate
//@RunWith(SpringJUnit4ClassRunner.class)
//public class TestRenter{
//
//    private Renter renterBefore1;
//    private Renter renterBefore2;
//    private List<Renter> renterList;
//
//    @Autowired
//    private RenterDAO renterDAO;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Before
//    public void createRenter() throws ParseException {
//        String d1 = "2021-01-01";
//        String d2 = "2021-07-04";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1 = sdf.parse(d1);
//        Date date2 = sdf.parse(d2);
//
//        renterBefore1 = new Renter();
//        renterBefore1.setName("TestName_1");
//        renterBefore1.setOgrn("TestOGRN_1");
//        renterBefore1.setInn("TestINN_1");
//        renterBefore1.setRegistrDate(date1);
//        renterBefore1.setAddress("TestAddress_1");
//        renterBefore1.setDirectorName("TestDirectorName_1");
//        renterBefore1.setContactName("testContactName_1");
//        renterBefore1.setPhoneNumber("testPhoneNumber_1");
//
//        renterBefore2 = new Renter();
//        renterBefore2.setName("TestName_2");
//        renterBefore2.setOgrn("TestOGRN_2");
//        renterBefore2.setInn("TestINN_2");
//        renterBefore2.setRegistrDate(date2);
//        renterBefore2.setAddress("TestAddress_2");
//        renterBefore2.setDirectorName("TestDirectorName_2");
//        renterBefore2.setContactName("testContactName_2");
//        renterBefore2.setPhoneNumber("testPhoneNumber_2");
//
//        renterDAO.saveRenter(renterBefore1);
//        renterDAO.saveRenter(renterBefore2);
//    }
//
//    @Before
//    public void fillRenterList(){
//        renterList = jdbcTemplate.query(
//                "SELECT * FROM renter",
//                new RowMapper<Renter>() {
//                    @Override
//                    public Renter mapRow(ResultSet resultSet, int i) throws SQLException {
//                        Renter renterAfter = new Renter();
//
//                        renterAfter.setName(resultSet.getString("name"));
//                        renterAfter.setOgrn(resultSet.getString("ogrn"));
//                        renterAfter.setInn(resultSet.getString("inn"));
//                        renterAfter.setRegistrDate(resultSet.getDate("registr_date"));
//                        renterAfter.setAddress(resultSet.getString("address"));
//                        renterAfter.setDirectorName(resultSet.getString("director_name"));
//                        renterAfter.setContactName(resultSet.getString("contact_name"));
//                        renterAfter.setPhoneNumber(resultSet.getString("phone"));
//
//                        return renterAfter;
//                    }
//                }
//        );
//    }
//
//    @After
//    public void clearRenterList(){
//
//        renterList.clear();
//    }
//
//    @Test
//    @Transactional
//    public void testInsert(){
//
//        assertFalse(renterList.isEmpty());
//
//        assertEquals(renterBefore1.getName(), renterList.get(0).getName());
//        assertEquals(renterBefore1.getOgrn(), renterList.get(0).getOgrn());
//        assertEquals(renterBefore1.getInn(), renterList.get(0).getInn());
//        assertEquals(renterBefore1.getRegistrDate(), renterList.get(0).getRegistrDate());
//        assertEquals(renterBefore1.getAddress(), renterList.get(0).getAddress());
//        assertEquals(renterBefore1.getDirectorName(), renterList.get(0).getDirectorName());
//        assertEquals(renterBefore1.getContactName(), renterList.get(0).getContactName());
//        assertEquals(renterBefore1.getPhoneNumber(), renterList.get(0).getPhoneNumber());
//
//    }
//
//    @Test
//    @Transactional
//    public void TestGetById(){
//
//        assertFalse(renterList.isEmpty());
//
//        assertEquals(renterBefore1.getName(), renterList.get(0).getName());
//        assertEquals(renterBefore1.getOgrn(), renterList.get(0).getOgrn());
//        assertEquals(renterBefore1.getInn(), renterList.get(0).getInn());
//        assertEquals(renterBefore1.getRegistrDate(), renterList.get(0).getRegistrDate());
//        assertEquals(renterBefore1.getAddress(), renterList.get(0).getAddress());
//        assertEquals(renterBefore1.getDirectorName(), renterList.get(0).getDirectorName());
//        assertEquals(renterBefore1.getContactName(), renterList.get(0).getContactName());
//        assertEquals(renterBefore1.getPhoneNumber(), renterList.get(0).getPhoneNumber());
//
//        assertEquals(renterBefore2.getName(), renterList.get(1).getName());
//        assertEquals(renterBefore2.getOgrn(), renterList.get(1).getOgrn());
//        assertEquals(renterBefore2.getInn(), renterList.get(1).getInn());
//        assertEquals(renterBefore2.getRegistrDate(), renterList.get(1).getRegistrDate());
//        assertEquals(renterBefore2.getAddress(), renterList.get(1).getAddress());
//        assertEquals(renterBefore2.getDirectorName(), renterList.get(1).getDirectorName());
//        assertEquals(renterBefore2.getContactName(), renterList.get(1).getContactName());
//        assertEquals(renterBefore2.getPhoneNumber(), renterList.get(1).getPhoneNumber());
//
//    }
//
//    @Test
//    @Transactional
//    public void TestDeleteById(){
//
//        renterDAO.deleteRenter(1);
//
//        fillRenterList();
//
//        assertEquals(renterList.size(), 1);
//
//        assertEquals(renterBefore2.getName(), renterList.get(0).getName());
//        assertEquals(renterBefore2.getOgrn(), renterList.get(0).getOgrn());
//        assertEquals(renterBefore2.getInn(), renterList.get(0).getInn());
//        assertEquals(renterBefore2.getRegistrDate(), renterList.get(0).getRegistrDate());
//        assertEquals(renterBefore2.getAddress(), renterList.get(0).getAddress());
//        assertEquals(renterBefore2.getDirectorName(), renterList.get(0).getDirectorName());
//        assertEquals(renterBefore2.getContactName(), renterList.get(0).getContactName());
//        assertEquals(renterBefore2.getPhoneNumber(), renterList.get(0).getPhoneNumber());
//
//    }
//}

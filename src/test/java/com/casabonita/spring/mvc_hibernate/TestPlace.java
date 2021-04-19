package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.*;
import com.casabonita.spring.mvc_hibernate.entity.*;
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
public class TestPlace {

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private MeterDAO meterDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private DataSource dataSource;

    // TODO занести данные в contract_init, meter_init и запустить тест
    @Test
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/meter_init.sql")
    public void testSave(){

        int testNumber = 100;
        String testName = "TestName";
        double testSquare = 200.00;
        int testFloor = 1;
        String testType = "TestType";

        Contract contract = contractDAO.getContract(1);
        Meter meter = meterDAO.getMeter(1);

        Place place = new Place();

        place.setNumber(testNumber);
        place.setName(testName);
        place.setSquare(testSquare);
        place.setFloor(testFloor);
        place.setType(testType);
        place.setContract(contract);
        place.setMeter(meter);

        placeDAO.savePlace(place);

        Request request = new Request(dataSource,
                "SELECT * FROM place");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("number").hasValues(testNumber)
                .column("name").hasValues(testName)
                .column("square").hasValues(testSquare)
                .column("floor").hasValues(testFloor)
                .column("type").hasValues(testType)
                .column("contract").hasValues(contract)
                .column("meter").hasValues(meter);
    }

    // TODO занести данные в contract_init, meter_init, place_init и запустить тест
    @Test
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/place_init.sql")
    public void testGetById() {

        int testNumber = 100;
        String testName = "TestName";
        double testSquare = 200.00;
        int testFloor = 1;
        String testType = "TestType";
        int testContract = contractDAO.getContract(1).getId();
        int testMeter = meterDAO.getMeter(1).getId();

        Place place = placeDAO.getPlace(1);

        assert testNumber == place.getNumber();
        assert testName.equals(place.getName());
        assert testSquare == place.getSquare();
        assert testFloor == place.getFloor();
        assert testType.equals(place.getType());
        assert testContract == place.getContract().getId();
        assert testMeter == place.getMeter().getId();
    }

    // TODO занести данные в contract_init, meter_init, place_init и запустить тест
    @Test
    @Sql("/scripts/contract_init.sql")
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/place_init.sql")
    public void testDeleteById() {

        int testNumber = 100;
        String testName = "TestName";
        double testSquare = 200.00;
        int testFloor = 1;
        String testType = "TestType";
        int testContract = contractDAO.getContract(1).getId();
        int testMeter = meterDAO.getMeter(1).getId();

        placeDAO.deletePlace(1);

        Place place = placeDAO.getPlace(1);

        assert testNumber == place.getNumber();
        assert testName.equals(place.getName());
        assert testSquare == place.getSquare();
        assert testFloor == place.getFloor();
        assert testType.equals(place.getType());
        assert testContract == place.getContract().getId();
        assert testMeter == place.getMeter().getId();
    }

    // TODO занести данные в place_init и запустить тест
    @Test
    @Sql("/scripts/place_init.sql")

    public void testUpdate() {

        Place placeExpected = placeDAO.getPlace(1);

        placeExpected.setNumber(123456);

        placeDAO.savePlace(placeExpected);

        Place placeActual = placeDAO.getPlace(1);

        assert placeExpected.getNumber() == placeActual.getNumber();
        assert placeExpected.getName().equals(placeActual.getName());
        assert placeExpected.getSquare() == placeActual.getSquare();
        assert placeExpected.getFloor() == placeActual.getFloor();
        assert placeExpected.getType().equals(placeActual.getType());
        assert placeExpected.getContract().equals(placeActual.getContract());
        assert placeExpected.getMeter().equals(placeActual.getMeter());
    }

    // TODO занести данные в place_init и запустить тест
    @Test
    @Sql("/scripts/place_init.sql")
    public void testGetAll() {

        List<Place> placeList = placeDAO.getAllPlaces();

        assertThat(placeList).extracting("id", "number", "name", "square", "floor", "type", "contract", "meter")
                .contains(tuple(0, "Romashka", "1076318010548", "6318308609", "443117, Samarskaya oblast, gorod Samara, Orshanskij pereulok, 9", "Prohorov Vladimir Stepanovich", "Yablochkin Vasilij Petrovich", "+7(495)123-45-67"),
                        tuple(1, "Luytik", "1064027042991", "4027073395", "248002, Kaluzhskaya oblast, gorod Kaluga, ulica Saltykova-Shchedrina, 76", "Shumakov Grigorij Anatolevich", "Goncharov Eduard Sergeevich", "+7(495)123-67-45"),
                        tuple(2, "Oduvanchik", "1145476032668", "5406775985", "656056, Altajskij kraj, gorod Barnaul, ploshchad im V.N.Bavarina, dom 2, ofis 910", "Trufanov Anton Yurevich", "Arhipova Nadezhda Viktorovna", "+7(495)123-45-89"),
                        tuple(3, "Margaritka", "1086168005550", "6168024958", "344015, Rostovskaya oblast, gorod Rostov-na-Donu, ulica Eremenko, 58/9", "Pavlickaya Natalya Yakovlevna", "Boldyreva Svetlana Aleksandrovna", "+7(495)123-67-45"));

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

package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
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
public class TestRenterIT {

    @Autowired
    private RenterDAO renterDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testSave() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String testName = "TestName";
        String testOGRN = "TestOGRN";
        String testINN = "TestINN";
        Date testDate = sdf.parse(d);
        String testAddress = "TestAddress";
        String testDirectorName = "TestDirectorName";
        String testContactName = "testContactName";
        String testPhoneNumber = "testPhoneNumber";

        Renter renter = new Renter();

        renter.setName(testName);
        renter.setOgrn(testOGRN);
        renter.setInn(testINN);
        renter.setRegistrDate(testDate);
        renter.setAddress(testAddress);
        renter.setDirectorName(testDirectorName);
        renter.setContactName(testContactName);
        renter.setPhoneNumber(testPhoneNumber);

        renterDAO.saveRenter(renter);

        Request request = new Request(dataSource,
                "SELECT * FROM renter");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(1)
                .column("name").hasValues(testName)
                .column("name").hasValues(testName)
                .column("ogrn").hasValues(testOGRN)
                .column("inn").hasValues(testINN)
                .column("registr_date").hasValues(d) // почему тут String, а не Date
                .column("address").hasValues(testAddress)
                .column("director_name").hasValues(testDirectorName)
                .column("contact_name").hasValues(testContactName)
                .column("phone").hasValues(testPhoneNumber);
    }

    @Test
    @Sql("/scripts/renter_init.sql")
    public void testGetById() throws ParseException{

        int id = 1;
        String name = "Romashka";
        String ogrn = "1076318010548";
        String inn = "6318308609";
        String d = "1995-01-11";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(d);
        String address = "443117, Samarskaya oblast, gorod Samara, Orshanskij pereulok, 9";
        String directorName = "Prohorov Vladimir Stepanovich";
        String contactName = "Yablochkin Vasilij Petrovich";
        String phone = "+7(495)123-45-67";

        // тест с использованием getRenter
        Renter renter = renterDAO.getRenter(id);

        assertThat(id).isEqualTo(renter.getId());
        assertThat(name).isEqualTo(renter.getName());
        assertThat(ogrn).isEqualTo(renter.getOgrn());
        assertThat(inn).isEqualTo(renter.getInn());
        assertThat(date).isEqualTo(renter.getRegistrDate());
        assertThat(address).isEqualTo(renter.getAddress());
        assertThat(directorName).isEqualTo(renter.getDirectorName());
        assertThat(contactName).isEqualTo(renter.getContactName());
        assertThat(phone).isEqualTo(renter.getPhoneNumber());

        // тест без использования getRenter
        Request request = new Request(dataSource,
                "SELECT * FROM renter WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(1)
                .column("name").hasValues(name)
                .column("ogrn").hasValues(ogrn)
                .column("inn").hasValues(inn)
                .column("registr_date").hasValues(d) // почему тут String, а не Date
                .column("address").hasValues(address)
                .column("director_name").hasValues(directorName)
                .column("contact_name").hasValues(contactName)
                .column("phone").hasValues(phone);
    }

    @Test
    @Sql("/scripts/renter_init.sql")
    public void testDeleteById() throws ParseException{

        int id = 1;
        renterDAO.deleteRenter(id);

        List<Renter> renterList = renterDAO.getAllRenters();

        assertThat(renterList)
                .isNotEmpty()
                .hasSize(3);

        String d2 = "2006-05-03";
        String d3 = "2014-03-18";
        String d4 = "2008-12-23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(renterList).extracting(x -> x.getId()).contains(2, 3, 4);
        assertThat(renterList).extracting(x -> x.getName()).contains("Luytik", "Oduvanchik", "Margaritka");
        assertThat(renterList).extracting(x -> x.getOgrn()).contains("1064027042991", "1145476032668", "1086168005550");
        assertThat(renterList).extracting(x -> x.getInn()).contains("4027073395", "5406775985", "6168024958");
        assertThat(renterList).extracting(x -> sdf.parse(x.getRegistrDate().toString())).contains(sdf.parse(d2), sdf.parse(d3), sdf.parse(d4));
        assertThat(renterList).extracting(x -> x.getAddress()).contains("248002, Kaluzhskaya oblast, gorod Kaluga, ulica Saltykova-Shchedrina, 76",
                "656056, Altajskij kraj, gorod Barnaul, ploshchad im V.N.Bavarina, dom 2, ofis 910",
                "344015, Rostovskaya oblast, gorod Rostov-na-Donu, ulica Eremenko, 58/9");
        assertThat(renterList).extracting(x -> x.getDirectorName()).contains("Shumakov Grigorij Anatolevich",
                "Trufanov Anton Yurevich", "Pavlickaya Natalya Yakovlevna");
        assertThat(renterList).extracting(x -> x.getContactName()).contains("Goncharov Eduard Sergeevich",
                "Arhipova Nadezhda Viktorovna", "Boldyreva Svetlana Aleksandrovna");
        assertThat(renterList).extracting(x -> x.getPhoneNumber()).contains("+7(495)123-67-45", "+7(495)123-45-89", "+7(495)123-67-45");
    }

    @Test
    @Sql("/scripts/renter_init.sql")
    public void testUpdate() {

        String name = "Test";

        int id = 1;
        Renter renterExpected = renterDAO.getRenter(id);

        renterExpected.setName(name);

        renterDAO.saveRenter(renterExpected);

        // тест без использования getRenter
        Request request = new Request(dataSource,
                "SELECT * FROM renter WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(renterExpected.getId())
                .column("name").hasValues(name)
                .column("ogrn").hasValues(renterExpected.getOgrn())
                .column("inn").hasValues(renterExpected.getInn())
                .column("registr_date").hasValues(renterExpected.getRegistrDate()) // почему тут String, а не Date
                .column("address").hasValues(renterExpected.getAddress())
                .column("director_name").hasValues(renterExpected.getDirectorName())
                .column("contact_name").hasValues(renterExpected.getContactName())
                .column("phone").hasValues(renterExpected.getPhoneNumber());

        // тест с использованием getRenter
        Renter renterActual = renterDAO.getRenter(id);

        assertThat(renterExpected.getId()).isEqualTo(renterActual.getId());
        assertThat(name).isEqualTo(renterActual.getName());
        assertThat(renterExpected.getOgrn()).isEqualTo(renterActual.getOgrn());
        assertThat(renterExpected.getInn()).isEqualTo(renterActual.getInn());
        assertThat(renterExpected.getRegistrDate()).isEqualTo(renterActual.getRegistrDate());
        assertThat(renterExpected.getAddress()).isEqualTo(renterActual.getAddress());
        assertThat(renterExpected.getDirectorName()).isEqualTo(renterActual.getDirectorName());
        assertThat(renterExpected.getContactName()).isEqualTo(renterActual.getContactName());
        assertThat(renterExpected.getPhoneNumber()).isEqualTo(renterActual.getPhoneNumber());
    }

    // не работает через tuple
    @Test
    @Sql("/scripts/renter_init.sql")
    public void testGetAll() throws ParseException{

        List<Renter> renterList = renterDAO.getAllRenters();

        String d1 = "1995-01-11";
        String d2 = "2006-05-03";
        String d3 = "2014-03-18";
        String d4 = "2008-12-23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(renterList).extracting(x -> x.getId()).contains(1, 2, 3, 4);
        assertThat(renterList).extracting(x -> x.getName()).contains("Romashka", "Luytik", "Oduvanchik", "Margaritka");
        assertThat(renterList).extracting(x -> x.getOgrn()).contains("1076318010548", "1064027042991", "1145476032668", "1086168005550");
        assertThat(renterList).extracting(x -> x.getInn()).contains("6318308609", "4027073395", "5406775985", "6168024958");
        assertThat(renterList).extracting(x -> sdf.parse(x.getRegistrDate().toString())).contains(sdf.parse(d1), sdf.parse(d2), sdf.parse(d3), sdf.parse(d4));
        assertThat(renterList).extracting(x -> x.getAddress()).contains("443117, Samarskaya oblast, gorod Samara, Orshanskij pereulok, 9",
                "248002, Kaluzhskaya oblast, gorod Kaluga, ulica Saltykova-Shchedrina, 76",
                "656056, Altajskij kraj, gorod Barnaul, ploshchad im V.N.Bavarina, dom 2, ofis 910",
                "344015, Rostovskaya oblast, gorod Rostov-na-Donu, ulica Eremenko, 58/9");
        assertThat(renterList).extracting(x -> x.getDirectorName()).contains("Prohorov Vladimir Stepanovich",
                "Shumakov Grigorij Anatolevich", "Trufanov Anton Yurevich", "Pavlickaya Natalya Yakovlevna");
        assertThat(renterList).extracting(x -> x.getContactName()).contains("Yablochkin Vasilij Petrovich",
                "Goncharov Eduard Sergeevich", "Arhipova Nadezhda Viktorovna", "Boldyreva Svetlana Aleksandrovna");
        assertThat(renterList).extracting(x -> x.getPhoneNumber()).contains("+7(495)123-45-67", "+7(495)123-67-45",
                "+7(495)123-45-89", "+7(495)123-67-45");

//        assertThat(renterList)
//                .flatExtracting("name", "ogrn", "inn", "registrDate", "address", "directorName", "contactName", "phoneNumber")
//                .contains(tuple("Romashka", "1076318010548", "6318308609", sdf.parse(d1), "443117, Samarskaya oblast, gorod Samara, Orshanskij pereulok, 9", "Prohorov Vladimir Stepanovich", "Yablochkin Vasilij Petrovich", "+7(495)123-45-67"),
//                        tuple("Luytik", "1064027042991", "4027073395", sdf.parse(d2), "248002, Kaluzhskaya oblast, gorod Kaluga, ulica Saltykova-Shchedrina, 76", "Shumakov Grigorij Anatolevich", "Goncharov Eduard Sergeevich", "+7(495)123-67-45"),
//                        tuple("Oduvanchik", "1145476032668", "5406775985", sdf.parse(d3), "656056, Altajskij kraj, gorod Barnaul, ploshchad im V.N.Bavarina, dom 2, ofis 910", "Trufanov Anton Yurevich", "Arhipova Nadezhda Viktorovna", "+7(495)123-45-89"),
//                        tuple("Margaritka", "1086168005550", "6168024958", sdf.parse(d4), "344015, Rostovskaya oblast, gorod Rostov-na-Donu, ulica Eremenko, 58/9", "Pavlickaya Natalya Yakovlevna", "Boldyreva Svetlana Aleksandrovna", "+7(495)123-67-45"));

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

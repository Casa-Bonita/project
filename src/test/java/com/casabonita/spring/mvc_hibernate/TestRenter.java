package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("test")
@ContextConfiguration
        (
                {
                        "file:src/main/webapp/WEB-INF/applicationContext.xml"
                }
        )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRenter{

    private Renter renterBefore1;
    private Renter renterBefore2;
    private List<Renter> renterList;

    @Autowired
    private RenterDAO renterDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void createRenter() throws ParseException {
        String d1 = "2021-01-01";
        String d2 = "2021-07-04";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(d1);
        Date date2 = sdf.parse(d2);

        renterBefore1 = new Renter();
        renterBefore1.setName("TestName_1");
        renterBefore1.setOgrn("TestOGRN_1");
        renterBefore1.setInn("TestINN_1");
        renterBefore1.setRegistrDate(date1);
        renterBefore1.setAddress("TestAddress_1");
        renterBefore1.setDirectorName("TestDirectorName_1");
        renterBefore1.setContactName("testContactName_1");
        renterBefore1.setPhoneNumber("testPhoneNumber_1");

        renterBefore2 = new Renter();
        renterBefore2.setName("TestName_2");
        renterBefore2.setOgrn("TestOGRN_2");
        renterBefore2.setInn("TestINN_2");
        renterBefore2.setRegistrDate(date2);
        renterBefore2.setAddress("TestAddress_2");
        renterBefore2.setDirectorName("TestDirectorName_2");
        renterBefore2.setContactName("testContactName_2");
        renterBefore2.setPhoneNumber("testPhoneNumber_2");

        renterDAO.saveRenter(renterBefore1);
        renterDAO.saveRenter(renterBefore2);
    }

    @Before
    public void fillRenterList(){
        renterList = jdbcTemplate.query(
                "SELECT * FROM renter",
                new RowMapper<Renter>() {
                    @Override
                    public Renter mapRow(ResultSet resultSet, int i) throws SQLException {
                        Renter renterAfter = new Renter();

                        renterAfter.setName(resultSet.getString("name"));
                        renterAfter.setOgrn(resultSet.getString("ogrn"));
                        renterAfter.setInn(resultSet.getString("inn"));
                        renterAfter.setRegistrDate(resultSet.getDate("registr_date"));
                        renterAfter.setAddress(resultSet.getString("address"));
                        renterAfter.setDirectorName(resultSet.getString("director_name"));
                        renterAfter.setContactName(resultSet.getString("contact_name"));
                        renterAfter.setPhoneNumber(resultSet.getString("phone"));

                        return renterAfter;
                    }
                }
        );
    }

    @After
    public void clearRenterList(){

        renterList.clear();
    }

    @Test
    @Transactional
    public void testInsert(){

        assertFalse(renterList.isEmpty());

        assertEquals(renterBefore1.getName(), renterList.get(0).getName());
        assertEquals(renterBefore1.getOgrn(), renterList.get(0).getOgrn());
        assertEquals(renterBefore1.getInn(), renterList.get(0).getInn());
        assertEquals(renterBefore1.getRegistrDate(), renterList.get(0).getRegistrDate());
        assertEquals(renterBefore1.getAddress(), renterList.get(0).getAddress());
        assertEquals(renterBefore1.getDirectorName(), renterList.get(0).getDirectorName());
        assertEquals(renterBefore1.getContactName(), renterList.get(0).getContactName());
        assertEquals(renterBefore1.getPhoneNumber(), renterList.get(0).getPhoneNumber());

    }

    @Test
    @Transactional
    public void TestGetById(){

        assertFalse(renterList.isEmpty());

        assertEquals(renterBefore1.getName(), renterList.get(0).getName());
        assertEquals(renterBefore1.getOgrn(), renterList.get(0).getOgrn());
        assertEquals(renterBefore1.getInn(), renterList.get(0).getInn());
        assertEquals(renterBefore1.getRegistrDate(), renterList.get(0).getRegistrDate());
        assertEquals(renterBefore1.getAddress(), renterList.get(0).getAddress());
        assertEquals(renterBefore1.getDirectorName(), renterList.get(0).getDirectorName());
        assertEquals(renterBefore1.getContactName(), renterList.get(0).getContactName());
        assertEquals(renterBefore1.getPhoneNumber(), renterList.get(0).getPhoneNumber());

        assertEquals(renterBefore2.getName(), renterList.get(1).getName());
        assertEquals(renterBefore2.getOgrn(), renterList.get(1).getOgrn());
        assertEquals(renterBefore2.getInn(), renterList.get(1).getInn());
        assertEquals(renterBefore2.getRegistrDate(), renterList.get(1).getRegistrDate());
        assertEquals(renterBefore2.getAddress(), renterList.get(1).getAddress());
        assertEquals(renterBefore2.getDirectorName(), renterList.get(1).getDirectorName());
        assertEquals(renterBefore2.getContactName(), renterList.get(1).getContactName());
        assertEquals(renterBefore2.getPhoneNumber(), renterList.get(1).getPhoneNumber());

    }

    @Test
    @Transactional
    public void TestDeleteById(){

        renterDAO.deleteRenter(1);

        fillRenterList();

        assertEquals(renterList.size(), 1);

        assertEquals(renterBefore2.getName(), renterList.get(0).getName());
        assertEquals(renterBefore2.getOgrn(), renterList.get(0).getOgrn());
        assertEquals(renterBefore2.getInn(), renterList.get(0).getInn());
        assertEquals(renterBefore2.getRegistrDate(), renterList.get(0).getRegistrDate());
        assertEquals(renterBefore2.getAddress(), renterList.get(0).getAddress());
        assertEquals(renterBefore2.getDirectorName(), renterList.get(0).getDirectorName());
        assertEquals(renterBefore2.getContactName(), renterList.get(0).getContactName());
        assertEquals(renterBefore2.getPhoneNumber(), renterList.get(0).getPhoneNumber());

    }
}

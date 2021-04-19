package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.*;
import com.casabonita.spring.mvc_hibernate.entity.*;
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
public class TestMeter {

    @Autowired
    private MeterDAO meterDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private ReadingDAO readingDAO;

    @Autowired
    private DataSource dataSource;

    // Нужно ли проверять сохранение списка показаний или только счётчика?
    @Test
    @Sql("/scripts/reading_init.sql")
    public void testSave(){

        Place place = placeDAO.getPlace(1);

        List<Reading> readingList = readingDAO.getAllReadings();

        int testNumber = 123456;

        Meter meter = new Meter();

        meter.setNumber(testNumber);
        meter.setMeterPlace(place);

        meterDAO.saveMeter(meter);

        Request request = new Request(dataSource,
                "SELECT * FROM meter");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("number").hasValues(testNumber)
                .column("meterPlace").hasValues(place.getId());
    }

    // Надо ли извлекать списки и сравнивать их?
    // TODO занести данные в contract_init, payment_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/place_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testGetById(){

        int meterNumber = 123456;;
        int placeId = 1;
        List<Reading> readingList = readingDAO.getAllReadings();

        Meter meter = meterDAO.getMeter(1);

        assert meterNumber == meter.getNumber();
        assert placeId == meter.getMeterPlace().getId();

        Assert.assertEquals(readingList, meter.getReadingsList());
    }

    // Надо ли извлекать списки и сравнивать их?
    // TODO занести данные в place_init, reading_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/place_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testDeleteById(){

        int meterNumber = 123456;;
        int placeId = 1;
        List<Reading> readingList = readingDAO.getAllReadings();

        meterDAO.deleteMeter(1);

        Meter meter = meterDAO.getMeter(1);

        assert meterNumber == meter.getNumber();
        assert placeId == meter.getMeterPlace().getId();

        Assert.assertEquals(readingList, meter.getReadingsList());
    }

    // Надо ли извлекать списки и сравнивать их?
    // TODO занести данные в place_init, reading_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/place_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testUpdate(){

        int meterNumber = 123456;;
        int placeId = 1;
        List<Reading> readingList = readingDAO.getAllReadings();

        Meter meterExpected = meterDAO.getMeter(1);

        meterExpected.setNumber(654321);

        meterDAO.saveMeter(meterExpected);

        Meter meterActual = meterDAO.getMeter(1);

        assert meterNumber == meterActual.getNumber();
        assert placeId == meterActual.getMeterPlace().getId();

        Assert.assertEquals(readingList, meterActual.getReadingsList());
    }

    // нужно ли указывать список показаний readingList
    // TODO занести данные в place_init, reading_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    public void testGetAll(){

        List<Meter> meterList = meterDAO.getAllMeters();

        assertThat(meterList).extracting("id", "number", "meterPlace")
                .contains(tuple(0, "62.01.001", 0),
                        tuple(1, "62.01.002", 1),
                        tuple(2, "62.01.003", 2),
                        tuple(3, "62.01.004", 3),
                        tuple(4, "62.01.005", 4),
                        tuple(5, "62.01.006", 5),
                        tuple(6, "62.01.007", 6));
    }

}

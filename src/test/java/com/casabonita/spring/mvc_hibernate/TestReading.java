package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
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
public class TestReading {

    @Autowired
    private MeterDAO meterDAO;

    @Autowired
    private ReadingDAO readingDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql("/scripts/meter_init.sql")
    public void testSave() throws ParseException {

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Meter meter = meterDAO.getMeter(1);

        int testTransferData = 100;
        Date testTransferDate = sdf.parse(d);

        Reading reading = new Reading();

        reading.setMeter(meter);
        reading.setTransferData(testTransferData);
        reading.setTransferDate(testTransferDate);

        readingDAO.saveReading(reading);

        Request request = new Request(dataSource,
                "SELECT * FROM meter_data");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("data").hasValues(testTransferData)
                .column("data_date").hasValues(d);
    }

    // TODO занести данные в reading_init, meter_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testGetById() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int meterId = 1;
        int transferData = 100;
        Date transferDate = sdf.parse(d);

        Reading reading = readingDAO.getReading(1);

        assert meterId == reading.getMeter().getId();
        assert transferData == reading.getTransferData();
        assert transferDate.equals(reading.getTransferDate());
    }

    // TODO занести данные в reading_init, meter_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testDeleteById() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int meterId = 1;
        int transferData = 100;
        Date transferDate = sdf.parse(d);

        readingDAO.deleteReading(1);

        Reading reading = readingDAO.getReading(1);

        assert meterId == reading.getMeter().getId();
        assert transferData == reading.getTransferData();
        assert transferDate.equals(reading.getTransferDate());
    }

    // TODO занести данные в reading_init, meter_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testUpdate() throws ParseException{

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int meterId = 1;
        int transferData = 100;
        Date transferDate = sdf.parse(d);

        Reading readingExpected = readingDAO.getReading(1);

        readingExpected.setTransferData(200);

        readingDAO.saveReading(readingExpected);

        Reading readingActual = readingDAO.getReading(1);

        assert meterId == readingActual.getMeter().getId();
        assert transferData == readingActual.getTransferData();
        assert transferDate.equals(readingActual.getTransferDate());
    }

    // TODO занести данные в reading_init, meter_init, запустить тест
    @Test
    @Sql("/scripts/meter_init.sql")
    @Sql("/scripts/reading_init.sql")
    public void testGetAll() throws ParseException{

        // TODO указать даты из reading_init
        String d1 = "1995-01-11";
        String d2 = "2006-05-03";
        String d3 = "2014-03-18";
        String d4 = "2008-12-23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Reading> readingList = readingDAO.getAllReadings();

        // TODO занести данные из reading_init
        assertThat(readingList).extracting("id", "meter", "transferData", "transferDate")
                .contains(tuple(0, 0, 100, sdf.parse(d1)),
                        tuple(1, 1, 200, sdf.parse(d2)),
                        tuple(2, 2, 300, sdf.parse(d3)),
                        tuple(3, 3, 400, sdf.parse(d4)));
    }

}
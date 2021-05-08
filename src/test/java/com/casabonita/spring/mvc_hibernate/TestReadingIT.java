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

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@ContextConfiguration
        (
                {
                        "file:src/main/webapp/WEB-INF/applicationContext.xml"
                }
        )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestReadingIT {

    @Autowired
    private MeterDAO meterDAO;

    @Autowired
    private ReadingDAO readingDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testSave() throws ParseException {

        String d = "2021-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int id = 1;
        Meter meter = meterDAO.getMeter(id);

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
                .column("id").hasValues(id)
                .column("data").hasValues(testTransferData)
                .column("data_date").hasValues(d);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testGetById() throws ParseException{

        String d = "2021-03-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int id = 1;
        int meterId = 1;
        int transferData = 11100;
        Date transferDate = sdf.parse(d);

        // проверка через getReading
        Reading reading = readingDAO.getReading(id);

        assertThat(id).isEqualTo(reading.getId());
        assertThat(meterId).isEqualTo(reading.getMeter().getId());
        assertThat(transferData).isEqualTo(reading.getTransferData());
        assertThat(transferDate).isEqualTo(reading.getTransferDate());

        // тест без использования getPayment
        Request request = new Request(dataSource,
                "SELECT * FROM meter_data WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("meter_id").hasValues(meterId)
                .column("data").hasValues(transferData)
                .column("data_date").hasValues(d);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testDeleteById() throws ParseException{

        int id = 1;
        readingDAO.deleteReading(id);

        List<Reading> readingList = readingDAO.getAllReadings();

        assertThat(readingList)
                .isNotEmpty()
                .hasSize(13);

        String d1 = "2021-03-01";
        String d2 = "2021-02-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(readingList).extracting(x -> x.getId()).contains(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        assertThat(readingList).extracting(x -> x.getMeter().getId()).contains(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7);
        assertThat(readingList).extracting(x -> x.getTransferData()).contains(11000, 21900, 21800, 3700, 3800, 6800, 6900, 15900, 15800, 32700, 32600, 29000, 28900);
        assertThat(readingList).extracting(x -> sdf.parse(x.getTransferDate().toString())).contains(sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2));

    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testUpdate() throws ParseException{

        int data = 123456;

        int id = 1;
        Reading readingExpected = readingDAO.getReading(id);

        readingExpected.setTransferData(data);

        readingDAO.saveReading(readingExpected);

        Reading readingActual = readingDAO.getReading(id);

        assertThat(readingExpected.getId()).isEqualTo(readingActual.getId());
        assertThat(readingExpected.getMeter().getId()).isEqualTo(readingActual.getMeter().getId());
        assertThat(data).isEqualTo(readingActual.getTransferData());
        assertThat(readingExpected.getTransferDate()).isEqualTo(readingActual.getTransferDate());
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testGetAll() throws ParseException{

        List<Reading> readingList = readingDAO.getAllReadings();

        String d1 = "2021-03-01";
        String d2 = "2021-02-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(readingList).extracting(x -> x.getId()).contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        assertThat(readingList).extracting(x -> x.getMeter().getId()).contains(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7);
        assertThat(readingList).extracting(x -> x.getTransferData()).contains(11100, 11000, 21900, 21800, 3700, 3800, 6800, 6900, 15900, 15800, 32700, 32600, 29000, 28900);
        assertThat(readingList).extracting(x -> sdf.parse(x.getTransferDate().toString())).contains(sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2), sdf.parse(d1), sdf.parse(d2));

    }
}

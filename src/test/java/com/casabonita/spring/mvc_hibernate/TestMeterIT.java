package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Place;
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
public class TestMeterIT {

    @Autowired
    private MeterDAO meterDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private ReadingDAO readingDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testGetAllMeters(){

        List<Meter> meterList = meterDAO.getAllMeters();

        assertThat(meterList).extracting(x -> x.getId()).contains(1, 2, 3, 4, 5, 6, 7);
        assertThat(meterList).extracting(x -> x.getNumber()).contains("428510", "428511", "428512", "428513", "428514", "428515", "428516");
        assertThat(meterList).extracting(x -> x.getMeterPlace().getId()).contains(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testSaveMeter(){

        int testPlaceId = 1;
        Place place = placeDAO.getPlaceById(testPlaceId);

        String testNumber = "TestNumber";

        Meter meter = new Meter();

        meter.setNumber(testNumber);
        meter.setMeterPlace(place);

        place.setMeter(meter);

        meterDAO.saveMeter(meter);

        Request request = new Request(dataSource,
                "SELECT * FROM meter WHERE id = 8");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(8)
                .column("meter_number").hasValues(testNumber)
                .column("place_id").hasValues(place.getId());
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testGetMeter(){

        int id = 1;
        String meterNumber = "428510";
        int placeId = 1;

        // проверка через getMeter
        Meter meter = meterDAO.getMeter(id);

        assertThat(id).isEqualTo(meter.getId());
        assertThat(meterNumber).isEqualTo(meter.getNumber());
        assertThat(placeId).isEqualTo(meter.getMeterPlace().getId());

        // проверка без getMeter через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM meter WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("meter_number").hasValues(meterNumber)
                .column("place_id").hasValues(placeId);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testGetMeterByPlaceId(){

        int id = 1;
        String meterNumber = "428510";
        int placeId = 1;

        // проверка через getMeter
        Meter meter = meterDAO.getMeterByPlaceId(id);

        assertThat(id).isEqualTo(meter.getId());
        assertThat(meterNumber).isEqualTo(meter.getNumber());
        assertThat(placeId).isEqualTo(meter.getMeterPlace().getId());

        // проверка без getMeter через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM meter WHERE place_id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("meter_number").hasValues(meterNumber)
                .column("place_id").hasValues(placeId);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testGetMeterByNumber(){

        int id = 1;
        String meterNumber = "428510";
        int placeId = 1;

        // проверка через getMeter
        Meter meter = meterDAO.getMeterByNumber(meterNumber);

        assertThat(id).isEqualTo(meter.getId());
        assertThat(meterNumber).isEqualTo(meter.getNumber());
        assertThat(placeId).isEqualTo(meter.getMeterPlace().getId());

        // проверка без getMeter через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM meter WHERE meter_number = '428510'");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("meter_number").hasValues(meterNumber)
                .column("place_id").hasValues(placeId);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testDeleteMeterById(){

        int id = 1;
        meterDAO.deleteMeterById(id);

        // проверка через request удаления строки
        Request requestOne = new Request(dataSource,
                "SELECT * FROM meter WHERE id = 1");

        Assertions.assertThat(requestOne)
                .isEmpty();

        // проверка через request всех оставшихся после удаления строк таблицы
        Request requestAll = new Request(dataSource,
                "SELECT * FROM meter");

        Assertions.assertThat(requestAll)
                .column("id").hasValues(2, 3, 4, 5, 6, 7)
                .column("meter_number").hasValues("428511", "428512", "428513", "428514", "428515", "428516")
                .column("place_id").hasValues(2, 3, 4, 5, 6, 7);

        //проверка через getAllMeters
        List<Meter> meterList = meterDAO.getAllMeters();

        assertThat(meterList).extracting(x -> x.getId()).contains(2, 3, 4, 5, 6, 7);
        assertThat(meterList).extracting(x -> x.getNumber()).contains("428511", "428512", "428513", "428514", "428515", "428516");
        assertThat(meterList).extracting(x -> x.getMeterPlace().getId()).contains(2, 3, 4, 5, 6, 7);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql", "/scripts/reading_init.sql"})
    public void testUpdate(){

        String meterNumber = "123456";;

        int id = 1;
        Meter meterExpected = meterDAO.getMeter(id);

        meterExpected.setNumber(meterNumber);

        meterDAO.saveMeter(meterExpected);

        Meter meterActual = meterDAO.getMeter(id);

        // тест без использования getMeter
        Request request = new Request(dataSource,
                "SELECT * FROM meter WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(meterExpected.getId())
                .column("meter_number").hasValues(meterNumber)
                .column("place_id").hasValues(meterExpected.getMeterPlace().getId());

        // тест с использованием getMeter
        assertThat(meterExpected.getId()).isEqualTo(meterActual.getId());
        assertThat(meterNumber).isEqualTo(meterActual.getNumber());
        assertThat(meterExpected.getMeterPlace().getId()).isEqualTo(meterActual.getMeterPlace().getId());
    }
}

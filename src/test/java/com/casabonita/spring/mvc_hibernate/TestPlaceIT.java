package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
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
public class TestPlaceIT {

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testGetAllPlaces() {

        List<Place> placeList = placeDAO.getAllPlaces();

        assertThat(placeList).extracting(x -> x.getId()).contains(1, 2, 3, 4, 5, 6, 7);
        assertThat(placeList).extracting(x -> x.getNumber()).contains(42, 43, 44, 10, 37, 40, 26);
        assertThat(placeList).extracting(x -> x.getName()).contains("Place-01", "Place-02", "Place-03", "H&M", "Post office", "Cafe", "Union cards");
        assertThat(placeList).extracting(x -> x.getSquare()).contains(45.8, 35.7, 50.1, 39.6, 6.0, 62.0, 2.0);
        assertThat(placeList).extracting(x -> x.getFloor()).contains(2, 2, 2, 3, 1, 0, 0);
        assertThat(placeList).extracting(x -> x.getType()).contains("office", "office", "office", "shop", "island", "other", "atm");
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testSavePlace(){

        int testContractId = 1;
        Contract contract = contractDAO.getContract(testContractId);

        int testNumber = 123456;
        String testName = "TestName";
        Double testSquare = 1111.99;
        int testFloor = 99;
        String testType = "TestType";

        Place place = new Place();

        place.setNumber(testNumber);
        place.setName(testName);
        place.setSquare(testSquare);
        place.setFloor(testFloor);
        place.setType(testType);

        contract.setContractPlace(place);

        placeDAO.savePlace(place);

        Request request = new Request(dataSource,
                "SELECT * FROM place WHERE id = 8");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(8)
                .column("number").hasValues(testNumber)
                .column("name").hasValues(testName)
                .column("square").hasValues(testSquare)
                .column("floor").hasValues(testFloor)
                .column("type").hasValues(testType);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testGetPlaceById() {

        int id = 1;
        int number = 42;
        String name = "Place-01";
        Double square = 45.8;
        int floor = 2;
        String type = "office";

        // проверка через getPlace
        Place place = placeDAO.getPlaceById(id);

        assertThat(id).isEqualTo(place.getId());
        assertThat(number).isEqualTo(place.getNumber());
        assertThat(name).isEqualTo(place.getName());
        assertThat(square).isEqualTo(place.getSquare());
        assertThat(floor).isEqualTo(place.getFloor());
        assertThat(type).isEqualTo(place.getType());

        // проверка без getPlace через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM place WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("number").hasValues(number)
                .column("name").hasValues(name)
                .column("square").hasValues(square)
                .column("floor").hasValues(floor)
                .column("type").hasValues(type);
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testGetPlaceByNumber() {

        int id = 1;
        int number = 42;
        String name = "Place-01";
        Double square = 45.8;
        int floor = 2;
        String type = "office";

        // проверка через getPlace
        Place place = placeDAO.getPlaceByNumber(number);

        assertThat(id).isEqualTo(place.getId());
        assertThat(number).isEqualTo(place.getNumber());
        assertThat(name).isEqualTo(place.getName());
        assertThat(square).isEqualTo(place.getSquare());
        assertThat(floor).isEqualTo(place.getFloor());
        assertThat(type).isEqualTo(place.getType());

        // проверка без getPlace через request из БД
        Request request = new Request(dataSource,
                "SELECT * FROM place WHERE number = 42");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(id)
                .column("number").hasValues(number)
                .column("name").hasValues(name)
                .column("square").hasValues(square)
                .column("floor").hasValues(floor)
                .column("type").hasValues(type);
    }

    @Test
    @Sql({"/scripts/place_init.sql"})
    public void testDeletePlaceById() {

        int id = 1;
        placeDAO.deletePlaceById(id);

        // проверка через request удаления строки
        Request requestOne = new Request(dataSource,
                "SELECT * FROM place WHERE id = 1");

        Assertions.assertThat(requestOne)
                .isEmpty();

        // проверка через request всех оставшихся после удаления строк таблицы
        Request requestAll = new Request(dataSource,
                "SELECT * FROM place");

        Assertions.assertThat(requestAll)
                .column("id").hasValues(2, 3, 4, 5, 6, 7)
                .column("number").hasValues(43, 44, 10, 37, 40, 26)
                .column("name").hasValues("Place-02", "Place-03", "H&M", "Post office", "Cafe", "Union cards")
                .column("square").hasValues(35.7, 50.1, 39.6, 6.0, 62.0, 2.0)
                .column("floor").hasValues(2, 2, 3, 1, 0, 0)
                .column("type").hasValues("office", "office", "shop", "island", "other", "atm");

        //проверка через getAllPlaces
        List<Place> placeList = placeDAO.getAllPlaces();

        assertThat(placeList).extracting(x -> x.getId()).contains(2, 3, 4, 5, 6, 7);
        assertThat(placeList).extracting(x -> x.getNumber()).contains(43, 44, 10, 37, 40, 26);
        assertThat(placeList).extracting(x -> x.getName()).contains("Place-02", "Place-03", "H&M", "Post office", "Cafe", "Union cards");
        assertThat(placeList).extracting(x -> x.getSquare()).contains(35.7, 50.1, 39.6, 6.0, 62.0, 2.0);
        assertThat(placeList).extracting(x -> x.getFloor()).contains(2, 2, 3, 1, 0, 0);
        assertThat(placeList).extracting(x -> x.getType()).contains("office", "office", "shop", "island", "other", "atm");
    }

    @Test
    @Sql({"/scripts/renter_init.sql", "/scripts/place_init.sql", "/scripts/contract_init.sql", "/scripts/meter_init.sql"})
    public void testUpdate() {

        int testNumber = 123456;

        int id = 1;
        Place placeExpected = placeDAO.getPlaceById(id);

        placeExpected.setNumber(testNumber);

        placeDAO.savePlace(placeExpected);

        Place placeActual = placeDAO.getPlaceById(id);

        // тест без использования getPlace
        Request request = new Request(dataSource,
                "SELECT * FROM place WHERE id = 1");

        Assertions.assertThat(request)
                .hasNumberOfRows(1)
                .column("id").hasValues(placeExpected.getId())
                .column("number").hasValues(testNumber)
                .column("name").hasValues(placeExpected.getName())
                .column("square").hasValues(placeExpected.getSquare())
                .column("floor").hasValues(placeExpected.getFloor())
                .column("type").hasValues(placeExpected.getType());

        // тест с использованием getPlace
        assertThat(placeExpected.getId()).isEqualTo(placeActual.getId());
        assertThat(testNumber).isEqualTo(placeActual.getNumber());
        assertThat(placeExpected.getName()).isEqualTo(placeActual.getName());
        assertThat(placeExpected.getSquare()).isEqualTo(placeActual.getSquare());
        assertThat(placeExpected.getFloor()).isEqualTo(placeActual.getFloor());
        assertThat(placeExpected.getType()).isEqualTo(placeActual.getType());
    }
}
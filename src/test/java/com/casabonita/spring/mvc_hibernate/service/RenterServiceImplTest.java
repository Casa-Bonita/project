package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RenterServiceImplTest {

//    Renter renter = new Renter(String renterName, String renterOGRN, String renterINN, Date renterRegistrDate, String renterAddress,
//            String renterDirector, String renterContactName, String renterPhone, Contract renterContract);

    @Test(expected = ParseException.class)
    public void saveRenter() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String renterName = "Test-1";
        String renterOGRN = "OGRN-Test";
        String renterINN = "INN-Test";

        Date renterRegistrDate = null;

        try{
            renterRegistrDate = sdf.parse("2021-03-21");
            Assert.fail("Expected ParseException");
        }
        catch(ParseException ex){
            Assert.assertNotEquals("", ex.getMessage());
        }

        String renterAddress = "Address-Test";
        String renterDirector = "DirectorName-Test";
        String renterContactName = "ContactName-Test";
        String renterPhone = "RenterPhone-Test";
        Contract renterContract
    }
}
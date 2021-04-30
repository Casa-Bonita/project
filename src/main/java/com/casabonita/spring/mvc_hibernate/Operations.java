package com.casabonita.spring.mvc_hibernate;

import com.casabonita.spring.mvc_hibernate.entity.Payment;
import com.casabonita.spring.mvc_hibernate.entity.Reading;

import java.util.List;

public class Operations {

    public int getMax (List<Reading> readingList){

        int max = 0;

        for (int i = 0; i < readingList.size(); i++) {
            if(max < readingList.get(i).getTransferData()){
                max = readingList.get(i).getTransferData();
            }
        }

        return max;
    }

    public int getSumm (List<Payment> paymentList){

        int summ = 0;

        for (int i = 0; i < paymentList.size(); i++) {
            summ = summ + paymentList.get(i).getAmount();
        }

        return summ;
    }
}

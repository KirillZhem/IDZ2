package org.example;

import org.example.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceReportProcessorDatesTest {
    FinanceReport createReport(){
        Payment[] pays = {
                new Payment("Иванов",5,3,2023,1000),
                new Payment("Петров",5,3,2023,500),
                new Payment("Иванов",10,7,2023,300)
        };
        return new FinanceReport(pays, "Автор",1,1,2024);
    }

    @Test
    void totalByDateSumsPayments(){
        assertEquals(1500,FinanceReportProcessor.getTotalByDate(createReport(),"05.03.23"));
    }

    @Test
    void totalByDateOnePayments(){
        assertEquals(300,FinanceReportProcessor.getTotalByDate(createReport(),"10.07.23"));
    }

    @Test
    void totalByDateNoPayments(){
        assertEquals(0,FinanceReportProcessor.getTotalByDate(createReport(),"01.01.23"));
    }

    @Test
    void totalByDateWrongFormatThrowsException(){
        assertThrows(IllegalArgumentException.class,
                ()->FinanceReportProcessor.getTotalByDate(createReport(),"5.3.2023"));
    }

    @Test
    void emptyMonthsSomePayments(){
        ArrayList<String> months = FinanceReportProcessor.getEmptyMonths(createReport(),2023);
        assertEquals(10,months.size());
        assertTrue(months.contains("январь"));
        assertFalse(months.contains("март"));
        assertFalse(months.contains("июль"));
    }

    @Test
    void emptyMonthsYearWithoutPayments(){
        ArrayList<String> months = FinanceReportProcessor.getEmptyMonths(createReport(),2020);
        assertEquals(12,months.size());
    }


}

package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FinanceReportProcessorTest {

    @Test
    void testGetPaymentsBySurnameChar() {
        Payment[] pays = new Payment[]{
                new Payment("Иванов Иван", 1, 1, 2023, 100),
                new Payment("иванов Иван", 2, 2, 2023, 200), // строчная буква
                new Payment("Петров Петр", 3, 3, 2023, 300)
        };
        FinanceReport report = new FinanceReport(pays, "Автор", 1, 1, 2023);

        FinanceReport result = FinanceReportProcessor.getPaymentsBySurnameChar(report, 'И');

        assertEquals(2, result.getPaymentsCount());
        assertEquals("Иванов Иван", result.getPayment(0).getFullName());
        assertEquals("иванов Иван", result.getPayment(1).getFullName());
    }

    @Test
    void testGetPaymentsLessThan() {
        Payment[] pays = new Payment[]{
                new Payment("Иванов", 1, 1, 2023, 100),
                new Payment("Петров", 2, 2, 2023, 200),
                new Payment("Сидоров", 3, 3, 2023, 300)
        };
        FinanceReport report = new FinanceReport(pays, "Автор", 1, 1, 2023);

        FinanceReport result = FinanceReportProcessor.getPaymentsLessThan(report, 250);

        assertEquals(2, result.getPaymentsCount());
        assertEquals(100, result.getPayment(0).getAmount());
        assertEquals(200, result.getPayment(1).getAmount());
    }
}

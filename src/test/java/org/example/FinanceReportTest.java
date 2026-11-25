package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FinanceReportTest {
    @Test
    void testToStringFormat() {
        Payment[] pays = new Payment[]{
                new Payment("Иванов", 1, 2, 2023, 15050)
        };
        FinanceReport report = new FinanceReport(pays, "Петров", 5, 3, 2023);

        String expected = "[Автор: Петров, дата: 5.3.2023, Платежи: [\n" +
                "Плательщик: Иванов, дата: 1.2.2023 сумма: 150 руб. 50 коп.\n" +
                "]]";
        assertEquals(expected, report.toString());
    }

    @Test
    void testCopyConstructorDeepCopy() {
        Payment p1 = new Payment("Иванов", 1, 1, 2023, 100);
        FinanceReport original = new FinanceReport(new Payment[]{p1}, "Бухгалтер", 10, 12, 2023);

        FinanceReport copy = new FinanceReport(original);

        original.getPayment(0).setAmount(9999);

        assertEquals(100, copy.getPayment(0).getAmount());
        assertNotEquals(original.getPayment(0).getAmount(), copy.getPayment(0).getAmount());
    }
}

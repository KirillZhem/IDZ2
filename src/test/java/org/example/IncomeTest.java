package org.example;

import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {
    long[] rub(long... values) {
        long[] result = new long[12];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] * 100;
        }
        return result;
    }

    @Test
    void certificateGetters() {
        IncomeCertificate c = new IncomeCertificate(2020, "Иванов", "ООО", rub(1, 2, 3));
        assertEquals(2020, c.getYear());
        assertEquals("Иванов", c.getCitizen());
        assertEquals("ООО", c.getOrganization());
        assertEquals(300, c.getMonthlyIncome()[2]);
    }

    @Test
    void certificateWrongLengthThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new IncomeCertificate(2020, "Иванов", "ООО", new long[5]));
    }

    @Test
    void certificateCannotBeChangedFromOutside() {
        IncomeCertificate c = new IncomeCertificate(2020, "Иванов", "ООО", rub(10));
        c.getMonthlyIncome()[0] = 99999;
        assertEquals(1000, c.getMonthlyIncome()[0]);
    }

    @Test
    void declarationExampleFromTask() {
        IncomeCertificate c1 = new IncomeCertificate(2020, "Иванов", "Рога и копыта",
                rub(1200, 2300, 24000, 25000));
        IncomeCertificate c2 = new IncomeCertificate(2020, "Иванов", "Казино",
                rub(0, 50, 0, 0));

        IncomeDeclaration d = new IncomeDeclaration(2020, "Иванов", c1, c2);

        assertEquals(235000, d.getMonthlyIncome()[1]);   // 2350,00 руб.
        assertEquals(355000, d.getTotalIncome()[1]);     // 3550,00 руб.
        assertEquals(0, d.getTax()[1]);
        assertEquals(46150, d.getTax()[2]);              // 461,50 руб.
        assertEquals(371150, d.getTax()[3]);             // 3711,50 руб.
    }

    @Test
    void noTaxBelowFirstLimit() {
        IncomeDeclaration d = new IncomeDeclaration(2020, "Иванов",
                new IncomeCertificate(2020, "Иванов", "ООО", rub(24000)));
        assertEquals(0, d.getTotalTax());
    }

    @Test
    void taxAtSecondLimit() {
        IncomeDeclaration d = new IncomeDeclaration(2020, "Иванов",
                new IncomeCertificate(2020, "Иванов", "ООО", rub(240000)));
        assertEquals(2_808_000, d.getTotalTax());
    }

    @Test
    void taxAboveSecondLimit() {
        IncomeDeclaration d = new IncomeDeclaration(2020, "Иванов",
                new IncomeCertificate(2020, "Иванов", "ООО", rub(300000)));
        assertEquals(4_008_000, d.getTotalTax());
    }

    @Test
    void declarationWithoutCertificates() {
        IncomeDeclaration d = new IncomeDeclaration(2020, "Иванов");
        assertEquals(0, d.getTotalTax());
    }

    @Test
    void certificateOfOtherYearThrowsException() {
        IncomeCertificate c = new IncomeCertificate(2019, "Иванов", "ООО", rub(1));
        assertThrows(IllegalArgumentException.class,
                () -> new IncomeDeclaration(2020, "Иванов", c));
    }

    @Test
    void declarationCannotBeChangedFromOutside() {
        IncomeDeclaration d = new IncomeDeclaration(2020, "Иванов",
                new IncomeCertificate(2020, "Иванов", "ООО", rub(100)));
        d.getTax()[0] = 777;
        assertEquals(0, d.getTax()[0]);
    }
}
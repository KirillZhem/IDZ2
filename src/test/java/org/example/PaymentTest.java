package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testGettersAndSetters() {
        Payment p = new Payment();
        p.setFullName("Иванов Иван");
        p.setDay(15);
        p.setMonth(5);
        p.setYear(2023);
        p.setAmount(15050);

        assertEquals("Иванов Иван", p.getFullName());
        assertEquals(15, p.getDay());
        assertEquals(5, p.getMonth());
        assertEquals(2023, p.getYear());
        assertEquals(15050, p.getAmount());
    }

    @Test
    void testEqualsAndHashCode() {
        Payment p1 = new Payment("Иванов Иван", 15, 5, 2023, 15050);
        Payment p2 = new Payment("Иванов Иван", 15, 5, 2023, 15050);
        Payment p3 = new Payment("Петров Петр", 15, 5, 2023, 15050);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testToString() {
        Payment p = new Payment("Иванов Иван", 15, 5, 2023, 15050);
        String expected = "Плательщик: Иванов Иван, дата: 15.5.2023 сумма: 150 руб. 50 коп.";
        assertEquals(expected, p.toString());
    }
}

package org.example;

public class FinanceReport {
    private final Payment[] payments;
    private final String author;
    private final int day;
    private final int month;
    private final int year;

    public FinanceReport(Payment[] payments, String author, int day, int month, int year) {
        this.payments = payments;
        this.author = author;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public FinanceReport(FinanceReport other) {
        this.author = other.author;
        this.day = other.day;
        this.month = other.month;
        this.year = other.year;

        this.payments = new Payment[other.payments.length];
        for (int i = 0; i < other.payments.length; i++) {
            Payment p = other.payments[i];
            this.payments[i] = new Payment(p.getFullName(), p.getDay(), p.getMonth(), p.getYear(), p.getAmount());
        }
    }

    public int getPaymentsCount() {
        return payments.length;
    }

    public Payment getPayment(int index) {
        return payments[index];
    }

    public void setPayment(int index, Payment payment) {
        payments[index] = payment;
    }

    public String getAuthor() { return author; }
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Автор: %s, дата: %d.%d.%d, Платежи: [\n", author, day, month, year));

        for (Payment p : payments) {
            sb.append(String.format("Плательщик: %s, дата: %d.%d.%d сумма: %d руб. %02d коп.\n",
                    p.getFullName(), p.getDay(), p.getMonth(), p.getYear(),
                    p.getAmount() / 100, p.getAmount() % 100));
        }
        sb.append("]]");
        return sb.toString();
    }
}
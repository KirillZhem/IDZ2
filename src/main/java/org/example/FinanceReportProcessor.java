package org.example;

import java.util.ArrayList;

public class FinanceReportProcessor {

    public static FinanceReport getPaymentsBySurnameChar(FinanceReport report, char c) {
        ArrayList<Payment> resultList = new ArrayList<>();

        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p.getFullName() != null && !p.getFullName().isEmpty()) {
                char firstChar = p.getFullName().toLowerCase().charAt(0);
                if (firstChar == Character.toLowerCase(c)) {
                    resultList.add(p);
                }
            }
        }

        Payment[] newPayments = resultList.toArray(new Payment[0]);
        return new FinanceReport(newPayments, report.getAuthor(), report.getDay(), report.getMonth(), report.getYear());
    }

    public static FinanceReport getPaymentsLessThan(FinanceReport report, int amount) {
        ArrayList<Payment> resultList = new ArrayList<>();

        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p.getAmount() < amount) {
                resultList.add(p);
            }
        }

        Payment[] newPayments = resultList.toArray(new Payment[0]);
        return new FinanceReport(newPayments, report.getAuthor(), report.getDay(), report.getMonth(), report.getYear());
    }
}

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
    public static long getTotalByDate(FinanceReport report, String date){
        if (date == null || date.isEmpty()){
            throw new IllegalArgumentException("Дата должна быть в формате дд.мм.гг");
        }
        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = 2000 + Integer.parseInt(date.substring(6,8));

        long sum = 0;
        for(int i = 0;i<report.getPaymentsCount();i++){
            Payment p = report.getPayment(i);
            if (p.getDay() == day && p.getMonth() == month && p.getYear() == year){
                sum+=p.getAmount();
            }
        }
        return sum;
    }
    public static ArrayList<String> getEmptyMonths(FinanceReport report, int year){
        String[] names = {"январь","февраль","март","апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        boolean[] hasPayment = new boolean[12];

        for(int i = 0;i<report.getPaymentsCount();i++){
            Payment p = report.getPayment(i);
            if(p.getYear() == year){
                hasPayment[p.getMonth()-1]=true;
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for(int m = 0;m<12;m++){
            if(!hasPayment[m]){
                result.add(names[m]);
            }
        }
        return result;
    }

}

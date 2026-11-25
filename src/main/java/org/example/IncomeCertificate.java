package org.example;

public class IncomeCertificate {
    private final int year;
    private final String citizen;
    private final String organization;
    private final long[] monthlyIncome = new long [12];

    public IncomeCertificate(int year, String citizen, String organization, long[] income){
        if (income == null || income.length != 12){
            throw new IllegalArgumentException("Нужно 12 сумм");
        }

        this.year = year;
        this.citizen = citizen;
        this.organization = organization;
        System.arraycopy(income, 0, this.monthlyIncome, 0, 12);
    }
    public int getYear(){
        return year;
    }
    public String getOrganization(){
        return organization;
    }
    public String getCitizen(){
        return citizen;
    }

    public long[] getMonthlyIncome() {
        return monthlyIncome.clone();
    }
}
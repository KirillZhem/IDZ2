package org.example;

public class IncomeDeclaration {
    private final int year;
    private final String citizen;
    private final long[] monthlyIncome = new long [12];
    private final long[] totalIncome = new long[12];
    private final long[] tax = new long[12];

    public IncomeDeclaration(int year,String citizen, IncomeCertificate... certificates){
        this.year = year;
        this.citizen = citizen;

        for(IncomeCertificate cert : certificates){
            if (cert.getYear()!=year){
                throw new IllegalArgumentException("Год справки не совпадает с годом декларации");
            }
            long[] income = cert.getMonthlyIncome();
            for(int m = 0; m<12;m++){
                monthlyIncome[m]+=income [m];
            }
        }
        long sum = 0;
        for (int m = 0;m<12;m++){
            sum +=monthlyIncome[m];
            totalIncome[m] = sum;
            tax[m]=calculateTax(sum);
        }
    }
    private long calculateTax(long total){
        long limit1 = 2_400_000;
        long limit2 = 24_000_000;

        if(total<=limit1){
            return 0;
        }
        if(total<=limit2){
            return (total - limit1)*13/100;
        }
        return (limit2 - limit1)*13/100+(total-limit2)*20/100;
    }

    public int getYear(){
        return year;
    }

    public String getCitizen(){
        return citizen;
    }

    public long[] getMonthlyIncome(){
        return monthlyIncome.clone();
    }

    public long[] getTotalIncome(){
        return totalIncome.clone();
    }

    public long[] getTax(){
        return tax.clone();
    }

    public long getTotalTax(){
        return tax[11];
    }
}

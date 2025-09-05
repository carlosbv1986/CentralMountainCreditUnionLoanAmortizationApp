/**
 * This class stores loan information and creates a
 * text file containing an amortization report.
 */
package com.centralmountaincreditunionloanamortizationapp.models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Amortization {

    private double loanAmount;    // Loan Amount
    private double interestRate;  // Annual Interest Rate
    private double loanBalance;   // Monthly Balance
    private double term;          // Term factor for calculation
    private double payment;       // Monthly Payment
    private int loanYears;        // Years of Loan

    /**
     * Constructor accepts the loan amount, the annual interest rate, and the
     * number of years of the loan.
     *
     * @param loan The loan amount.
     * @param rate The annual interest rate.
     * @param years The number of years of the loan.
     */
    public Amortization(double loan, double rate, int years) {
        this.loanAmount = loan;
        this.loanBalance = loan;
        this.interestRate = rate;
        this.loanYears = years;
        calcPayment();
    }

    /**
     * Calculates the monthly payment amount.
     */
    private void calcPayment() {
        // Calculate the term factor
        term = Math.pow(1 + interestRate / 12.0, 12.0 * loanYears);

        // Calculate monthly payment
        payment = (loanAmount * interestRate / 12.0 * term) / (term - 1);
    }

    /**
     * Returns the total number of payments for the loan.
     *
     * @return The number of monthly payments.
     */
    public int getNumberOfPayments() {
        return 12 * loanYears;
    }

    /**
     * Saves the amortization report to a file.
     *
     * @param filename The name of the file to create.
     * @throws IOException If an I/O error occurs.
     */
    public void saveReport(String filename) throws IOException {
        double monthlyInterest;
        double principal;

        try (PrintWriter outputFile = new PrintWriter(new FileWriter(filename))) {

            // Print monthly payment
            outputFile.printf("Monthly Payment: $%.2f%n", payment);

            // Print header
            outputFile.println("Month\tInterest\tPrincipal\tBalance");
            outputFile.println("-----------------------------------------------");

            // Amortization table
            for (int month = 1; month <= getNumberOfPayments(); month++) {
                monthlyInterest = interestRate / 12.0 * loanBalance;

                if (month != getNumberOfPayments()) {
                    principal = payment - monthlyInterest;
                } else {
                    principal = loanBalance;
                    payment = loanBalance + monthlyInterest;
                }

                // Update balance
                loanBalance -= principal;

                // Print monthly data
                outputFile.printf("%d\t%.2f\t\t%.2f\t\t%.2f%n",
                        month, monthlyInterest, principal, loanBalance);
            }
        }
    }

    public String generateReport() {
        double monthlyInterest;
        double principal;
        double balance = loanAmount; // use a local copy, do NOT modify loanBalance!

        StringBuilder sb = new StringBuilder();

        // Print monthly payment
        sb.append(String.format("Monthly Payment: $%.2f%n", payment));

        // Print header
        sb.append("Month\tInterest\tPrincipal\tBalance\n");
        sb.append("-----------------------------------------------\n");

        // Amortization table
        for (int month = 1; month <= getNumberOfPayments(); month++) {
            monthlyInterest = interestRate / 12.0 * balance;

            if (month != getNumberOfPayments()) {
                principal = payment - monthlyInterest;
            } else {
                principal = balance; // last payment
            }

            balance -= principal;

            sb.append(String.format("%d\t%.2f\t\t%.2f\t\t%.2f%n",
                    month, monthlyInterest, principal, balance));
        }

        return sb.toString();
    }

    /**
     * Clears all loan data and resets the object to an empty state.
     */
    public void clearData() {
        loanAmount = 0.0;
        interestRate = 0.0;
        loanBalance = 0.0;
        term = 0.0;
        payment = 0.0;
        loanYears = 0;
    }

    // Getter methods
    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getLoanYears() {
        return loanYears;
    }

    public double getPayment() {
        return payment;
    }
}

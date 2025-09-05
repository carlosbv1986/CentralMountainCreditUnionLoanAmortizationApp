package com.centralmountaincreditunionloanamortizationapp.main;

import com.centralmountaincreditunionloanamortizationapp.models.Amortization;
import javax.swing.JOptionPane; // For the JOptionPane class
import java.io.IOException;

/**
 * This program displays a loan amortization report. It saves the report in
 * LoanAmortization.txt and asks the user if they want to run another report.
 */
public class LoanReportTester {

    public static void main(String[] args) throws IOException {
        String input;          // To hold user input
        double loan;           // Loan amount
        double interestRate;   // Annual interest rate
        int years;             // Years of the loan
        char again;            // To indicate if loop should repeat

        do {
            // Get the loan amount
            input = JOptionPane.showInputDialog("Enter the loan amount:");
            loan = Double.parseDouble(input);

            // Validate the loan amount (must be non-negative)
            while (loan < 0) {
                input = JOptionPane.showInputDialog("Invalid amount. Enter the loan amount:");
                loan = Double.parseDouble(input);
            }

            // Get the annual interest rate
            input = JOptionPane.showInputDialog("Enter the annual interest rate:");
            interestRate = Double.parseDouble(input);

            // Validate the interest rate (must be non-negative)
            while (interestRate < 0) {
                input = JOptionPane.showInputDialog("Invalid amount. Enter the annual interest rate:");
                interestRate = Double.parseDouble(input);
            }

            // Get the years of the loan
            input = JOptionPane.showInputDialog("Enter the years of the loan:");
            years = Integer.parseInt(input);

            // Validate the number of years (must be non-negative)
            while (years < 0) {
                input = JOptionPane.showInputDialog("Invalid amount. Enter the years of the loan:");
                years = Integer.parseInt(input);
            }

            // Create and initialize an Amortization object
            Amortization am = new Amortization(loan, interestRate, years);

            // Save the report
            am.saveReport("LoanAmortization.txt");
            JOptionPane.showMessageDialog(null, "Report saved to the file LoanAmortization.txt.");

            // Ask if the user wants to run another report
            input = JOptionPane.showInputDialog("Would you like to run another report? Enter Y for yes or N for no:");
            again = input.charAt(0);

        } while (again == 'Y' || again == 'y');

        System.exit(0);
    }
}

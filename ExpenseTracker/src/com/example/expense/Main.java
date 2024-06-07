package com.example.expense;

import com.example.expense.service.ExpenseService;

import java.util.Scanner;

public class Main {
    private static ExpenseService expenseService = new ExpenseService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Expense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total Expenses");
            System.out.println("4. View Total Expenses by Category");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    viewTotalExpenses();
                    break;
                case 4:
                    viewTotalExpensesByCategory();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addExpense() {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        
        if (amount <= 0 || description.isEmpty() || category.isEmpty()) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        expenseService.addExpense(description, amount, category);
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        System.out.println("Expenses:");
        expenseService.viewExpenses().forEach(System.out::println);
    }

    private static void viewTotalExpenses() {
        System.out.println("Total Expenses: " + expenseService.getTotalExpenses());
    }

    private static void viewTotalExpensesByCategory() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.println("Total Expenses for " + category + ": " + expenseService.getTotalExpensesByCategory(category));
    }
}
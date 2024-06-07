package com.example.expense.service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.expense.model.Expense;
import com.example.expense.util.FileUtil;

public class ExpenseService {
    private List<Expense> expenses;
    private final String FILE_PATH = "/Users/vishwa/Desktop/ExpenseTracker/data/expenses.txt";

    public ExpenseService() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(String description, double amount, String category) {
        Expense expense = new Expense(description, amount, category, LocalDate.now());
        expenses.add(expense);
        saveExpenses();
    }

    public List<Expense> viewExpenses() {
        return new ArrayList<>(expenses);
    }

    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getTotalExpensesByCategory(String category) {
        return expenses.stream()
                       .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                       .mapToDouble(Expense::getAmount)
                       .sum();
    }

    private void loadExpenses() {
        try {
            expenses = FileUtil.readExpenses(FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveExpenses() {
        try {
            FileUtil.writeExpenses(FILE_PATH, expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
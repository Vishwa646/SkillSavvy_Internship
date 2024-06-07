package com.example.expense.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.expense.model.Expense;

public class FileUtil {
    public static void writeExpenses(String filePath, List<Expense> expenses) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(expenses);
        }
    }

    public static List<Expense> readExpenses(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Expense>) ois.readObject();
        }
    }
}
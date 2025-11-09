package org.example.service;

import org.example.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCSVReader {
    private static final String DEFAULT_CSV_FILE = "employees.csv";

    public List<Employee> readEmployees() throws IOException {
        return readEmployees(DEFAULT_CSV_FILE);
    }

    public List<Employee> readEmployees(String filename) throws IOException {
        try (InputStream inputStream = getFileFromResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<Employee> employees = new ArrayList<>();
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Employee employee = new Employee(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            Double.parseDouble(parts[3]),
                            parts[4].isEmpty() ? null : Integer.parseInt(parts[4])
                    );
                    employees.add(employee);
                }
            }
            return employees;
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("Resource file '" + fileName + "' not found!");
        }

        return inputStream;
    }
}

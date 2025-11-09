package org.example;

import org.example.model.Employee;
import org.example.service.EmployeeCSVReader;
import org.example.service.OrganizationAnalyzer;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            EmployeeCSVReader csvReader = new EmployeeCSVReader();
            List<Employee> employees = csvReader.readEmployees();
            OrganizationAnalyzer analyzer = new OrganizationAnalyzer(employees);

            System.out.println("Analyzing organization:");
            List<String> analysisResults = analyzer.analyzeOrganization();
            analysisResults.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error reading the employees data: " + e.getMessage());
        }
    }
}

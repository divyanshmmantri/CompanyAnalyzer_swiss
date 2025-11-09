package org.example;

import org.example.model.Employee;
import org.example.service.OrganizationAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationAnalyzerTest {

    @Test
    void testReportingLineAnalysis() {
        // Create a deep reporting structure
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Level1", "Boss", 100000.0, null),
            new Employee(2, "Level2", "Manager", 90000.0, 1),
            new Employee(3, "Level3", "Manager", 80000.0, 2),
            new Employee(4, "Level4", "Manager", 70000.0, 3),
            new Employee(5, "Level5", "Manager", 60000.0, 4),
            new Employee(6, "Level6", "Manager", 50000.0, 5),
                new Employee(7, "Level7", "Employee", 30000.0, 6)
        );

        OrganizationAnalyzer analyzer = new OrganizationAnalyzer(employees);
        List<String> results = analyzer.analyzeLongReportingLines();

        // Employee Level6 has 5 managers above them (exceeds limit of 4)
        assertTrue(results.stream().anyMatch(s -> s.contains("Level7")));
    }
}

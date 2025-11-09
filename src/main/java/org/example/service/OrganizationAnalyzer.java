package org.example.service;

import org.example.model.Employee;
import org.example.service.analyzer.AnalyzerRule;
import org.example.service.analyzer.SalaryAnalyzerRule;
import org.example.service.analyzer.ReportingLineAnalyzerRule;

import java.util.*;
import java.util.stream.Collectors;

public class OrganizationAnalyzer {
    private static final double MIN_SALARY_RATIO = 1.2; // 20% more
    private static final double MAX_SALARY_RATIO = 1.5; // 50% more
    private static final int MAX_REPORTING_LINE = 4;

    private final List<Employee> employeeList;
    private final Map<Integer, Employee> employees;
    private final List<AnalyzerRule> rules;

    public OrganizationAnalyzer(List<Employee> employeeList) {
        this.employeeList = employeeList;
        this.employees = employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, e -> e));
        this.rules = new ArrayList<>();
        initializeDefaultRules();
    }

    private void initializeDefaultRules() {
        rules.add(new SalaryAnalyzerRule(MIN_SALARY_RATIO, MAX_SALARY_RATIO));
        rules.add(new ReportingLineAnalyzerRule(MAX_REPORTING_LINE));
    }

    public void addAnalyzerRule(AnalyzerRule rule) {
        rules.add(rule);
    }

    public List<String> analyzeOrganization() {
        List<String> results = new ArrayList<>();

        // Analyze Hierarchy Reporting Lines
        results.addAll(new ReportingLineAnalyzerRule(MAX_REPORTING_LINE)
                .analyze(null, employeeList));

        // Analyze salary for each managers
        for (Employee manager : employees.values()) {
            List<Employee> subordinates = findSubordinates(manager);
            if (!subordinates.isEmpty()) {
                results.addAll(new SalaryAnalyzerRule(MIN_SALARY_RATIO, MAX_SALARY_RATIO)
                        .analyze(manager, subordinates));
            }
        }

        return results;
    }

    private List<Employee> findSubordinates(Employee manager) {
        if (manager == null) return new ArrayList<>();
        return employeeList.stream()
                .filter(e -> e.getManagerId() != null && e.getManagerId() == manager.getId())
                .collect(Collectors.toList());
    }

    public List<String> analyzeSalaries() {
        return new SalaryAnalyzerRule(MIN_SALARY_RATIO, MAX_SALARY_RATIO)
                .analyze(null, employeeList);
    }

    public List<String> analyzeLongReportingLines() {
        return new ReportingLineAnalyzerRule(MAX_REPORTING_LINE)
                .analyze(null, employeeList);
    }
}

package org.example.service.analyzer;

import org.example.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportingLineAnalyzerRule implements AnalyzerRule {
    private final int maxReportingLine;
    private Map<Integer, Employee> employeeMap;

    public ReportingLineAnalyzerRule(int maxReportingLine) {
        this.maxReportingLine = maxReportingLine;
    }

    @Override
    public List<String> analyze(Employee manager, List<Employee> employeeList) {
        List<String> results = new ArrayList<>();
        this.employeeMap = employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, e -> e));

        for (Employee employee : employeeList) {
            if (employee.getManagerId() == null) continue;

            int depth = countManagersToTop(employee);
            if (depth > maxReportingLine) {
                results.add(String.format("Employee %s %s has %d managers between them and CEO, exceeding maximum of %d",
                        employee.getFirstName(), employee.getLastName(), depth, maxReportingLine));
            }
        }
        return results;
    }

    private int countManagersToTop(Employee employee) {
        int depth = 0;
        Integer currentManagerId = employee.getManagerId();
        while (currentManagerId != null) {
            depth++;
            Employee manager = employeeMap.get(currentManagerId);
            if (manager == null) break;
            currentManagerId = manager.getManagerId();
        }

        return depth - 1; // Excluding CEO
    }
}

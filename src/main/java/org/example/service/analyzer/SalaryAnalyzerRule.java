package org.example.service.analyzer;

import org.example.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class SalaryAnalyzerRule implements AnalyzerRule {
    private final double minSalaryRatio;
    private final double maxSalaryRatio;

    public SalaryAnalyzerRule(double minSalaryRatio, double maxSalaryRatio) {
        this.minSalaryRatio = minSalaryRatio;
        this.maxSalaryRatio = maxSalaryRatio;
    }

    @Override
    public List<String> analyze(Employee manager, List<Employee> subordinates) {
        List<String> results = new ArrayList<>();

        if (subordinates.isEmpty()) {
            return results;
        }

        double avgSubordinateSalary = subordinates.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        double minRequiredSalary = avgSubordinateSalary * minSalaryRatio;
        double maxAllowedSalary = avgSubordinateSalary * maxSalaryRatio;

        if (manager.getSalary() < minRequiredSalary) {
            double deficit = minRequiredSalary - manager.getSalary();
            results.add(String.format("%s earns %.2f less than minimum required (%.2f)",
                    manager, deficit, minRequiredSalary));
        } else if (manager.getSalary() > maxAllowedSalary) {
            double excess = manager.getSalary() - maxAllowedSalary;
            results.add(String.format("%s earns %.2f more than maximum allowed (%.2f)",
                    manager, excess, maxAllowedSalary));
        }

        return results;
    }
}

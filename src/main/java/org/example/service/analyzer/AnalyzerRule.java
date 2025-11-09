package org.example.service.analyzer;

import org.example.model.Employee;
import java.util.List;

public interface AnalyzerRule {
    List<String> analyze(Employee employee, List<Employee> subordinates);
}

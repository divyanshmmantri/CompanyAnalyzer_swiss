# CompanyAnalyzer

A Java application that analyzes organizational structure and salary distributions.



## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/example/
│   │       ├── Main.java
│   │       ├── model/
│   │       │   └── Employee.java
│   │       └── service/
│   │           ├── EmployeeCSVReader.java
│   │           ├── OrganizationAnalyzer.java
│   │           └── analyzer/
│   │               ├── AnalyzerRule.java
│   │               ├── ReportingLineAnalyzerRule.java
│   │               └── SalaryAnalyzerRule.java
│   └── resources/
│       └── employees.csv
└── test/
    └── java/
        └── org/example/
            └── OrganizationAnalyzerTest.java
```
## How to Run

1. Clone the repository
2. Make sure you have Java and Maven installed
3. Run the following commands:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## Input Data Format
CSV File is defined under `src/main/resources/employees.csv`. with sample data.
The application expects a CSV file with the following columns:
- Id
- firstName
- lastName
- salary
- managerId

Example:
```csv
Id,firstName,lastName,salary,managerId
123,Joe,Doe,120000,
124,Martin,Chekov,65000,123
```
# Output of the program for the above csv file
<img width="1867" height="662" alt="image" src="https://github.com/user-attachments/assets/9337b098-5519-4764-a05a-d2fe1fba23f0" />



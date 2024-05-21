package service;

import org.springframework.stereotype.Service;
import model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;
    private final Map<String, Employee> employees = new HashMap<>();

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (employeeList.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (employeeList.contains(key)) {
            employees.remove(key);
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (employeeList.contains(key)) {
        return employees.get(key);
        }

        throw new EmployeeNotFoundException();
    }

    private String buildKey(String name, String surname) {
        return name + surname;
    }

    @Override
    public Collection<Employee> findAll() {
        return Collection.unmodifiableList(employeeList);
    }
}

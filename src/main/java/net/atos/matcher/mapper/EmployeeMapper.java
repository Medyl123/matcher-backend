package net.atos.matcher.mapper;

import net.atos.matcher.dto.EmployeeDto;
import net.atos.matcher.entity.Employee;
import net.atos.matcher.entity.Role;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getCode(),
                employee.getRole().name()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getCode(),
                Role.valueOf(employeeDto.getRole())
        );
    }
}

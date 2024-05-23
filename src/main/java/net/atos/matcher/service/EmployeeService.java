package net.atos.matcher.service;

import net.atos.matcher.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
    void deleteEployee(Long employeeId);
    boolean verifyEmail(String email);
    String generateValidationCode();
    void sendValidationCode(String email, String code);


}

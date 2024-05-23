package net.atos.matcher.controller;

import lombok.AllArgsConstructor;
import net.atos.matcher.dto.EmployeeDto;
import net.atos.matcher.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    // build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        if(!employeeService.verifyEmail(employeeDto.getEmail())){
                    //ResponseEntity.badRequest().body(" Cette adresse n'est pas prise en compte");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if(employeeService.verifyEmail(employeeDto.getEmail())){
            String code = employeeService.generateValidationCode();
            employeeService.sendValidationCode(employeeDto.getEmail(), code);
            EmployeeDto saveEmployee = employeeService.createEmployee(employeeDto);

            return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
        } else {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


      //  EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
      //  return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // build Get All Employee REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // build update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    // build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!.");
    }
}

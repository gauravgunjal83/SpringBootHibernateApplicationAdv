package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")

    public ResponseEntity<String>signUp(@RequestBody Employee employee){
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp Done Successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")

    public ResponseEntity<String>signIn(@PathVariable String empEmailId,String empPassword){
        employeeServiceImpl.signIn(empEmailId,empPassword);
        return ResponseEntity.ok("SignIn Done Successfuly");
    }
    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }
    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>>getDataByName(@PathVariable String empName ){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpName().equals(empName)).collect(Collectors.toList()));
    }
    @GetMapping("/getdatabycontactnumber/{empContactNumber}")

    public ResponseEntity<Employee>getDataByContactNumber(@PathVariable long empContactNumber){
        return  ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()).get(0));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>>sortBySalary(){
        return  ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>>SortByName(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyid")

    public ResponseEntity<List<Employee>>sortDataById(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }
    @PostMapping("/savebulkofdata")
    public ResponseEntity<String>saveBulkOfData(@RequestBody List<Employee> employees){
        employeeServiceImpl.saveBulkOfData(employees);
        return ResponseEntity.ok("Bulk Of Data Saved Successfully");

    }
    @PutMapping("/updatedata/{empId}")

    public ResponseEntity<String>updateData(@PathVariable int empId,@RequestBody Employee employee){
        employeeServiceImpl.updateData(empId,employee);
        return ResponseEntity.ok("Data Updated Successfully");
    }
    @DeleteMapping("/deletedatabyid/{empId}")

    public ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted for Id Successfully");
    }
    @DeleteMapping("/deletealldata")
    public ResponseEntity<String>deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }
}

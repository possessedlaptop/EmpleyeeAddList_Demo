package com.FinalDAE.demo.api;

import com.FinalDAE.demo.entity.Employee;
import com.FinalDAE.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public ModelAndView getAllEmployees() {
        ModelAndView modelAndView = new ModelAndView("employees");
        List<Employee> employees = employeeService.getAllEmployees();
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("newEmployee", new Employee());
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView saveEmployee(@ModelAttribute("newEmployee") Employee newEmployee, Model model) {
        // Check if the provided email is already registered
        Employee existingEmployee = employeeService.findByEmail(newEmployee.getEmail());
        if (existingEmployee != null) {
            List<Employee> employees = employeeService.getAllEmployees();
            model.addAttribute("employees", employees);
            model.addAttribute("newEmployee", new Employee());
            model.addAttribute("error", "Email already registered.");
            return new ModelAndView("employees", model.asMap());
        }

        employeeService.saveEmployee(newEmployee);
        return new ModelAndView("redirect:/employee");
    }
}

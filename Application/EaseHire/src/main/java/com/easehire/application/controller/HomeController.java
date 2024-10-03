package com.easehire.application.controller;

import com.easehire.application.dto.ConnectDTO;
import com.easehire.application.service.CollegeService;
import com.easehire.application.service.ConnectService;
import com.easehire.application.service.StudentService;
import com.easehire.application.utility.Debug;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private final ConnectService connectService;
    private final CollegeService collegeService;
    private final StudentService studentService;
    @RequestMapping("/")
    public ModelAndView showDashboard() {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        long totalStudents = studentService.countAllStudents();
        long totalColleges = collegeService.countAllColleges();
        long totalConnects = connectService.countAllConnects();
        Debug.debug(totalStudents, totalColleges, totalConnects);
        List<ConnectDTO> connectDTOS = connectService.readActiveConnects();

        modelAndView.addObject("totalStudents", totalStudents);
        modelAndView.addObject("totalColleges", totalColleges);
        modelAndView.addObject("totalConnects", totalConnects);
        modelAndView.addObject("connectDTOS", connectDTOS);
        return modelAndView;
    }
}

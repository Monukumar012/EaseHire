package com.easehire.application.controller;


import com.easehire.application.service.StudentService;
import com.easehire.application.utility.Debug;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public ModelAndView show() {
        return new ModelAndView("student", "studentDTOS", studentService.readAll());
    }


    @PostMapping("/upload-result")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam("studentFile")MultipartFile studentFile,
                                           @RequestParam(name = "connectId", required = false) Long connectId,
                                           @RequestParam(name = "collegeCode", required = false) String collegeCode){
        try{
            Debug.debug(this.getClass(), "upload: ","connectId = ",connectId, " collegeCode: ",collegeCode);
            Long cnt=studentService.uploadStudent(studentFile, connectId, collegeCode);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data':{'cnt':%d}}",cnt));
        }catch (Exception e){
            // redirect to error page
            Debug.debug(this.getClass(), "upload: ","Exception = ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }

    @GetMapping("/download-template")
    public ResponseEntity<Resource> downloadTemplate() {
        Resource resource = new ClassPathResource("static/file/student_info.xlsx");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student_info.xlsx")
                .body(resource);
    }

}

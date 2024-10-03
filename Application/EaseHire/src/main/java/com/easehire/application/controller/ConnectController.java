package com.easehire.application.controller;

import com.easehire.application.dto.CollegeDTO;
import com.easehire.application.dto.ConnectDTO;
import com.easehire.application.service.CollegeService;
import com.easehire.application.service.ConnectService;
import com.easehire.application.utility.JsonResponse;
import com.easehire.application.utility.enums.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/connect")
@Slf4j
public class ConnectController {
    private final ConnectService connectService;
    private final CollegeService collegeService;

    @GetMapping
    public ModelAndView show() {
        return new ModelAndView("connect","connectDTOS", connectService.readAll());
    }

    @GetMapping("/add-connect")
    public ModelAndView saveForm(){
        List<CollegeDTO> collegeDTOS = collegeService.readAll();

        ModelAndView modelAndView=new ModelAndView("connect-form", "collegeDTOS", collegeDTOS);
        modelAndView.addObject("connectDTO", new ConnectDTO());

        return modelAndView;
    }

    @PostMapping("/add-connect")
    @ResponseBody
    public ResponseEntity<JsonResponse> save(@ModelAttribute ConnectDTO connectDTO, @ModelAttribute CollegeDTO collegeDTO){
        log.info("save: connectDTO = {} | collegeDTO = {}" , connectDTO, collegeDTO);
        try{
            connectDTO.setCollegeDTO(collegeDTO);
            Boolean response = connectService.create(connectDTO);
            return ResponseEntity.ok().body(new JsonResponse(true, null, response));
        }catch (Exception e){
            log.error("save: exception = {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, e.getMessage(), null));
        }
    }


    @GetMapping("/update-connect")
    public ModelAndView updateForm(@RequestParam(name = "connectId", required = false) Long connectId){
        List<CollegeDTO> collegeDTOS = collegeService.readAll();

        ModelAndView modelAndView=new ModelAndView("connect-form", "collegeDTOS", collegeDTOS);
        modelAndView.addObject("connectDTO", connectService.read(connectId));

        return modelAndView;
    }

    @PutMapping("/update-connect")
    @ResponseBody
    public ResponseEntity<JsonResponse> update(@ModelAttribute ConnectDTO connectDTO, CollegeDTO collegeDTO,
                                         @RequestParam(name = "status", required = false) Status status){
        log.info("update: connectDTO = {} | collegeDTO = {}", connectDTO, collegeDTO);
        try{
            connectDTO.setCollegeDTO(collegeDTO);
            connectDTO.setStatus(status);
            ConnectDTO response = connectService.update(connectDTO);
            return ResponseEntity.ok().body(new JsonResponse(true, null, response));
        }catch (Exception e){
            log.error("update: exception = {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-connect")
    @ResponseBody
    public ResponseEntity<JsonResponse> delete(@RequestParam(name = "connectId", required = false) Long connectId,
                                         @RequestParam(name = "status", required = false) Status status){
        log.info("delete: connectId = {} status = {}",connectId, status);
        try{
            connectService.delete(connectId, status);
            return ResponseEntity.ok().body(new JsonResponse(true, null, "Connect Deleted Successfully!"));
        }catch (Exception e){
            log.error("delete: exception = {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, e.getMessage(), null));
        }
    }
}

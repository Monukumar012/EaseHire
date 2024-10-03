package com.easehire.application.controller;

import com.easehire.application.dto.CollegeDTO;
import com.easehire.application.service.CollegeService;
import com.easehire.application.service.CoordinatorService;
import com.easehire.application.service.TpoService;
import com.easehire.application.utility.Debug;
import com.easehire.application.utility.JsonResponse;
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
@RequestMapping("/college")
@Slf4j
public class CollegeController {
    private final CollegeService collegeService;
    private final TpoService tpoService;
    private final CoordinatorService coordinatorService;

    @GetMapping
    public ModelAndView show() {
        // clear both list
        tpoService.clear();
        coordinatorService.clear();

        List<CollegeDTO> collegeDTOS = collegeService.readAll();
        return new ModelAndView("college", "collegeDTOS", collegeDTOS);
    }

    @GetMapping("/add-college")
    public ModelAndView saveForm(){
        return new ModelAndView("college-form", "collegeDTO", new CollegeDTO());
    }
    @PostMapping("/add-college")
    @ResponseBody
    public ResponseEntity<JsonResponse> save(@ModelAttribute CollegeDTO collegeDTO){
        try{
            collegeDTO.setTpos(tpoService.readAll());
            collegeDTO.setCoordinators(coordinatorService.readAll());
            CollegeDTO response = collegeService.create(collegeDTO);

            // clear both list
            tpoService.clear();
            coordinatorService.clear();

            return ResponseEntity.ok().body(new JsonResponse(true, null, response));
        }catch (Exception e){
            log.error("save: exception = {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/update-college")
    public ModelAndView updateForm(@RequestParam(name = "collegeId", required = false) Long collegeId){
        CollegeDTO collegeDTO=collegeService.read(collegeId);

        tpoService.setTpoDTOS(collegeDTO.getTpos());
        coordinatorService.setCoordinatorDTOS(collegeDTO.getCoordinators());

        Debug.debug(CollegeController.class, "updateCollegeForm: ",collegeDTO);
        return new ModelAndView("college-form", "collegeDTO", collegeDTO);
    }

    @PutMapping("/update-college")
    @ResponseBody
    public ResponseEntity<JsonResponse> update(@ModelAttribute CollegeDTO collegeDTO){
        log.info("delete: collegeDTO = {}",collegeDTO);
        try{
            collegeDTO.setTpos(tpoService.readAll());
            collegeDTO.setCoordinators(coordinatorService.readAll());

            CollegeDTO response = collegeService.update(collegeDTO);

            // clear both list
            tpoService.clear();
            coordinatorService.clear();

            return ResponseEntity.ok().body(new JsonResponse(true, null, response));
        }catch (Exception e){
            log.error("update: exception = {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, e.getMessage(), null));
        }
    }


    @DeleteMapping("/delete-college")
    @ResponseBody
    public ResponseEntity<JsonResponse> delete(@RequestParam(name = "collegeId", required = false) Long collegeId){
        log.info("delete: collegeId = {}",collegeId);
        try{
            collegeService.delete(collegeId);
            return ResponseEntity.ok().body(new JsonResponse(true, null, "College Deleted!"));
        }catch (Exception e){
            log.error("Delete: exception = {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, e.getMessage(), null));
        }
    }
}

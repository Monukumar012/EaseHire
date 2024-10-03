package com.easehire.application.controller;

import com.easehire.application.dto.CoordinatorDTO;
import com.easehire.application.service.CoordinatorService;
import com.easehire.application.utility.Debug;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/coordinator")
public class CoordinatorController {
    private final CoordinatorService coordinatorService;

    @PostMapping("/add-coordinator")
    public ResponseEntity<String> save(@ModelAttribute CoordinatorDTO coordinatorDTO){
        try{
            boolean response=coordinatorService.create(coordinatorDTO);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data':%s}",response));
        }catch (Exception e){
            Debug.debug(CoordinatorController.class, "Save= ","Exception: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }

    @DeleteMapping("/delete-coordinator")
    public ResponseEntity<String> delete(@RequestParam(name = "id", required = false) Integer coordinatorId){
        Debug.debug("delete: ", "coordinatorId: ", coordinatorId, " coordinatorDTOS: ",coordinatorService.readAll());
        try{
            CoordinatorDTO response=coordinatorService.deleteAtIndex(coordinatorId);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data': %s}", response));
        }catch (Exception e){
            Debug.debug(CoordinatorController.class, "delete= ","Exception: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }

    @PutMapping("/update-coordinator")
    public ResponseEntity<String> update(@RequestParam(name = "id", required = false) Integer coordinatorId, @ModelAttribute CoordinatorDTO coordinatorDTO){
        Debug.debug(CoordinatorController.class, "update: ", "coordinatorId: ", coordinatorId, " coordinatorDTO: ",coordinatorDTO,"\nList: ",coordinatorService.readAll());
        try{
            CoordinatorDTO response=coordinatorService.update(coordinatorDTO, coordinatorId);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data': %s}", response));
        }catch (Exception e){
            Debug.debug(CoordinatorController.class, "delete= ","Exception: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }
}

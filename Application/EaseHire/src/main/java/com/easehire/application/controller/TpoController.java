package com.easehire.application.controller;

import com.easehire.application.dto.TpoDTO;
import com.easehire.application.service.TpoService;
import com.easehire.application.utility.Debug;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/tpo")
public class TpoController {

    private final TpoService tpoService;

    @PostMapping("/add-tpo")
    public ResponseEntity<String> save(@ModelAttribute TpoDTO tpoDTO){
        System.out.println(tpoDTO);
        try{
            boolean response=tpoService.create(tpoDTO);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data':%s}",response));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }

    @DeleteMapping("/delete-tpo")
    public ResponseEntity<String> delete(@RequestParam(name = "id", required = false) Integer tpoId){
        try{
            TpoDTO response=tpoService.deleteAtIndex(tpoId);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data': %s}", response));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }

    @PutMapping("/update-tpo")
    public ResponseEntity<String> update(@RequestParam(name = "id", required = false) Integer tpoId, @ModelAttribute TpoDTO tpoDTO){
        Debug.debug("update: ", "tpoId: ", tpoId, " TpoDTO: ",tpoDTO);
        try{
            TpoDTO response=tpoService.update(tpoDTO, tpoId);
            return ResponseEntity.ok().body(String.format("{'success':true, 'error':null,'data': %s}", response));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{'success':false, 'error':%s,'data':null}",e.getMessage()));
        }
    }
}

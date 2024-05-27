package com.amithag.backendproximity.controller;

import com.amithag.backendproximity.api.response.GoogleAutoResponse;
import com.amithag.backendproximity.dto.LocationDto;
import com.amithag.backendproximity.dto.LocationRoleDto;
import com.amithag.backendproximity.dto.SpecialistDto;
import com.amithag.backendproximity.dto.SpecialistNoRoleDto;
import com.amithag.backendproximity.model.Specialist;
import com.amithag.backendproximity.service.GoogleAutocompletionServiceImpl;
import com.amithag.backendproximity.service.SpecialistService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/specialist")
public class SpecialistController {
    @Autowired
    SpecialistService specialistService;



    @GetMapping("all")
    public List<SpecialistDto> findAll(){
        return specialistService.findAllStudents();
    }
    @PostMapping("put")
    public ResponseEntity<?> saveSpecialistWithRole(@RequestBody SpecialistDto specialistDto){
       boolean response= specialistService.saveSpecialist(specialistDto);
       if(!response){
           return new ResponseEntity<>("you already have registered",HttpStatus.CONFLICT);
       }
        return new ResponseEntity<>("sucessfully registered,Thank you ",HttpStatus.OK);

    }
//    @GetMapping("delete")
//    public String  delete(){
//        specialistService.deleteAllSpecialistValue();
//        return "Deleted";
//    }
    @PostMapping("nearest")
    public ResponseEntity<List<SpecialistDto>> getNearestSpecialist(@RequestBody LocationRoleDto locationRoleDto){
        return new ResponseEntity<List<SpecialistDto>>(specialistService.findNearestSpecialistByLocationRole(locationRoleDto),HttpStatus.OK);

    }
    @GetMapping("/email/{email}")
    public SpecialistNoRoleDto findSpecialistByEmail(@PathVariable String email){
        return specialistService.findSpecialistByEmail(email);
    }

    @GetMapping("/id/{id}")
    public SpecialistNoRoleDto findSpecialistById(@PathVariable Long id){
        return specialistService.findSpecialistById(id);
    }


}

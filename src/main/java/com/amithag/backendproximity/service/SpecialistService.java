package com.amithag.backendproximity.service;

import com.amithag.backendproximity.dto.LocationDto;
import com.amithag.backendproximity.dto.LocationRoleDto;
import com.amithag.backendproximity.dto.SpecialistDto;
import com.amithag.backendproximity.dto.SpecialistNoRoleDto;
import com.amithag.backendproximity.model.Specialist;

import java.util.List;
import java.util.Map;

public interface SpecialistService {
       List<SpecialistDto> findAllStudents();
       boolean saveSpecialist(SpecialistDto specialistDto);
//     List<SpecialistDto> getSpecialist();
//     void insertSpecialistValue(SpecialistDto specialistdto);
//     void deleteAllSpecialistValue();
       List<SpecialistDto> findNearestSpecialistByLocationRole(LocationRoleDto locationRoleDto);
    SpecialistNoRoleDto findSpecialistByEmail(String email);
    SpecialistNoRoleDto findSpecialistById(Long id);

}

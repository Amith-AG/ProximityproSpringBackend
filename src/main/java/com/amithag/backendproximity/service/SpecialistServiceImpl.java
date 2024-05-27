package com.amithag.backendproximity.service;

import com.amithag.backendproximity.dto.LocationRoleDto;
import com.amithag.backendproximity.dto.SpecialistDto;
import com.amithag.backendproximity.dto.SpecialistNoRoleDto;
import com.amithag.backendproximity.model.Role;
import com.amithag.backendproximity.model.Specialist;
import com.amithag.backendproximity.respositry.RoleRepo;
import com.amithag.backendproximity.respositry.SpecialistRepo;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecialistServiceImpl implements SpecialistService{

    @Autowired
    private SpecialistRepo specialistRepo;
    @Autowired
    private RoleRepo roleRepo;

    //Helper
    public Geometry wktToGeometry(String wellKnownText) throws ParseException {
        return new WKTReader().read(wellKnownText);
    }

    enum RoleEnum{
        machanic,
        painter,
        electrician,
        cleaner
    }


    @Override
    public boolean saveSpecialist(SpecialistDto specialistDto) {
        try {
            Specialist specialist=new Specialist();
            specialist.setId(specialistDto.getId());
            specialist.setName(specialistDto.getName());
            specialist.setEmail(specialistDto.getEmail());
            specialist.setPhone(specialistDto.getPhone());
            specialist.setPlace(specialistDto.getPlace());
            specialist.setLocation((Point) wktToGeometry(String.format("POINT(%s %s)",specialistDto.getLat().toString(),specialistDto.getLng().toString())));
            Set<Role> roleSet=new HashSet<>();
            for (Map<String,String>nameMap:specialistDto.getRoles()){


//TODO implement type safety for specialist plumber mechanic ...etc


                Role role=roleRepo.findByName(nameMap.get("name"));
                if(role !=null){
                    roleSet.add(role);
                    continue;
                }
                Role role2 = new Role();
                role2.setName(nameMap.get("name"));
                roleSet.add(roleRepo.save(role2));
            }
            specialist.setRoles(roleSet);

            specialistRepo.save(specialist);
            return true;
        }
        catch (Exception e){
            System.out.println("something went wrong"+ e);
            return false;

        }

    }
        @Override
    public List<SpecialistDto> findAllStudents() {
        List<Map<String,Object>> specialistMap=specialistRepo.findAllSpecialist();
        List<SpecialistDto> specialistList = new ArrayList<>();
         for(Map<String,Object> data:specialistMap){
             SpecialistDto specialistDto1=new SpecialistDto();
             specialistDto1.setId((Long)data.get("id"));
             specialistDto1.setName((String)data.get("name"));
             specialistDto1.setLat((Double) data.get("lat"));
             specialistDto1.setLng((Double)data.get("lng"));
             specialistDto1.setEmail((String)data.get("email"));
             specialistDto1.setPhone((String)data.get("phone"));
             specialistDto1.setPlace((String) data.get("place"));
             specialistList.add(specialistDto1);
        }
        return specialistList;
    }
    @Override
    public List<SpecialistDto> findNearestSpecialistByLocationRole(LocationRoleDto locationRoleDto) {
        List<Map<String,Object>> specialist=specialistRepo.getNearestSpecialistRole(locationRoleDto.getLat().toString(),locationRoleDto.getLng().toString(),locationRoleDto.getName());

//        if(StringUtils.isEmpty(locationRoleDto.getName())){
//           specialist =specialistRepo.getNearestSpecialist(locationRoleDto.getLat().toString(),locationRoleDto.getLng().toString());
//
//        }
//        else {
//            specialist=specialistRepo.getNearestSpecialistRole(locationRoleDto.getLat().toString(),locationRoleDto.getLng().toString(),locationRoleDto.getName());
//        }

        List<SpecialistDto> specialistList=new ArrayList<>();
        for(Map<String,Object>data:specialist){
            SpecialistDto specialistDto1=new SpecialistDto();
            specialistDto1.setId((Long) data.get("id"));
            specialistDto1.setName((String)data.get("name"));
            specialistDto1.setLat((Double) data.get("lat"));
            specialistDto1.setLng((Double)data.get("lng"));
            specialistDto1.setEmail((String)data.get("email"));
            specialistDto1.setPhone((String)data.get("phone"));
            specialistDto1.setPlace((String) data.get("place"));
            specialistList.add(specialistDto1);
        }
        return specialistList;
    }

    @Override
    public SpecialistNoRoleDto findSpecialistByEmail(String email) {
        Map<String,Object> specialist=specialistRepo.findSpecialistByEmail(email);
        if(specialist ==null) return  null;
        SpecialistNoRoleDto specialistNoRoleDto=new SpecialistNoRoleDto();
        specialistNoRoleDto.setId((Long) specialist.get("id"));
        specialistNoRoleDto.setName((String)specialist.get("name"));
        specialistNoRoleDto.setLat((Double) specialist.get("lat"));
        specialistNoRoleDto.setLng((Double)specialist.get("lng"));
        specialistNoRoleDto.setEmail((String)specialist.get("email"));
        specialistNoRoleDto.setPhone((String)specialist.get("phone"));
        specialistNoRoleDto.setPlace((String) specialist.get("place"));

        return specialistNoRoleDto;
    }

    @Override
    public SpecialistNoRoleDto findSpecialistById(Long id) {
        Map<String,Object> specialist=specialistRepo.findSpecialistById(id.toString());
        if(specialist ==null) return  null;
        SpecialistNoRoleDto specialistNoRoleDto=new SpecialistNoRoleDto();
        specialistNoRoleDto.setId((Long) specialist.get("id"));
        specialistNoRoleDto.setName((String)specialist.get("name"));
        specialistNoRoleDto.setLat((Double) specialist.get("lat"));
        specialistNoRoleDto.setLng((Double)specialist.get("lng"));
        specialistNoRoleDto.setEmail((String)specialist.get("email"));
        specialistNoRoleDto.setPhone((String)specialist.get("phone"));
        specialistNoRoleDto.setPlace((String) specialist.get("place"));
        List<String> roleList=roleRepo.findRoleBySid(id);
        specialistNoRoleDto.setRoles(roleList);


        return specialistNoRoleDto;
    }

}

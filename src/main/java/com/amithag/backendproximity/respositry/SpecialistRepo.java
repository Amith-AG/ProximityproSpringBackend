package com.amithag.backendproximity.respositry;

import com.amithag.backendproximity.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpecialistRepo extends JpaRepository<Specialist,Integer> {




    @Query(value = "SELECT id, name, ST_X(location) as lat , ST_Y(location) AS lng ,phone,email,place FROM specialist",nativeQuery = true)
    List<Map<String, Object>>findAllSpecialist();

    @Query(value = "SELECT sp.id, sp.name, ST_X(sp.location) as lat , ST_Y(sp.location) AS lng ,sp.phone,sp.email,sp.place, " +
            "       ST_Distance_Sphere(location, ST_GeomFromText(CONCAT('POINT(', ?1, ' ', ?2, ')'))) AS distance " +
            "FROM specialist sp " +
            "JOIN specialist_role sr ON sp.id=sr.specialist_id "+
            "JOIN role ro ON ro.id=sr.role_id "+
            "WHERE ro.name="+"?3 "+
            "ORDER BY distance " +
            "LIMIT 10;", nativeQuery = true)
    List<Map<String, Object>> getNearestSpecialistRole(String lat, String lng,String name);

    @Query(value = "SELECT sp.id, sp.name, ST_X(sp.location) as lat , ST_Y(sp.location) AS lng ,sp.phone,sp.email,sp.place, " +
            "       ST_Distance_Sphere(location, ST_GeomFromText(CONCAT('POINT(', ?1, ' ', ?2, ')'))) AS distance " +
            "FROM specialist sp " +
            "ORDER BY distance " +
            "LIMIT 10;", nativeQuery = true)
    List<Map<String, Object>> getNearestSpecialist(String lat, String lng);

    @Query(value = "SELECT id, name, ST_X(location) as lat , ST_Y(location) AS lng ,phone,email,place FROM specialist WHERE email= :email ",nativeQuery = true)
    Map<String,Object> findSpecialistByEmail(String email);

    @Query(value = "SELECT id, name, ST_X(location) as lat , ST_Y(location) AS lng ,phone,email,place FROM specialist WHERE id= :id ",nativeQuery = true)
    Map<String,Object> findSpecialistById(String id);


}
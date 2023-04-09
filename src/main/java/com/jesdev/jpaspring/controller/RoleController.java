package com.jesdev.jpaspring.controller;

import com.jesdev.jpaspring.entities.RoleEntity;
import com.jesdev.jpaspring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getRoles(){
        return new ResponseEntity<>(service.getRoles(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<RoleEntity> createRoles(@RequestBody RoleEntity role){
        return new ResponseEntity<>(service.createRole(role),HttpStatus.CREATED);
    }

    @PutMapping("/{roleid}")
    public ResponseEntity<RoleEntity> updateRoles(@PathVariable("roleid") Integer roleid,@RequestBody RoleEntity role){
        return new ResponseEntity<>(service.updateRole(roleid,role),HttpStatus.OK);
    }

    @DeleteMapping("/{roleid}")
    public ResponseEntity<Void> deleteRole(@PathVariable("roleid")Integer roleid){
        service.deleteRole(roleid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

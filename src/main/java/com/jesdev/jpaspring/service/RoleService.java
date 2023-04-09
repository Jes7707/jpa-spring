package com.jesdev.jpaspring.service;

import com.jesdev.jpaspring.entities.RoleEntity;
import com.jesdev.jpaspring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<RoleEntity> getRoles(){
        return repository.findAll();
    }

    public RoleEntity createRole(RoleEntity role){
        return repository.save(role);
    }

    public RoleEntity updateRole(Integer roleid, RoleEntity role){
        Optional<RoleEntity> result = repository.findById(roleid);
        if (result.isPresent()){
            return repository.save(role);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %s doesn't exists",roleid));
        }
    }

    public void deleteRole(Integer roleid) {
        Optional<RoleEntity> result = repository.findById(roleid);
        if (result.isPresent()){
            repository.delete(result.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role id %s doesn't exists",roleid));
        }
    }
}

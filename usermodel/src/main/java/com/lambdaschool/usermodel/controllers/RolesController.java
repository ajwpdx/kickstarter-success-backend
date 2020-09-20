package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/roles")
public class RolesController
{

    @Autowired
    RoleService roleService;


    // GET http://localhost:2019/roles/roles
    @GetMapping(value = "/roles",
            produces = "application/json")
    public ResponseEntity<?> listRoles()
    {
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles,
                                    HttpStatus.OK);
    }


    // GET http://localhost:2019/roles/role/3
    @GetMapping(value = "/role/{roleId}",
            produces = "application/json")
    public ResponseEntity<?> getRoleById(
            @PathVariable
                    Long roleId)
    {
        Role r = roleService.findRoleById(roleId);
        return new ResponseEntity<>(r,
                                    HttpStatus.OK);
    }


    // POST http://localhost:2019/roles/role
    @PostMapping(value = "/role",
            consumes = "application/json")
    public ResponseEntity<?> addNewRole(
            @Valid
            @RequestBody
                    Role newRole)
    {
        // ids are not recognized by the Post method
        newRole.setRoleid(0);
        newRole = roleService.save(newRole);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleid}")
                .buildAndExpand(newRole.getRoleid())
                .toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(null,
                                    responseHeaders,
                                    HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/roles/role/2
    @PutMapping(value = "/role/{roleid}",
            consumes = {"application/json"})
    public ResponseEntity<?> putUpdateRole(
            @PathVariable
                    long roleid,
            @Valid
            @RequestBody
                    Role newRole)
    {
        newRole = roleService.update(roleid,
                                     newRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

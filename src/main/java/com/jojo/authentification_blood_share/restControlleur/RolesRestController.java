package com.jojo.authentification_blood_share.restControlleur;

import com.jojo.authentification_blood_share.entities.Roles;
import com.jojo.authentification_blood_share.entities.Users;
import com.jojo.authentification_blood_share.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")

public class RolesRestController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(path="/addrole",method = RequestMethod.POST)
    public Roles createProduit(@RequestBody Roles roles) {
        return roleService.addRole(roles);
    }

    @RequestMapping(value="/delRole/{id}",method = RequestMethod.DELETE)
    public void deleteProduit(@PathVariable("id") Long id)
    {
        roleService.deleteRole(id);
    }

    @GetMapping("/all")
    public List<Roles> findAllUsers() {
        return roleService.getAllRoles();
    }
}

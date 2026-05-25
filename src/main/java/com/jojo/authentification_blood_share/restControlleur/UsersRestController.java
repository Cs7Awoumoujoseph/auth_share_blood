package com.jojo.authentification_blood_share.restControlleur;

import com.jojo.authentification_blood_share.entities.Users;
import com.jojo.authentification_blood_share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UsersRestController {

    @Autowired
    UserService userService;

    @GetMapping("all")
    public List<Users> findAllUsers() {
        return userService.findAllUser();
    }
    @GetMapping("/verifyEmail/{token}")
    public Users verifyEmail(@PathVariable("token") String token){
        return userService.validateToken(token);
    }
}

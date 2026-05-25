package com.jojo.authentification_blood_share.securiy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojo.authentification_blood_share.Repository.UsersRepository;
import com.jojo.authentification_blood_share.entities.Users;
import com.jojo.authentification_blood_share.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static com.jojo.authentification_blood_share.securiy.SecParams.EXP_TIME;
import static com.jojo.authentification_blood_share.securiy.SecParams.SECRET;

//UsernamePasswordAuthenticationFilter est une classe de spring boot....pas ma classe
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UsersRepository usersRepository; //on veut sauvegarder la date de connection

    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UsersRepository usersRepository) {
        super();
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository; //injection de dependance par le constructeur ou lieu de authowired
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        Users user =null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(),
                    Users.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authenticationManager.
                authenticate(new
                        UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException
    {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User)
                        authResult.getPrincipal();

        //add la derniere date de connexion
        Users user = usersRepository.findByUsername(springUser.getUsername());
        if(user==null){
            usersRepository.save(user) ;
        }

        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au-> {
            roles.add(au.getAuthority());
        });
        String jwt = JWT.create().
                withSubject(springUser.getUsername()).
                withArrayClaim("roles", roles.toArray(new String[roles.size()])).
                withExpiresAt(new Date(System.currentTimeMillis()+EXP_TIME)).
                sign(Algorithm.HMAC256(SECRET));
        response.addHeader("Authorization", jwt);
    }

    //va afficher dans le json les messages de erreur si le user est innactif
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        if (failed instanceof DisabledException) {//si plusieur type de auth excepception,il saagit de une exception disabled : user non actif
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            Map<String, Object> data = new HashMap<>();
//sa va  retourner un objet json du genre {
//    "errorCause": "disabled",
//    "message": "L'utilisateur est désactivé !"
//}
            data.put("errorCause", "disabled");
            data.put("message", "L'utilisateur est désactivé !");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(data);
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.flush();

        } else {
            //si i9l ne sagit pas de une disabled exception faire :
            super.unsuccessfulAuthentication(request, response, failed);
        }
    }
}

package com.riznuchok.resources;

import com.riznuchok.entity.User;
import com.riznuchok.service.SecUserDetailService;
import com.riznuchok.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RestController
@Slf4j
@Path("/api/user")
@Produces("application/json")
@PermitAll
public class UserResources {

    @Autowired
    UserService userService;

    @Autowired
    SecUserDetailService secUserDetailService;

    @POST
    public Response registration(User user){

        log.info("user: " + user);
        userService.registration(user);
        return Response.ok()
                .entity(user).build();
    }

    @POST
    @Path("login")
    public Response login(User user){
        log.info("user: " + user);
        UserDetails userLoged = secUserDetailService.loadUserByUsername(user.getEmail());
        return Response.ok()
                .entity(userLoged).build();
    }



}

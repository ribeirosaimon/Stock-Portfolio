package com.saimon.Stock.portfolio.Controllers;

import com.saimon.Stock.portfolio.DTO.CredentialsDTO;
import com.saimon.Stock.portfolio.DTO.TokenDTO;
import com.saimon.Stock.portfolio.DTO.UserDTO;
import com.saimon.Stock.portfolio.Database.Model.MyUser;
import com.saimon.Stock.portfolio.Security.Jwt.JwtService;
import com.saimon.Stock.portfolio.Services.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody MyUser user) {
        userDetailService.saveUser(user);
        return new UserDTO(user.getName(), user.getLogin(), user.isAdmin());
    }

    @PostMapping("/auth")
    public TokenDTO authentication(@RequestBody CredentialsDTO credentials) throws Exception {
        try {
            MyUser user = new MyUser(credentials.getLogin(), credentials.getPassword());
            UserDetails authenticatedUser = userDetailService.authenticated(user);
            String token = jwtService.generateToken(user);
            return new TokenDTO(user.getLogin(), token);
        } catch (Exception e) {
            throw new Exception("ERROR TOKEN");
        }
    }
}

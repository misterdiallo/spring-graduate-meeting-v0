package com.linyiuniversity.graduate.microservices.userservices.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeaderEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeadersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentsRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeacherEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeachersRepository;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.LoginUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserWithRoleDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UsersService usersService;
    private Environment env;
    StudentsRepository studentsRepository;
    LeadersRepository leadersRepository;
    TeachersRepository teachersRepository;

    public AuthenticationFilter(
            UsersService usersService,
            Environment env,
            AuthenticationManager authenticationManager
    ) {
        this.usersService = usersService;
        this.env =  env;
        super.setAuthenticationManager(authenticationManager);
    }

    public AuthenticationFilter(
            StudentsRepository studentsRepository,
            LeadersRepository leadersRepository,
            TeachersRepository teachersRepository
    ) {
        this.studentsRepository = studentsRepository;
        this.leadersRepository = leadersRepository;
        this.teachersRepository = teachersRepository;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws AuthenticationException {
        try {

            LoginUserRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginUserRequestModel.class);
            return  getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getLogin(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth
    ) throws IOException, ServletException  {
        String userName =  ((User) auth.getPrincipal()).getUsername();
        UserDTO userDetails = usersService.getUserDetailsByUserId(userName);
        System.out.println(Long.parseLong(env.getProperty("config.token.expiration_time")));
        System.out.println(env.getProperty("config.token.secret"));
        String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("config.token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512,env.getProperty("config.token.secret"))
                .compact();
        String role = usersService.getRole(userName);
        res.addHeader("token", token);
        res.addHeader("userId", userDetails.getUserId());
        res.addHeader("role", role);
         // TODO : token check and renew expiration.
        // TODO : OAuth..



    }
}

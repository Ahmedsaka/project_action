package io.medalytics.projectactions.Controller;

import io.medalytics.projectactions.Models.Role;
import io.medalytics.projectactions.Models.Users;
import io.medalytics.projectactions.Payloads.ApiResponse;
import io.medalytics.projectactions.Payloads.AuthenticationLoginRequestPayload;
import io.medalytics.projectactions.Payloads.AuthenticationResponse;
import io.medalytics.projectactions.Payloads.UserSignUpPayload;
import io.medalytics.projectactions.Repositories.RoleRepository;
import io.medalytics.projectactions.Service.CustomUserDetailService;
import io.medalytics.projectactions.Util.JwtUtil;
import io.medalytics.projectactions.Util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;



@RestController
public class UserAuthenticationController {

    private CustomUserDetailService userDetailService;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;


    @Autowired
    public UserAuthenticationController(CustomUserDetailService userDetailService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository, JwtUtil jwtUtil) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/api/users/auth", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody AuthenticationLoginRequestPayload authPayload) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authPayload.getUsername(),
                            authPayload.getPassword()
                    )
            );
        }catch (BadCredentialsException ex){
            throw new Exception("Incorrect username or password", ex);
        }
        String jwt = jwtUtil.generateToken(userDetailService.loadUserByUsername(authPayload.getUsername()));
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "api/users/register", method = RequestMethod.POST)
    public ResponseEntity<?> userSignUp(@RequestBody UserSignUpPayload signUpPayload) throws Exception{
        if(userDetailService.existsByUsername(signUpPayload.getUsername())) {
            return new ResponseEntity(new ApiResponse("User already exists"), HttpStatus.BAD_REQUEST);
        }

        Role role = roleRepository.findRolesByType(RoleType.ROLE_USER)
                .orElseThrow(()-> new Exception("Role not set"));

        userDetailService.saveUser(
                Users.builder()
                        .username(signUpPayload.getUsername())
                        .password(passwordEncoder.encode(signUpPayload.getPassword()))
                        .email(signUpPayload.getEmail())
                        .roles(Collections.singleton(role))
                        .build()
        );
        String jwt = jwtUtil.generateToken(userDetailService.loadUserByUsername(signUpPayload.getUsername()));
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}

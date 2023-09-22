package com.projet.ecommerce.WebControllers;
import com.projet.ecommerce.Entites.Customer;
import com.projet.ecommerce.Services.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerApi {
    @Autowired
    AuthenticationManager authenticationManager;
    CustomersService customersService;
    @Autowired
    JwtEncoder jwtEncoder;
    @PostMapping("/login")
    public Map<String,String> login(String username,String password){
    Authentication authentication=
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        Instant instant=Instant.now();
        String scope=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(5,ChronoUnit.MINUTES))
                .subject(username)
                .claim("scope",scope)
                .build();
        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS512).build(),jwtClaimsSet);
        String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("access-Token",jwt);
    }
    @PostMapping("/customerSign")
    public ResponseEntity<Customer> Sign(@RequestBody Customer customer){
        return new ResponseEntity<>(customersService.addcustomer(customer), HttpStatus.OK);
    }
}

package com.projet.ecommerce.Security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import javax.crypto.spec.SecretKeySpec;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
   private UserDetailserviceImp userDetailsService;
@Value("${jwt.secret}")
    private String secretkey;
//    public SecurityConfig(UserDetailserviceImp userDetailsService) {
    //    this.userDetailsService = userDetailsService;}

    //private UserDetailserviceImp userDetailserviceImp;
    //@Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
    PasswordEncoder passwordEncoder=passwordEncoder();
        return  new InMemoryUserDetailsManager(
            User.withUsername("hassan").password(passwordEncoder.encode("12345")).authorities("USER").build()
    );}
    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return  new ProviderManager(daoAuthenticationProvider);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        httpSecurity
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests((req)->req
                .requestMatchers(mvcMatcherBuilder.pattern("/user/login/**"),
                        mvcMatcherBuilder.pattern("/user/customerSign/**")).permitAll()
                //.requestMatchers(HttpMethod.GET, "/Liste/**").hasRole(USER)
                //.requestMatchers(HttpMethod.DELETE, "/delete").hasRole(ADMIN)
                //.requestMatchers(HttpMethod.POST,"/create/**").hasRole("ADMIN")
               // .requestMatchers(mvcMatcherBuilder.pattern("/products/liste")).hasRole("USER")
                 .requestMatchers(toH2Console()).permitAll()
                .anyRequest().authenticated() );
        //  .authorizeHttpRequests(req->req.anyRequest().authenticated())
        httpSecurity.csrf(c->c.disable());
        httpSecurity.headers(h->h.frameOptions(f->f.disable()));
        httpSecurity.cors(c->c.configurationSource(corsConfigurationSource()));
        httpSecurity.oauth2ResourceServer(oau->oau.jwt(Customizer.withDefaults()));
        httpSecurity.userDetailsService(userDetailsService);
              //  userDetailsService(u->u.userDetailserviceImp);
        return    httpSecurity.build();}
  @Bean
  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
  @Bean
  JwtEncoder jwtEncoder(){
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.secretkey.getBytes()));
  }
  @Bean
  JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec=new SecretKeySpec(secretkey.getBytes(),"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
  }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://localhost:4200");
        // config.setExposedHeaders((List.of("x-auth-token")));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;}

}

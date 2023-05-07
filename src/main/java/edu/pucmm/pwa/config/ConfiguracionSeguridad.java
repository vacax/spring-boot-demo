package edu.pucmm.pwa.config;


import edu.pucmm.pwa.servicios.seguridad.SeguridadServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Created by vacax on 27/09/16.
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad  {

    //Configuación para la validación del acceso modo JDBC
    private DataSource dataSource;
    @Value("${query.user-jdbc}")
    private String queryUsuario;
    @Value("${query.rol-jdbc}")
    private String queryRol;
    //Opción JPA
    private SeguridadServices seguridadServices;
    private PasswordEncoder passwordEncoder;

    public ConfiguracionSeguridad(DataSource dataSource, SeguridadServices seguridadServices, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.seguridadServices = seguridadServices;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * La autentificación de los usuarios.
     * Para habilitar la autentificación de vía JDBC y en Memoria es necesario desconfigurar la clase
     * JPA por la inyección de dependencia.
     * @param http
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        //En Memoria
        /*System.out.println("Autentificación en Memoria");
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("admin")
                .password(passwordEncoder.encode("otro"))
                .roles("ADMIN","USER")
                .and()
                .withUser("usuario")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .and()
                .withUser("vendedor")
                .password(passwordEncoder.encode("1234"))
                .roles("VENDEDOR");*/

        //Configuración JDBC
        /*System.out.println("Autentificación JDBC");
        auth.jdbcAuthentication().usersByUsernameQuery(queryUsuario)
                .authoritiesByUsernameQuery(queryRol)
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource);*/

        //Servicio.
        auth.userDetailsService(seguridadServices)
                .passwordEncoder(passwordEncoder);


        return auth.build();
    }



    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http
                .authorizeHttpRequests((requests) -> requests

                        .requestMatchers("/","/*.html","/css/**", "/js/**", "/actuator/**", "/webjars/**").permitAll() //permitiendo llamadas a esas urls.
                        .requestMatchers("/dbconsole/**").permitAll()
                        .requestMatchers("/thymeleaf/**", "/freemarker/**", "/api/**", "/jpa/**").permitAll()
                        .requestMatchers("/api-docs/**", "/api-docs.yaml", "/swagger-ui.html", "/swagger-ui/**").permitAll() //para OpenApi
                        .requestMatchers("/admin/").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/estudiantes/").permitAll() //hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() //cualquier llamada debe ser validada
                )
                .formLogin((form) -> form
                        .loginPage("/login") //indicando la ruta que estaremos utilizando.
                        .failureUrl("/login?error") //en caso de fallar puedo indicar otra pagina.
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());


        //TODO: validar exclusivamente en ambiente de prueba.
        // deshabilitando las seguridad contra los frame internos.
        //if(!profiles.matches(Pre"prod")){
        //Necesario para H2.
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //}
        return http.build();
    }
}

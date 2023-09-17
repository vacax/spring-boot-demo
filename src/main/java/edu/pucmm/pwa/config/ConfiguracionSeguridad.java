package edu.pucmm.pwa.config;


import edu.pucmm.pwa.servicios.seguridad.SeguridadServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

/**
 * Created by vacax on 27/09/16.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfiguracionSeguridad {

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
     * Manejando el MvcRequestMatcher como mitigación al problema de https://spring.io/security/cve-2023-34035
     * Ver: https://stackoverflow.com/questions/76809698/spring-security-method-cannot-decide-pattern-is-mvc-or-not-spring-boot-applicati
     *
     * @param introspector
     * @return
     */
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    /**
     * La autentificación de los usuarios.
     * Para habilitar la autentificación de vía JDBC y en Memoria es necesario desconfigurar la clase
     * JPA por la inyección de dependencia.
     *
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
                .password(passwordEncoder.encode("admin"))
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
        System.out.println("Autentificación en JPA");
        auth.userDetailsService(seguridadServices)
                .passwordEncoder(passwordEncoder);


        return auth.build();
    }

    /**
     * Metodo para el registro de autorización, ver la diferencia entre antmatcher y mvcmatcher:
     * https://stackoverflow.com/questions/50536292/difference-between-antmatcher-and-mvcmatcher
     * MVCMatcher controla más patrones asociados, mientras que el antmatcher es exacto.
     *
     * @param http
     * @param mvc
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(authorization ->
                        authorization
                                .requestMatchers(mvc.pattern("/")).permitAll() //permitiendo llamadas a esas urls.
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/css/**"), AntPathRequestMatcher.antMatcher("/js/**"), AntPathRequestMatcher.antMatcher("/webjars/**"), AntPathRequestMatcher.antMatcher("*.html")).permitAll() //permitiendo llamadas a esas urls.
                                .requestMatchers(mvc.pattern("/h2-console/**")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/thymeleaf/**"), AntPathRequestMatcher.antMatcher("/freemarker/**"), AntPathRequestMatcher.antMatcher("/api/**"), AntPathRequestMatcher.antMatcher("/jpa/**")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api-docs/**"), AntPathRequestMatcher.antMatcher("/api-docs.yaml"), AntPathRequestMatcher.antMatcher("/swagger-ui.html"), AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll() //para OpenApi
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/")).hasAnyRole("ADMIN", "USER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/estudiantes/")).permitAll() //hasAnyRole("ADMIN", "USER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/logout")).permitAll()
                                .anyRequest().authenticated() //cualquier llamada debe ser validada
                )
                .formLogin((form) -> form
                        .loginPage("/login") //indicando la ruta que estaremos utilizando.
                        .failureUrl("/login?error") //en caso de fallar puedo indicar otra pagina.
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/logout"))
                        .permitAll());
        return http.build();
    }

}



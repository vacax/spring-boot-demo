package edu.pucmm.pwa.config;

import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by vacax on 27/09/16.
 */
//@Configurable
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionSeguridad /*extends WebSecurityConfigurerAdapter*/ {

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Cargando los usuarios en memoria.
        auth.inMemoryAuthentication()
                .withUser("vacax")
                .password("1234")
                .roles("ADMIN","USER")
                .and()
                .withUser("usuario")
                .password("1234")
                .roles("USER");
    }

    *//**
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
     *//*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http.authorizeRequests()
                .antMatchers("/admin*//**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                  //  .loginPage("/login") //indicando la ruta que estaremos utilizando.
                  //  .failureUrl("/login?error")
                    .permitAll()
                .and()
                .logout()
                    .permitAll();
    }*/
}

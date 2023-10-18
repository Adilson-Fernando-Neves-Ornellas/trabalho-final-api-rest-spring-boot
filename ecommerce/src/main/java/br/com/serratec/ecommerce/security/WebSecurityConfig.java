package br.com.serratec.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // aqui informo que é uma classe se configuracao de seguranca do springSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    //metodo que tem a configuracao global de acesso e permissoes por rotas
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //parte das configuracoes, por enquanto ignorar
        http
            .cors().and().csrf().disable()
            //.exceptionHandling().authenticationEntryPoint((request, response, authException)->response.sendError(401))
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()

            /*
             * Daqui para baixo vamos modificar para fazer nossas validacoes dinamicas
             * aqui vamos informar
             */

            .antMatchers(HttpMethod.POST, "/usuarios", "/usuarios/login","/swagger-ui/index.html#", "/pedidos")
             .permitAll() // informo que todos podem acessar esses endpontis sem autorisacao
             .anyRequest()
             //.permitAll();
             .authenticated();//digo que qualquer outro endpont nao mapeado acima deve cobrar autenticacao

             http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

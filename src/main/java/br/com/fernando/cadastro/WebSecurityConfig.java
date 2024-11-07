package br.com.fernando.cadastro;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsServiceImp userDetailsServiceImp;
	
	
	@Bean
    public CustomPasswordEncoder customPasswordEncoder() {
        return new CustomPasswordEncoder();
    }
	
	// Define o UserDetailsService para carregar o usuário do banco de dados
    @Bean
	@Override
    public UserDetailsService userDetailsService() {
        UserDetailsServiceImp imp = new UserDetailsServiceImp();
        userDetailsServiceImp = imp;
        return imp;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(customPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
			.csrf().disable() 
            .authorizeRequests()
                .antMatchers("/login", "/css/**", "/js/**").permitAll() // Permite acesso à tela de login e arquivos estáticos
                .anyRequest().authenticated() // Todas as outras requisições precisam de autenticação
                .and()
            .formLogin()
                .loginPage("/login") // Rota da página de login
                .defaultSuccessUrl("/index.html", true) // Página inicial após o login bem-sucedido
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true) 
                .clearAuthentication(true) 
                .permitAll();
    }
}
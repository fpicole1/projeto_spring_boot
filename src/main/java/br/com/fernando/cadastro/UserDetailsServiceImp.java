/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro;

import br.com.fernando.cadastro.dto.UsuarioDTO;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author fernando
 */
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private EntityManagerFactory emf;

    private String username;
    private EntityManager em;

    public Session getSession() {
       return (Session) em.getDelegate();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        setUsername(login);
        em = emf.createEntityManager();
        UsuarioDTO usuario = getUsuario(login);
              

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não localizado");
        } else {
            GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRole());
            UserDetails userDetails = (UserDetails) new User(usuario.getLogin(),
                    usuario.getPassword(), Arrays.asList(authority));
            return userDetails;
        }
    }

    
   
    /**
     * Trazendo usuários ativos, com o login digitado na tela de login.
     * 
     */
    private UsuarioDTO getUsuario(String login) {
        String sql = "select DISTINCT usu.login as login, usu.senha as password, "+
                          " 'ROLE_USER' as role " +
                      " from usuario usu "+
                     " WHERE usu.login = '" + login + "' " +
                     "   AND usu.ativo ";

        List<UsuarioDTO> usuarios = getSession().createSQLQuery(sql)
                .setResultTransformer(new AliasToBeanResultTransformer(UsuarioDTO.class)).list();

        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
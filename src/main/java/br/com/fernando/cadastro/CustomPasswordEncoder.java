/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro;

import br.com.fernando.cadastro.util.Criptografia;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author fernando
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
		final StringBuilder sb = new StringBuilder(rawPassword.length());
		sb.append(rawPassword);
			
        return Criptografia.encriptar(sb.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Verifica se a senha em texto simples criptografada é igual à armazenada
        boolean b = Criptografia.desencriptar(encodedPassword).equals(rawPassword);
		System.out.println(b);
		return b;
    }
	
}

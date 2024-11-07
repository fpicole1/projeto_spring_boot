/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author fernando
 */
public class Criptografia {
	private static final String CHAVE = "Fernando";
    
    public static String encriptar(String texto){
        BasicTextEncryptor bte = new BasicTextEncryptor();
        bte.setPassword(CHAVE);
        return bte.encrypt(texto);
    }
    
    public static String desencriptar(String texto){
        BasicTextEncryptor bte = new BasicTextEncryptor();
        bte.setPassword(CHAVE);
        return bte.decrypt(texto);
    }
}

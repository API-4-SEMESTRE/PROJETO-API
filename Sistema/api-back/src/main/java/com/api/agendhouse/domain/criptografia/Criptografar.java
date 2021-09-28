package com.api.agendhouse.domain.criptografia;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografar {
    private static MessageDigest password = null;

    static {
        try {
            password = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    private static char[] CodigoHexa(byte[] texto) {
        char[] SaidaHexa = new char[texto.length * 2];
        String SenhaCriptografada;

        for (int i = 0; i < texto.length; i++) {
            SenhaCriptografada = "00" + Integer.toHexString(texto[i]);
            SenhaCriptografada.toUpperCase().getChars(SenhaCriptografada.length() - 2,
                    SenhaCriptografada.length(), SaidaHexa, i * 2);
        }
        return SaidaHexa;

    }
    public static String criptografar(String getsenha){
        if (password != null){
            return new String(CodigoHexa(password.digest(getsenha.getBytes())));
        }
        return null;
    }
}

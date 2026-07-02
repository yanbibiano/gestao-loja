/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.gestaoloja.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    public static String gerarHash(String senhaPura) {
        if (senhaPura == null) {
            return null;
        }
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = algoritmo.digest(senhaPura.getBytes(StandardCharsets.UTF_8));


            StringBuilder resultadoHex = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    resultadoHex.append('0');
                }
                resultadoHex.append(hex);
            }

            return resultadoHex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro crítico: Algoritmo SHA-256 não está disponível no sistema", e);
        }
    }
}

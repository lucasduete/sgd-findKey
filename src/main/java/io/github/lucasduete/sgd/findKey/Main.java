package io.github.lucasduete.sgd.findKey;

import io.github.lucasduete.sgd.findKey.cryptoAlgorithms.DesCryptoAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class Main {

    private static final String ENCRYPTED_TEXT = "Yg4bEcmgxWjdBNe2TbNUdKfo5GBU5KxKMO7V997E4Nc0irx55OEezuw3GHTa0XNR";
    private static final String WORDLIST = "Meu nome é Joselito Ferreira, nasci em 23 de outubro de 1982, casei no dia 12 de janeiro de 2017, mas antes de casar tive 2 filhos: Josélia Ferreira que nasceu em 21 de novembro de 2001 e Joselito Ferreira Junior que nasceu em 22 de novembro de 2003. Meu primeiro carro foi um scort verde. Ainda lembro da placa: DXW-2103. O nome de minha mãe é Maria das Graças e do meu pai é Antônio Araújo. Trabalho com oficina mecânica e gosto muito de carros como Hilux, Pajero, Land Rover. Infelizmente perdi minha filha em um acidente no dia 18 de outubro de 2015. Um dia que marcou muito minha vida! Antes dela falecer tive outro filho, Joel Ferreira. É uma benção em minha vida. Joel nasceu no dia 18 de dezembro de 2014. É um menino danado. Adora ficar no youtube. Hoje estuda na escolinha Horas Alegres.";


    public static void main(String[] args) throws Exception {
        String texto = WORDLIST
//                .toLowerCase()
                .replaceAll("\\.", "")
                .replaceAll(":", "")
                .replaceAll(",", "")
                .replaceAll("-", "")
                .replaceAll("!", "")
                .replaceAll("ç", "c")
                .replaceAll("é", "e")
                .replaceAll("ô", "o")
                .replaceAll("ú", "u")
                .replaceAll("â", "a")
                .replaceAll("ã", "a")
                .replaceAll("1", "")
                .replaceAll("2", "")
                .replaceAll("3", "")
                .replaceAll("4", "")
                .replaceAll("5", "")
                .replaceAll("6", "")
                .replaceAll("7", "")
                .replaceAll("8", "")
                .replaceAll("9", "")
                .replaceAll("0", "")
                .replaceAll(" ", "");

        char[] textoChar = texto.toCharArray();

        for (int i = 0; i < (textoChar.length - 8); i++)
            testKey(
                    generateString(textoChar, i, 9)
            );


    }

    private static String generateString(char[] textoChar, int initialPosition, int quantCaracters) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < quantCaracters; i++)
            stringBuilder.append(textoChar[initialPosition + i]);

        return stringBuilder.toString();
    }

    private static void testKey(String key) throws Exception {
        DesCryptoAlgorithm cryptoAlgorithm = new DesCryptoAlgorithm();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        KeySpec keySpec = new DESKeySpec(key.getBytes());
        SecretKey temp = factory.generateSecret(keySpec);
        SecretKey secretKey = new SecretKeySpec(temp.getEncoded(), "DES");

        try {
            System.out.println(cryptoAlgorithm.decrypt(ENCRYPTED_TEXT, secretKey));
            System.out.printf(key + "\n\n");
        } catch (Exception ex) {
//            ex.printStackTrace();
        }

    }
}

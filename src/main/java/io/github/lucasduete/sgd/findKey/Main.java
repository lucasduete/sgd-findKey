package io.github.lucasduete.sgd.findKey;

import io.github.lucasduete.sgd.findKey.cryptoAlgorithms.DesCryptoAlgorithm;
import io.github.lucasduete.sgd.findKey.factories.EncriptedTextFactory;
import io.github.lucasduete.sgd.findKey.factories.WordlistFactory;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class Main {

    private static String ENCRYPTED_TEXT;

    public static void main(String[] args) throws Exception {
        ENCRYPTED_TEXT = new EncriptedTextFactory().retrieveEncriptedText();

        String[] wordlist = new WordlistFactory().retrieveWordlist();

        for (String word: wordlist) testKey(word);
    }

    private static void testKey(String key) throws Exception {
        DesCryptoAlgorithm cryptoAlgorithm = new DesCryptoAlgorithm();

        if (key.length() < 8)
            return;

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

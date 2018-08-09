package io.github.lucasduete.sgd.findKey.cryptoAlgorithms;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;

public class DesCryptoAlgorithm {

    public String crypt(String plainText, Key key) throws Exception {

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] byteText = plainText.getBytes("UTF8");
        byte[] cifredByteText = cipher.doFinal(byteText);

        String cifredText = new String(Base64.getEncoder().encodeToString(cifredByteText));

        return cifredText;
    }

    public String decrypt(String cifredText, Key key) throws Exception {

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] cifredByteText = Base64.getDecoder().decode(cifredText);
        byte[] byteText = cipher.doFinal(cifredByteText);

        return new String(byteText);
    }
}

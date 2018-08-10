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

    //  Crio um objeto do tipo String para receber o valor do texto decriptado.
    //  Utilizo como um objeto externo pois será utilzido em vário métodos.
    private static String ENCRYPTED_TEXT;


    public static void main(String[] args) throws Exception {
        //  Setto o objeto recuperando o texto decriptado
        ENCRYPTED_TEXT = new EncriptedTextFactory().retrieveEncriptedText();

        //  Recupera a wordlist
        String[] wordlist = new WordlistFactory().retrieveWordlist();

        //  Intera sobre as palavras da wordlist considerando cada palavra como uma chave
        //  em potencial, para cada chave tenta-se decriptar o texto.
        for (String word : wordlist) testKey(word);
    }

    /** Este método testa uma chave. Aqui o termo "testar uma chave" se refere a tentar decriptar o texto
     *  cifrado com a chave, se ele decriptar e não gerar nenhuma exception então ele imprime o texto. O texto
     *  impresso por este método pode ou não ser legível, este método não verifica se o texto é legivel apenas
     *  tenta decifrar o texto com a chave dada.
     * @param key Este parametro recebe uma chave em texto plano que será utilizada para tentar decrifar um texto.
     * @throws Exception Este método lança qualquer Exception que seja disparada durante conversão da chave passada
     * como argumento para o objeto SecretKey
     */
    private static void testKey(String key) throws Exception {
        //A chave precisa ter pelo menos 8 caracteres, se não tiver o método nem executa
        if (key.length() < 8)
            return;

        //Instancio o algoritmo DES para decript o texto posteriormente
        DesCryptoAlgorithm cryptoAlgorithm = new DesCryptoAlgorithm();

        //Tenta Converter o parametro Key em um Objeto SecretKey
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        KeySpec keySpec = new DESKeySpec(key.getBytes());
        SecretKey temp = factory.generateSecret(keySpec);
        SecretKey secretKey = new SecretKeySpec(temp.getEncoded(), "DES");

        try {
            //Tenta decriptar o texto, se conseguir imprime o texto decriptado na tela
            System.out.println(cryptoAlgorithm.decrypt(ENCRYPTED_TEXT, secretKey));

            //Imprime a chave utilizada pra decriptar o texto
            System.out.printf(key + "\n\n");
        } catch (Exception ex) {
            //  As exceptions disparadas durante este processo são, geralmente, referentes a "chave inválida"
            //  ou "impossivel decriptar o texto com esta chave" por isto ignoro as exceptions disparadas
        }

    }
}

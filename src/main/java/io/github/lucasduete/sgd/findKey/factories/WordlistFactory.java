package io.github.lucasduete.sgd.findKey.factories;

import java.io.*;

public class WordlistFactory {

    public String[] retrieveWordlist() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File rawFile = new File(classLoader.getResource("wordlist.txt").getFile());

        FileInputStream fileInputStream = new FileInputStream(rawFile);

        // Read file
        String fileContent = readFileContent(fileInputStream);
        fileInputStream.close();

        String[] wordlist = fileContent.split("\\s");

        return wordlist;
    }

    private String readFileContent(InputStream in) throws IOException {

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = new byte[1];

        while(in.read(byteArray) != -1) {
            byteOutputStream.write(byteArray);
            byteArray = new byte[1];
        }

        byte[] resulting = byteOutputStream.toByteArray();

        return new String(resulting);
    }
}
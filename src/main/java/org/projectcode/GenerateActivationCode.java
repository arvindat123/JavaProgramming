package org.projectcode;

import java.security.SecureRandom;
import java.util.stream.Collectors;

public class GenerateActivationCode {

    public static String generateActivationCode(){
        String currentTimestamp = String.valueOf(System.currentTimeMillis());
        int timestampLength = currentTimestamp.length();
        String codeRandPart = currentTimestamp.substring(timestampLength-6, timestampLength);
        SecureRandom random = new SecureRandom();
         String trailingRandom = random.ints(4,0,10).mapToObj(String::valueOf).collect(Collectors.joining());
        System.out.println(trailingRandom);
        System.out.println(currentTimestamp);
        System.out.println(codeRandPart);
        System.out.println(timestampLength);
        return codeRandPart+trailingRandom;
    }


    public static void main(String[] args) {
        System.out.println("Activation Code=> "+ generateActivationCode());
    }
}

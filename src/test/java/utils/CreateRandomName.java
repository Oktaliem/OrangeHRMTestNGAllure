package utils;

import java.util.HashSet;
import java.util.Set;

public class CreateRandomName {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final java.util.Random name = new java.util.Random();
    final Set<String> identifiers = new HashSet<>();

    public String randomIdentifier() {
        // create 5 digit random name
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = name.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(ALPHANUMERIC.charAt(name.nextInt(ALPHANUMERIC.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();

    }
}

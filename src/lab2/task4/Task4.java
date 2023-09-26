package lab2.task4;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

public class Task4 {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                String path = Task4.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);
                System.out.printf("Usage: java %s <input_string> <desired_length>", decodedPath);
                return;
            }

            String inputString = args[0];
            int desiredLength = Integer.parseInt(args[1]);

            if (desiredLength <= inputString.length()) {
                throw new IllegalArgumentException("Desired length should be greater than the input string length.");
            }

            System.out.println(addTrailingSpaces(inputString, desiredLength));
        } catch(Exception error) {
            error.printStackTrace();
            System.exit(1);
        }
    }

    public static String addTrailingSpaces(String inputString, int desiredLength) {
        String[] words = inputString.split("\\s+");

        int spacesToAdd = desiredLength - inputString.replace(" ", "").length(),
            spacesBetweenWords = spacesToAdd / (words.length - 1),
            extraSpaces = spacesToAdd % (words.length - 1);

        StringBuilder result = new StringBuilder();
        IntStream.range(0, words.length).forEach(i -> {
            result.append(words[i]);
            if (i < (words.length - 1)) {
                int spacesThisWord = spacesBetweenWords + ((i < extraSpaces) ? 1 : 0);
                result.append(" ".repeat(Math.max(0, spacesThisWord)));
            }
        });

        while (result.length() < desiredLength) result.append(" ");

        return result.toString();
    }
}


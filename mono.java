import java.util.Scanner;

public class mono {

    private static char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', '1', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static char[] key = new char[26];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String msg, cText = null;
        int choice, flag = 0;

        System.out.println("MonoAlphabetic Cipher Code--");
        System.out.print("Enter plain text: ");
        msg = scanner.nextLine();
        System.out.println("Your plain text message is: " + msg);

        System.out.print("Enter the unique key of 26 characters for encryption: ");
        String keyInput = scanner.nextLine();

        System.out.println("\n\nabcdefghijklmnopqrstuvwxyz");
        System.out.println("||||||||||||||||||||||||||");
        System.out.println(keyInput);

        if (keyInput.length() != 26) {
            System.out.println("Invalid key length. Key should be 26 characters.");
            return;
        }
        key = keyInput.toCharArray();

        do {
            System.out.println("\nPress 1 for encryption\nPress 2 for decryption\nPress 0 to exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cText = encryption(msg, key);
                    flag = 1;
                    break;
                case 2:
                    if (flag == 1)
                        decryption(cText, key);
                    else
                        System.out.println("First perform the encryption process.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    private static String encryption(String plainText, char[] encryptionKey) {
        char[] cipherText = new char[plainText.length()];

        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                int index = ch - 'a';
                cipherText[i] = encryptionKey[index];
            } else {
                cipherText[i] = ch; // Preserve non-alphabet characters
            }
        }

        String result = new String(cipherText);
        System.out.println("Your encrypted message is: " + result);
        return result;
    }

    private static void decryption(String cipherText, char[] decryptionKey) {
        char[] plainText = new char[cipherText.length()];

        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                int index = new String(decryptionKey).indexOf(ch);
                plainText[i] = (char) ('a' + index);
            } else {
                plainText[i] = ch; // Preserve non-alphabet characters
            }
        }

        String result = new String(plainText);
        System.out.println("Decrypted Text: " + result);
    }
}


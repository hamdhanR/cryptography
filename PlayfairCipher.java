
import java.util.Scanner;

public class PlayfairCipher {

    static char[][] play = new char[5][5];
    static char[] ct;
    static char[] pt;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlayfair Cipher ---");
        System.out.print("Enter plain text: ");
        String msg = scanner.nextLine().replaceAll("\\s", "");

        int size = msg.length();
        StringBuilder modifiedMsg = new StringBuilder(msg);

        for (int i = 0; i < size - 1; i += 2) {
            if (modifiedMsg.charAt(i) == modifiedMsg.charAt(i + 1)) {
                modifiedMsg.insert(i + 1, 'x');
                size++;
            }
        }

        if (size % 2 != 0) {
            modifiedMsg.append('x');
            size++;
        }

        msg = modifiedMsg.toString();

        System.out.println("\nPlain text after space removal: " + msg);
        System.out.print("Message for encryption is: ");

        for (int i = 0; i < msg.length(); i++) {
            System.out.print(msg.charAt(i));
            if (i % 2 != 0) {
                System.out.print(" ");
            }
        }

        ct = new char[size];
        pt = msg.toCharArray();

        choiceFill(scanner);
    }

    public static void choiceFill(Scanner scanner) {
        int choice;
        int flag = 0;

        do {
            System.out.println("\nPress 1 for encryption");
            System.out.println("Press 2 for decryption");
            System.out.println("Press 0 to exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    encryption();
                    flag = 1;
                    break;
                case 2:
                    if (flag == 1) {
                        decryption();
                    } else {
                        System.out.println("First perform encryption process");
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nPlease enter a valid choice\n");
                    break;
            }
        } while (choice != 0);
    }

    public static void encryption() {
        playFair();
        int i, j, k, l, r1, r2, c1, c2, p, q;

        j = 1;
        i = 0;

        while (i < pt.length - 1) {
            r1 = 0;
            r2 = 0;
            c1 = 0;
            c2 = 0;
            p = 0;
            q = 0;
            p = pt[i];
            q = pt[j];

            if (p == 'j') {
                p = 'i';
            }
            if (q == 'j') {
                q = 'i';
            }

            for (k = 0; k < 5; k++) {
                for (l = 0; l < 5; l++) {
                    if (play[k][l] == p) {
                        r1 = k;
                        c1 = l;
                    }
                    if (play[k][l] == q) {
                        r2 = k;
                        c2 = l;
                    }
                }
            }

            if (r1 == r2) {
                ct[i] = play[r1][(c1 + 1) % 5];
                ct[j] = play[r2][(c2 + 1) % 5];
            } else if (c1 == c2) {
                ct[i] = play[(r1 + 1) % 5][c1];
                ct[j] = play[(r2 + 1) % 5][c2];
            } else {
                ct[i] = play[r1][c2];
                ct[j] = play[r2][c1];
            }

            i = i + 2;
            j = j + 2;
        }

        System.out.println("\n\n\tEncrypted msg is: " + new String(ct));
    }

    public static void decryption() {
        int i, j, k, l, r1, r2, c1, c2, p, q;

        j = 1;
        i = 0;

        while (i < ct.length - 1) {
            r1 = 0;
            r2 = 0;
            c1 = 0;
            c2 = 0;
            p = 0;
            q = 0;
            p = ct[i];
            q = ct[j];

            if (p == 'j') {
                p = 'i';
            }
            if (q == 'j') {
                q = 'i';
            }

            for (k = 0; k < 5; k++) {
                for (l = 0; l < 5; l++) {
                    if (play[k][l] == p) {
                        r1 = k;
                        c1 = l;
                    }
                    if (play[k][l] == q) {
                        r2 = k;
                        c2 = l;
                    }
                }
            }

            if (r1 == r2) {
                pt[i] = play[r1][(c1 - 1 + 5) % 5];
                pt[j] = play[r2][(c2 - 1 + 5) % 5];
            } else if (c1 == c2) {
                pt[i] = play[(r1 - 1 + 5) % 5][c1];
                pt[j] = play[(r2 - 1 + 5) % 5][c2];
            } else {
                pt[i] = play[r1][c2];
                pt[j] = play[r2][c1];
            }

            i = i + 2;
            j = j + 2;
        }

        System.out.println("\n\n\tDecrypted Text: " + new String(pt));
    }

    public static void playFair() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter key: ");
        String key = scanner.nextLine().toLowerCase().replaceAll("\\s", "");

        key = key.replace("j", "i");
        key += "abcdefghiklmnopqrstuvwxyz";

        StringBuilder uniqueKey = new StringBuilder();

        for (int i = 0; i < key.length(); i++) {
            if (uniqueKey.indexOf(String.valueOf(key.charAt(i))) == -1) {
                uniqueKey.append(key.charAt(i));
            }
        }

        key = uniqueKey.toString();

        System.out.println("\nKey: " + key);
        int k = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                play[i][j] = key.charAt(k++);
                System.out.print("  "+play[i][j]);
            }
            System.out.println();
        }
    }
}


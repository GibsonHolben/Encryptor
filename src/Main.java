import java.util.Scanner;

public class Main
{
    static boolean keepGoing = true;
    static Encryptor Encryptor;
    static void changeSeed()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a encryption/decryption key (Remember the numbers)");
        String Seed = sc.nextLine();
        if(Seed.isEmpty() || Seed.equals(" ")){Seed = "0";}
        Encryptor = new Encryptor(Integer.parseInt(Seed));
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String encrypted = "";

        System.out.println("Please enter a encryption/decryption key (Remember the numbers)");

        String Seed = sc.nextLine();
        if(Seed.isEmpty() || Seed.equals(" ")){Seed = "0";}
        Encryptor = new Encryptor(Integer.parseInt(Seed));
        while(keepGoing) {


            System.out.println("what would you like to do? (enc/dec/help/exit)");
            Encryptor.setSpaceChar("_");
            Encryptor.setLowerChar("*");
            sc = new Scanner(System.in);
            switch (sc.nextLine().toLowerCase())
            {
                case "enc":
                    System.out.println("Enter a string to encrypt");
                    String toEnc = sc.nextLine();
                    encrypted = Encryptor.encrypt(toEnc);
                    System.out.println(encrypted);
                    break;
                case "dec":
                    System.out.println("Enter a string to decrypt");
                    String decrypted = Encryptor.decrypt(sc.nextLine());
                    System.out.println(decrypted);
                    break;
                case "dump":
                    for(int i : Encryptor.letterNumb)
                    {
                        System.out.println(i + "," + Encryptor.Letters.get(i-1));
                    }
                    break;
                case "exit":
                    keepGoing = false;
                    break;
                case "seed":
                    changeSeed();
                    break;
                default:
                    System.out.println("'" + sc.nextLine() + "' is not recognized as a command");

            }
        }


    }
}



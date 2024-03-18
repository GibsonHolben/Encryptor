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
        Encryptor.seed(Integer.parseInt(Seed));
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String encrypted = "";

        System.out.println("Please enter a encryption/decryption key (Remember the numbers)");

        String Seed = sc.nextLine();
        if(Seed.isEmpty() || Seed.equals(" ")){Seed = "0";}
        try
        {
            Encryptor = new Encryptor(Integer.parseInt(Seed));
        }
        catch(Exception e)
        {
            Encryptor = new Encryptor(0);
            Encryptor.seed(Seed);

        }
        while(keepGoing) {


            System.out.println("what would you like to do? (enc/dec/help/exit)");
            Encryptor.setSpaceChar("_");
            Encryptor.setLowerChar("*");
            sc = new Scanner(System.in);
            String cmd = sc.nextLine();
            switch (cmd.toLowerCase())
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
                    System.out.println(Encryptor.toString());
                    break;
                case "exit":
                    keepGoing = false;
                    break;
                case "seed":
                    changeSeed();
                    break;
                case "help":
                    System.out.println("The commands are:\n" +
                            "enc  (Encrypts the given string)\n" +
                            "dec  (Decrypts the given string)\n" +
                            "dum  (dumps the encryption data)\n" +
                            "exit (Exits the command line)\n" +
                            "seed (Changes the seed of the encryptor)\n" +
                            "help (Shows a help menu)\n");
                    break;
                default:
                    System.out.println("'" + cmd + "' is not recognized as a command");

            }
        }


    }
}



import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Encryptor
{

    /**An Arraylist that holds all the characters that are supported in the Encryptor*/
    private final ArrayList<String> Letters = new ArrayList<>();
    /**An Arraylist that holds the indexes of the characters*/
    private final ArrayList<Integer> letterNumb = new ArrayList<>();

    /**Used to hold the seed so that it can be gotten by the user*/
    private long seed;

    /**The char that is used to signal a lowercase char*/
    private String LowerChar = "*";
    /**The char that is used to signal a space*/
    private String SpaceChar = "_";

    /**Creates an Encryptor with a seed that is used for encryption/decryption
     * @param Seed The seed that is used for randomization in the encryption*/
    Encryptor(int Seed)
    {
        seed(Seed);
    }

    /**Used to load the Arraylists with the proper values*/
    void load()
    {
        //Creates an array that holds all the chars
        String[] s = {"1", "2", "3","4","5","6","7","8","9","0","A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "?", "/", "(", ")", "@", "#", "$", "%", "^", "&", "*","-","_","+","=","|",",",".","<",">","`","~","\"","'","\\", "}", "{","★","©","®","™","℗","¢",""}; //Leave empty char

        //Creates the number key
        for(int i = 1; i < s.length + 1; i++) {
            letterNumb.add(i);
        }
        //Adds the letters to the array
        Collections.addAll(Letters, s);
    }

    /**
     * Shuffles the Arraylists with a passed in seed
     * @param seed The seed that is used for randomization of the Arraylists
     */
    public void seed(int seed)
    {
        //Sets the seed
        this.seed = seed;
        //Loads the arrays

        load();
        //Randomizes the arrays
        Random random = new Random(seed);
        Collections.shuffle(letterNumb, random);
    }
    /**
     * Shuffles the Arraylists with a passed in seed that is a string
     * @param seed The seed that is used for randomization of the Arraylists
     */
    public void seed(String seed)
    {
        //Loads the arrays
        load();
        //Split the seed into chars
        String[] charA = seed.split("");
        //Creates variables
        int finish =0;
        int x = 0;
        //Gets the number values of the chars
        for(String s1 : charA){
            x +=1;
            for(String s2 : Letters) {
                if(s2.equals(s1)) {
                   finish += x;
                }
            }
        }
        //Sets the seed
        this.seed = finish;
        //Randomizes the Arrays
        Random random = new Random(finish);
        Collections.shuffle(letterNumb, random);
    }

    /**
     * Encrypts the passed in string to numbers and symbols
     * @param toEnc The String that is to be encrypted
     * @return The encrypted String
     */
    public String encrypt(String toEnc) throws CouldNotEncryptError
    {
        try
        {
            //Trims excess b
            if(toEnc.endsWith(".2"))
                toEnc = toEnc.substring(0, toEnc.length() - 2);
            //Creates variables
            StringBuilder finish = new StringBuilder();
            String[] charArray = toEnc.split("");
            ArrayList<Integer> upperIndex = new ArrayList<>();

            //Loads the upper index
            for(String ignored : charArray) {
                upperIndex.add(0);
            }
            //Check if letters are lowercase
            for(int i = 0; i < charArray.length; i++) {
                if(isLowerCase(charArray[i])) {
                    charArray[i] = charArray[i].toUpperCase();
                    upperIndex.set(i, 1);
                }
            }
            //Gets the number value and appends special chars (spaces and uppers)
            for(int j = 0; j < charArray.length; j++) {
                try {
                    for(int i = 0; i < Letters.size(); i++) {
                        if(charArray[j].equals(Letters.get(i))) {
                            if(upperIndex.get(j).equals(1)) {
                                finish.append(letterNumb.get(i)).append(LowerChar).append(".");
                            }
                            else {
                                finish.append(letterNumb.get(i)).append(".");
                            }
                        }
                    }
                    if (charArray[j].equals(" ")) {
                        finish.append(SpaceChar).append(".");
                    }
                }
                catch (Exception e) {
                    System.err.println("Could not encrypt. Error: " + e.getMessage());
                }
            }
            //Returns the encrypted string
            return finish.toString();
        }
        catch (Exception e)
        {
            throw new CouldNotEncryptError();
        }

    }

    /**
     * Used to decrypt a passed in String tha that has been encrypted (Will only properly decrypt
     * if the seed that was used to create the encryptor was the same as when the string was encrypted)
     * @param toDec The Encrypted string that is passed in
     * @return The decrypted string
     */
    public String decrypt(String toDec)
    {
        //Creates variables
        StringBuilder finish = new StringBuilder();
        String[] intArray = toDec.split("\\.");

        //Converts the numbers to the letter value
        for(String s : intArray) {
            boolean doLower = false;
            boolean doSpace = false;
            String toAp = "";
            if(s.contains(LowerChar)) {
                doLower = true;
                s = s.replace(LowerChar, "");
            }
            else if(s.contains(SpaceChar)) {
                doSpace = true;
                s = s.replace(SpaceChar, "");
            }
            if(!doSpace) {
                for(int i = 0; i < letterNumb.size() - 1; i++) {
                    if(letterNumb.get(i) == Integer.parseInt(s)) {
                        toAp = Letters.get(i);
                    }
                }
            }
            if(doLower)
                toAp = toAp.toLowerCase();

            else if(doSpace)
                toAp = " ";
            finish.append(toAp);
        }
        //returns the decrypted string
        return finish.toString();
    }



    /**
     * Checks if the passed in String is lowercase
     * @param str The String to check
     * @return true if the String is lowercase and false if it is uppercase
     */
    boolean isLowerCase(String str) {return Objects.equals(str, str.toLowerCase()) && !str.equals(str.toUpperCase());}

    /**
     * Gets the lower char
     * @return the lower char
     */
    public String getLowerChar() {
        return new String(LowerChar);
    }

    /**
     * sets the lower signal of the encryptor
     * @param lowerChar the char to set
     */
    public void setLowerChar(String lowerChar) {
        //Creates variables
        String[] LowerCharA = lowerChar.split("");//💀
        String[] SpaceCharA = SpaceChar.split("");//💀
        boolean doAppend = true;
        //Make sure the chars are not the same as the space char
        for(String s : LowerCharA) {
            for(String s2 : SpaceCharA) {
                if (s.equals(s2)) {
                    doAppend = false;
                    break;
                }
            }
        }
        //set the lower chars or return an error
        if(doAppend)
            LowerChar = lowerChar;
        else {
            System.err.println("Lowercase String cannot contain the same charters as the space String");
        }
    }


    /**
     * Gets the space char
     * @return space char
     */
    public String getSpaceChar() {
        return new String(SpaceChar);
    }

    /**
     * sets the space signal of the encryptor
     * @param spaceChar the space char to set
     */
    public void setSpaceChar(String spaceChar) {
        String[] LowerCharA = LowerChar.split("");//💀
        String[] SpaceCharA = spaceChar.split("");//💀
        boolean doAppend = true;
        for(String s : LowerCharA) {
            for(String s2 : SpaceCharA) {
                if (s2.equals(s)) {
                    doAppend = false;
                    break;
                }
            }
        }
        if(doAppend)
        {
            SpaceChar = spaceChar;
        }
        else {
            System.err.println("Space String cannot contain the same charters as the lowercase String");
        }

    }

    /**
     * Gets the seed of the current encryptor
     * @return the seed
     */
    public long getSeed() {
        long sd = seed;
        return sd;
    }

    /**
     * Returns the objects values in a dump
     * @return the variables
     */
    public String toString()
    {
        StringBuilder returns = new StringBuilder();
        for(int i = 0; i < Letters.size() - 1; i++)
        {
            System.out.println(Letters.get(i) + ":" + letterNumb.get(i));
        }

        returns.append("Lower char: ").append(LowerChar).append("\n");
        returns.append("Space char: ").append(SpaceChar).append("\n");
        returns.append("Seed: ").append(seed).append("\n");
        return returns.toString();
    }

}

class CouldNotEncryptError extends Throwable{
    public void printStackTrace()
    {
        System.err.println("Could not encrypt the given String");
        super.printStackTrace();
    }
}

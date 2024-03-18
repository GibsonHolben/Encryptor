import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Encryptor
{

    /**Creates an Encryptor with a seed that is used for encryption/decryption
     * @param Seed The seed that is used for randomization in the encryption*/
    Encryptor(int Seed)
    {
        OffsetList(Seed);
    }

    /**An Arraylist that holds all the characters that are supported in the Encryptor*/
    ArrayList<String> Letters = new ArrayList<>();
    /**An Arraylist that holds the indexes of the characters*/
    ArrayList<Integer> letterNumb = new ArrayList<>();

    /**The char that is used to signal a lowercase char*/
    String LowerChar = "*";
    /**The char that is used to signal a space*/
    String SpaceChar = "_";

    /**Used to load the Arraylists with the proper values*/
    void load()
    {
        String[] s = {"A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "?", "/", "(", ")", "@", "#", "$", "%", "^", "&", "*","-","_","+","=","|",",",".","<",">","`","~","\"","'","\\", "}", "{","★","©","®","™","℗","¢",""}; //Leave empty char
        for(int i = 1; i < s.length + 1; i++) {
            letterNumb.add(i);
        }
        Collections.addAll(Letters, s);
    }

    /**
     * Shuffles the Arraylists with a passed in seed
     * @param seed The seed that is used for randomization of the Arraylists
     */
    void OffsetList(int seed)
    {
        load();
        Random random = new Random(seed);
        Collections.shuffle(letterNumb, random);
    }

    /**
     * Encrypts the passed in string to numbers and symbols
     * @param toEnc The String that is to be encrypted
     * @return The encrypted String
     */
    public String encrypt(String toEnc)
    {
        if(toEnc.endsWith(".2"))
            toEnc = toEnc.substring(0, toEnc.length() - 2);
        StringBuilder finish = new StringBuilder();
        String[] charArray = toEnc.split("");
        ArrayList<Integer> upperIndex = new ArrayList<>();
        for(String ignored : charArray) {
            upperIndex.add(0);
        }
        for(int i = 0; i < charArray.length; i++) {
            if(isLowerCase(charArray[i])) {
                charArray[i] = charArray[i].toUpperCase();
                upperIndex.set(i, 1);
            }
        }

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
        return finish.toString();
    }

    /**
     * Used to decrypt a passed in String tha that has been encrypted (Will only properly decrypt
     * if the seed that was used to create the encryptor was the same as when the string was encrypted)
     * @param toDec The Encrypted string that is passed in
     * @return The decrypted string
     */
    public String decrypt(String toDec)
    {
        StringBuilder finish = new StringBuilder();
        String[] intArray = toDec.split("\\.");
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
        return LowerChar;
    }

    /**
     * sets the lower signal of the encryptor
     * @param lowerChar the char to set
     */
    public void setLowerChar(String lowerChar) {
        String[] LowerCharA = lowerChar.split("");//💀
        String[] SpaceCharA = SpaceChar.split("");//💀
        boolean doAppend = true;
        for(String s : LowerCharA) {
            for(String s2 : SpaceCharA) {
                if (s.equals(s2)) {
                    doAppend = false;
                    break;
                }
            }
        }
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
        return SpaceChar;
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

}

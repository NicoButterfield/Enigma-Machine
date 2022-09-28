import java.util.*;


public class Enigma
{

    /*Contains the rotor setting coming both right to left and then left to right. 
    Also holds the indexs that the reflector maps to.*/

    static final Map<Integer, Integer> reflectorMapping;
    static final Map<Character, Character> RLrotor;
    static final Map<Character, Character> LRrotor;

    static {
        reflectorMapping = new HashMap<Integer, Integer>();
        RLrotor = new HashMap <Character, Character>();
        LRrotor = new HashMap <Character, Character>();

        reflectorMapping.put(1, 15); 
        reflectorMapping.put(2, 19);
        reflectorMapping.put(3, 14);
        reflectorMapping.put(4, 13);
        reflectorMapping.put(5, 25);
        reflectorMapping.put(6, 8);
        reflectorMapping.put(7, 10);
        reflectorMapping.put(8, 6);
        reflectorMapping.put(9, 16);
        reflectorMapping.put(10, 7);
        reflectorMapping.put(11, 24);
        reflectorMapping.put(12, 20);
        reflectorMapping.put(13, 4);
        reflectorMapping.put(14, 3);
        reflectorMapping.put(15, 1);
        reflectorMapping.put(16, 9);
        reflectorMapping.put(17, 23);
        reflectorMapping.put(18, 22);
        reflectorMapping.put(19, 2);
        reflectorMapping.put(20, 12);
        reflectorMapping.put(21, 26);
        reflectorMapping.put(22, 18);
        reflectorMapping.put(23, 17);
        reflectorMapping.put(24, 11);
        reflectorMapping.put(25, 5);
        reflectorMapping.put(26, 21);

        RLrotor.put('A', 'V');
        RLrotor.put('B', 'Z');
        RLrotor.put('C', 'B');
        RLrotor.put('D', 'R');
        RLrotor.put('E', 'G');
        RLrotor.put('F', 'I');
        RLrotor.put('G', 'T');
        RLrotor.put('H', 'Y');
        RLrotor.put('I', 'U');
        RLrotor.put('J', 'P');
        RLrotor.put('K', 'S');
        RLrotor.put('L', 'D');
        RLrotor.put('M', 'N');
        RLrotor.put('N', 'H');
        RLrotor.put('O', 'L');
        RLrotor.put('P', 'X');
        RLrotor.put('Q', 'A');
        RLrotor.put('R', 'W');
        RLrotor.put('S', 'M');
        RLrotor.put('T', 'J');
        RLrotor.put('U', 'Q');
        RLrotor.put('V', 'O');
        RLrotor.put('W', 'F');
        RLrotor.put('X', 'E');
        RLrotor.put('Y', 'C');
        RLrotor.put('Z', 'K');

        LRrotor.put('A', 'Q');
        LRrotor.put('B', 'C');
        LRrotor.put('C', 'Y');
        LRrotor.put('D', 'L');
        LRrotor.put('E', 'X');
        LRrotor.put('F', 'W');
        LRrotor.put('G', 'E');
        LRrotor.put('H', 'N');
        LRrotor.put('I', 'F');
        LRrotor.put('J', 'T');
        LRrotor.put('K', 'Z');
        LRrotor.put('L', 'O');
        LRrotor.put('M', 'S');
        LRrotor.put('N', 'M');
        LRrotor.put('O', 'V');
        LRrotor.put('P', 'J');
        LRrotor.put('Q', 'U');
        LRrotor.put('R', 'D');
        LRrotor.put('S', 'K');
        LRrotor.put('T', 'G');
        LRrotor.put('U', 'I');
        LRrotor.put('V', 'A');
        LRrotor.put('W', 'R');
        LRrotor.put('X', 'P');
        LRrotor.put('Y', 'H');
        LRrotor.put('Z', 'B');

    }

    //Sets up letter mapping relative to the initial key.
    public static char[] keyPosition(char key)
    {
        char [] charPositions = new char[26];
        char [] shiftedPos = new char[26];
        
        charPositions[0] = 'A';
        charPositions[1] = 'Z';
        charPositions[2] = 'Y';
        charPositions[3] = 'X';
        charPositions[4] = 'W';
        charPositions[5] = 'V';
        charPositions[6] = 'U';
        charPositions[7] = 'T';
        charPositions[8] = 'S';
        charPositions[9] = 'R';
        charPositions[10] = 'Q';
        charPositions[11] = 'P';
        charPositions[12] = 'O';
        charPositions[13] = 'N';
        charPositions[14] = 'M';
        charPositions[15] = 'L';
        charPositions[16] = 'K';
        charPositions[17] = 'J';
        charPositions[18] = 'I';
        charPositions[19] = 'H';
        charPositions[20] = 'G';
        charPositions[21] = 'F';
        charPositions[22] = 'E';
        charPositions[23] = 'D';
        charPositions[24] = 'C';
        charPositions[25] = 'B';

        int shift = 0;
        for(int x = 0; x < charPositions.length; x++)
        {
            if(charPositions[x] == key)
            {
                shift = x;
                break;
            }
        }

        for(int x = 0; x < charPositions.length; x++)
        {
            
            int pos =  (x + shift) % 26;
            shiftedPos[x] = charPositions[pos];
        }

        return shiftedPos;
    }

    //Method both encodes and decodes the given message.
    public static String encodeDecode(String message, Character key)
    {
        char [] start = keyPosition(key);
        
        message = message.toUpperCase();
        String code = "";

        for(int x = 0; x < message.length(); x++)
        {

            char letter = message.charAt(x);

            if(letter == ' ')
            {
                code += " ";
            }
            else
            {
                letter = RLrotor.get(letter);
                
                int position = 0;
                for(int y = 0; y < start.length; y++)
                {
                    if(start[y] == letter)
                    {
                        position = reflectorMapping.get(y + 1) - 1;
                        letter = LRrotor.get(start[position]);
                        break;
                    } 
                }
                
                code += letter;
                
                char [] shifted = new char[26];
                
                for(int z = 0; z < start.length; z++)
                {
                    if(z == 25)
                    {
                        shifted[0] = start[25];
                    } 
                    else
                    {
                        shifted[z + 1] = start[z];
                    }
                }
                
                start = shifted;
            }
        }
        return code;

    }

    public static void main(String[] args)
    {
        System.out.print("Please enter message to be encoded:");

        Scanner scan = new Scanner(System.in);

        String message = scan.nextLine();

        scan.close();

        char key = 'K'; 

        String encrypt = encodeDecode(message, key);
        System.out.println("Encrypted message: " + encrypt);

        System.out.println();
        System.out.println();


        String decrypt = encodeDecode(encrypt, key);
        System.out.println("Decrypted message: " + decrypt);

    }
}
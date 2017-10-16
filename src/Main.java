import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mspusta on 16.10.2017.
 */
public class Main {
    private static Scanner input = new Scanner(System.in);

    private static String[] fileScanner() throws FileNotFoundException {
        Scanner file = new Scanner(new File("word2guess.txt"));
        List<String> lines = new ArrayList<String>();
        while(file.hasNextLine()){
            lines.add(file.nextLine());
        }
        String[] arr = lines.toArray(new String[0]);
        return arr;
    }

    private static int random100(){
        int secretnum = (int)(Math.random()*100);
        return secretnum;
    }

    private static void init() throws FileNotFoundException {
        String[] words = fileScanner();
        String word2guess = words[random100()];
        int repeats = 1;
        String attempt = "";
        String temp = "";

        // naplnenie premennej podjemnikmi
        for(int i = 0; i < word2guess.length(); i++){
            temp += "_";
        }

        // inicializacia jednotlivych pokusov
        while(!word2guess.equals(attempt) && !word2guess.equals(temp)){
            System.out.print("Key in one character or your guess word: ");
            attempt = input.next();

            // ak user zada viac ako jeden znak a neuhadne slovo, tak je pokus neplatny
            while (!attempt.equals(word2guess) && attempt.length() != 1){
                System.out.print("Wrong, try again: ");
                attempt = input.next();
            }

            // ak user uhadne cele slovo, tak while cyklus sa breakne
            if (attempt.equals(word2guess)){
                break;
            }

            // nahrada jedneho uhadnuteho znaku za podjemnik
            for(int i = 0; i < word2guess.length(); i++){
                if(word2guess.substring(i, i+1).equals(attempt)){
                    temp = temp.substring(0, i) + attempt + temp.substring(i+1);
                }
            }

            System.out.println("Trial " + repeats + ": " + temp);
            repeats++;
        }

        // vypis v pripade uhadnutia
        System.out.println("Congratilation! \nYou got it in " + repeats + " trials");
        System.out.println("Press any character and confirm with enter to exit..");
        input.next();
    }

    public static void main(String[] args) throws FileNotFoundException {
        init();
        input.close();
    }
}

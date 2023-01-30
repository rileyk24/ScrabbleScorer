import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * runs through a dictionary of scrabble words and compiles a dictionary. the program takes in user input and returns the scrabble score of the word, if word is valid.
 * @author riley kim
 * @version 01.30.23
 */
public class ScrabbleScorer {
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<ArrayList<String>> dictionary; // array lsit of an array list of strings

    /**
     * constructor for the class Scrabble Scorer
     */
    public ScrabbleScorer() {
        //constructor
        dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            dictionary.add(new ArrayList<String>());
        }
        buildDictionary();


    }

    /**
     * a method that builds the dictionary containing all possible words and the ArrayList within the dictionaries are sorted by ASCII values
     */
        public void buildDictionary () {
            //build dictionary by importing data
            try {
                Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
                while (in.hasNext()) {
                    String word = in.nextLine().toUpperCase();
                    int index = alpha.indexOf(word.substring(0, 1));
                    dictionary.get(index).add(word);

                }
                in.close();
                //now sort all of the buckets
                for (int i = 0; i < dictionary.size(); i++) {
                    Collections.sort(dictionary.get(i));
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    /**
     * a method that is used to determine whether the
     * @param word the word entered in by the user
     * @return whether the word inputted is valid or not
     */
    public boolean isValidWord (String word){
            // find correct bucket for word by looking at the first letter
            // find indexof the first letter in alpha
            // get corresponding bucket
            String first = word.substring(0,1);
            return Collections.binarySearch(dictionary.get(alpha.indexOf(first)), word) >= 0;
        }

    /**
     *a method in which the scrabble score of a word is determined
     * @param word the word entered in by the user
     * @return amount of points the word is worth
     */

    public int getWordScore (String word){
            int score = 0;
            for (int i = 0; i < word.length(); i++) {
                int index = alpha.indexOf(word.substring(i, i + 1));
                score += values[index];
            }
            return score;
        }

    /**
     * Main method of ScrabbleScorer
     * @param args Command-line arguments, if needed
     */
    public static void main (String[]args){
            ScrabbleScorer app = new ScrabbleScorer();
            System.out.println("* Welcome to the Scrabble Word Scorer app *");
            Scanner in = new Scanner(System.in);
            while(true){
                System.out.println("Enter a word to score or 0 to quit: ");
                String word = in.nextLine().toUpperCase();
                if (word.equals("0")||word.equals("")){
                    break;
                }
                if(app.isValidWord(word)){
                    System.out.println(word + " = "+ app.getWordScore(word) + " points");
                }
                else{
                    System.out.println(word + " is not a valid word in the dictionary");
                }
            }
            System.out.println("Exiting the program thanks for playing");
        }
    }
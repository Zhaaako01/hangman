package hangmanProcedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        startGame();
    }

    private static final List<String> listOfWords = new ArrayList<>();
    private static final Random randomizer = new Random();
    private static String randomWord;
    private static StringBuilder hiddenWord;
    private static final Scanner scanner = new Scanner(System.in);
    private static int attempts = 6;
    private static char guessedLetter;
    private static boolean isCorrect;
    private static boolean isAnswer;


    public static void startGame() {
        System.out.println("Хотите сыграть?");
        while (doYouWantToPlay()) {
            getListOfWords();
            getRandomWordFromTheList();
            hideRandomWord();
            while (attempts > 0 && !hiddenWord.toString().equals(randomWord)) {
                showProgress();
                tryToGuess();
                isContain(guessedLetter);
            }
            endGame();
        }
    }

    public static List<String> getListOfWords() {

        listOfWords.add("машина");
        listOfWords.add("футбол");
        listOfWords.add("телефон");
        listOfWords.add("квартира");
        listOfWords.add("ноутбук");
        listOfWords.add("клавиатура");
        listOfWords.add("диван");
        listOfWords.add("чехол");
        listOfWords.add("монитор");
        listOfWords.add("тетрадь");
        return listOfWords;
    }

    public static String getRandomWordFromTheList() {

        int randomIndex = randomizer.nextInt(listOfWords.size());

        randomWord = listOfWords.get(randomIndex);

//        System.out.println(randomWord);
        return randomWord;
    }

    public static StringBuilder hideRandomWord() {
// попробуем сперва все буквы заменить на *
//        hiddenWord = new StringBuilder();
//        for (int i = 0; i < randomWord.length(); i++) {
//            hiddenWord.append('*');
//        }
//        System.out.println(hiddenWord);
//        return hiddenWord;

        hiddenWord = new StringBuilder();
        int indexOf1st = randomizer.nextInt(randomWord.length());
        int indexOf2nd;

        // чтобы вторая не совпадало с первой
        do {
            indexOf2nd = randomizer.nextInt(randomWord.length());
        } while (indexOf2nd == indexOf1st);

        for (int i = 0; i < randomWord.length(); i++) {
            if (i == indexOf1st || i == indexOf2nd) {
                hiddenWord.append(randomWord.charAt(i));
            } else {
                hiddenWord.append('*');
            }
        }
//        System.out.println(hiddenWord);
        return hiddenWord;
    }

    public static void showProgress() {
        System.out.println("Слово которое надо угадать: " + hiddenWord);
        System.out.println("Осталось попыток: " + attempts);
    }

    public static char tryToGuess() {

        System.out.println("Попытайтесь угадать букву: ");
        guessedLetter = scanner.next().charAt(0);
//        System.out.println(guessedLetter);
        return guessedLetter;
    }

    public static boolean isContain(char guessedLetter) {
        isCorrect = false;
        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == guessedLetter) {
                hiddenWord.setCharAt(i, guessedLetter);
                isCorrect = true;
            }
        }
        if (!isCorrect) {
            attempts--;
        }
//        System.out.println(isCorrect);
        return isCorrect;
    }

    public static void endGame() {
        if (hiddenWord.toString().equalsIgnoreCase(randomWord)) {
            System.out.println("Вы выиграли! Скрытым словом было: " + randomWord);
        } else {
            System.out.println("Вы проиграли! Скрытым словом было: " + randomWord);
        }
        System.out.println("Хотите сыграть ещё?");
        doYouWantToPlay();
    }

    public static boolean doYouWantToPlay() {

        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("да")) {
            isAnswer = true;
        } else isAnswer = false;
        return isAnswer;
    }
}

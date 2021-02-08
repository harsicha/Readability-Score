import java.nio.file.Paths;
import java.util.Scanner;

public class ReadabilityScore {
    public static void main(String[] args) {
        int sentence = 0;
        int words = 0;
        int characters = 0;
        int syllables = 0;
        int polysyllables = 0;

        try (Scanner reader = new Scanner(Paths.get(args[0]))) {
            while (reader.hasNext()) {
                String scan = reader.next();
                characters = characters + scan.length();
                if (scan.matches(".+[!\\?\\.]") || !reader.hasNext()) {
                    sentence++;
                }
                int vowels = 0;
                for (int i = 0; i < scan.length(); i++) {
                    if (scan.charAt(i) == 'a' || scan.charAt(i) == 'A' || scan.charAt(i) == 'e' ||
                            scan.charAt(i) == 'E' || scan.charAt(i) == 'i' || scan.charAt(i) == 'I' ||
                            scan.charAt(i) == 'o' || scan.charAt(i) == 'O' || scan.charAt(i) == 'u' ||
                            scan.charAt(i) == 'U' || scan.charAt(i) == 'y' || scan.charAt(i) == 'Y') {
                        vowels++;
                    }
                }

                if (scan.matches(".*[aeiouyAEIOUY]{2}.*")) {
                    vowels = vowels - 1;
                }
                if (scan.matches(".*[aeiouyAEIOUY]{3,}.*")) {
                    vowels = vowels - 1;
                }
                if (scan.matches(".*[aeiouyAEIOUY]{2}.*[aeiouy]{2}.*")) {
                    vowels = vowels - 1;
                }
                if (scan.matches(".+[e][!,\\?\\.]?")) {
                    vowels = vowels - 1;
                }
                if (vowels > 0) {
                    syllables = syllables + vowels;
                } else {
                    syllables++;
                }
                if (vowels > 2) {
                    polysyllables++;
                }
                words++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentence);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = scanner.nextLine();
        System.out.println();

        if (choice.equals("ARI")) {

            System.out.print("Automated Readability Index: ");
            double scoreARI = 4.71 * (1.0 * characters / words) + 0.5 * (1.0 * words / sentence) - 21.43;
            double roundOffARI = Math.round(scoreARI);
            System.out.printf("%.2f", scoreARI);
            System.out.println(" (about " + score(roundOffARI) + " year olds).");

        } else if (choice.equals("FK")) {

            System.out.print("Flesch" +'\u002D' +"Kincaid readability tests: ");
            double scoreFK = (0.39 * words / sentence) + (11.8 * syllables / words) - 15.59;
            double roundOffFK = Math.round(scoreFK);
            System.out.printf("%.2f", scoreFK);
            System.out.println(" (about " + score(roundOffFK) + " year olds).");

        } else if (choice.equals("SMOG")) {

            System.out.print("Simple Measure of Gobbledygook: ");
            double scoreSMOG = (1.043 * Math.sqrt(polysyllables * 30.0 / sentence)) + 3.1291;
            double roundOffSMOG = Math.round(scoreSMOG);
            System.out.printf("%.2f", scoreSMOG);
            System.out.println(" (about " + score(roundOffSMOG) + " year olds).");

        } else if (choice.equals("CL")) {

            System.out.print("Coleman" + '\u002D' + "Liau index: ");
            double scoreCL = (0.0588 * 100 * characters / words) - (0.296 * 100 * sentence / words) - 15.8;
            double roundOffCL = Math.round(scoreCL);
            System.out.printf("%.2f", scoreCL);
            System.out.println(" (about " + score(roundOffCL) + " year olds).");

        } else if (choice.equals("all")) {

            System.out.print("Automated Readability Index: ");
            double scoreARI = 4.71 * (1.0 * characters / words) + 0.5 * (1.0 * words / sentence) - 21.43;
            double roundOffARI = Math.round(scoreARI);
            System.out.printf("%.2f", scoreARI);
            System.out.println(" (about " + score(roundOffARI) + " year olds).");

            System.out.print("Flesch" +'\u2013' +"Kincaid readability tests: ");
            double scoreFK = (0.39 * words / sentence) + (11.8 * syllables / words) - 15.59;
            double roundOffFK = Math.round(scoreFK);
            System.out.printf("%.2f", scoreFK);
            System.out.println(" (about " + score(roundOffFK) + " year olds).");

            System.out.print("Simple Measure of Gobbledygook: ");
            double scoreSMOG = (1.043 * Math.sqrt(polysyllables * 30.0 / sentence)) + 3.1291;
            double roundOffSMOG = Math.round(scoreSMOG);
            System.out.printf("%.2f", scoreSMOG);
            System.out.println(" (about " + score(roundOffSMOG) + " year olds).");

            System.out.print("Coleman" + '\u2013' + "Liau index: ");
            double scoreCL = (0.0588 * 100 * characters / words) - (0.296 * 100 * sentence / words) - 15.8;
            double roundOffCL = Math.round(scoreCL);
            System.out.printf("%.2f", scoreCL);
            System.out.println(" (about " + score(roundOffCL) + " year olds).");
            System.out.println();

            double average = (score(roundOffARI) + score(roundOffFK) + score(roundOffSMOG) + score(roundOffCL)) / 4.0;
            System.out.println("This text should be understood in average by " + average + " year olds.");

        } else {

            System.out.println("Invalid input");

        }
    }
    public static int score(double roundOff) {
        int checkScore = 1;
        int age = 5;
        while (checkScore < 15) {
            if (checkScore == 3) {
                age++;
            }
            if (checkScore == roundOff) {
                if (checkScore == 3) {

                    return (age + 2);

                } else if (checkScore == 13) {

                    return (age + 6);

                } else if (checkScore == 14) {

                    return age;

                } else {

                    return (age + 1);

                }
            }
            checkScore++;
            age++;
        }
        return age;
    }
}

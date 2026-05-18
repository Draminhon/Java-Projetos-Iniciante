import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        System.out.println("Welcome to the number guessing game!\nI'm thinking in a number between 1 and 100!\nWhich difficulty do you want to play?\n1 - Easy(10 tries)\n2 - Normal(5 tries)\n3 - Hard(3 tries)\n");
        choice = sc.nextInt();

         int randomNumb = (int) (Math.random() * 100);
          prompt(choice, randomNumb, sc);


    }

    static void prompt(int choice, int randomNumb, Scanner sc){

        switch (choice){
            case 1:
           game(sc, randomNumb,  10);
                break;
            case 2:
                game(sc, randomNumb, 5);

                break;
            case 3:
                game(sc, randomNumb,3);
                break;
            default:
                break;
        }
    }
    static void game(Scanner sc, int randomNumb, int numberOfTries){

        int number;
        boolean result;
        int tries = 0;
        int choice;
        System.out.println("\nYou chose the easy option.\nLet's begin!\n");
        System.out.println("Type a number between 1 and 100 to start.");
        while(true){
            number = sc.nextInt();
            result = numberGuess(number, randomNumb);
            tries++;
            if (tries >= numberOfTries){
                System.out.println("You lose!");
                break;
            }
            if (result){
                System.out.println("Do you want to keep playing?\n1 - yes\n2 - no\n");
                choice = sc.nextInt();
                if(choice == 1){
                    tries = 0;
                    System.out.println("I'm thinking in a number between 1 and 100!\n");
                    System.out.println("Type a number between 1 and 100 to start.");
                    randomNumb = (int) (Math.random() * 100);

                }else{
                    break;
                }
            }
        }
    }
    static boolean numberGuess( int value, int randomNumb){

        System.out.println(randomNumb);
        if(randomNumb != value){
            System.out.println("EEEEHM! Wrooong!");

            if(randomNumb > value){
                System.out.println("The number is higher than " + value);
                return false;

            } else if (randomNumb < value) {
                System.out.println("The number is less than " + value);
                return false;
            }
        }
            System.out.println("Congratulations! You guessed the number!");
            return true;

    }
}

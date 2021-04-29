import java.util.Random;
import java.util.Scanner;

public class NumberBaseBallGame {
    public static void main(String[] args) {
        System.out.println("---Welcome to Number Baseball Game---\n"
        + "* You can put numbers until you get 3 OUT\n"
        + "* Digit Range: [0,9]\n"
        + "* Each digit should be distinct\n\n"
        + "Put the numbers with comma: ex) 1,2,3");
        NumberBaseBallGame newGame = new NumberBaseBallGame();
        int[] usersNum;
        int ball;
        int strike;
        String countString = "";
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while(newGame.outCount < 3 && !newGame.win){
            while(!UtilClass.isValidInput(input) ||
                    newGame.getHistory().contains(input) || !UtilClass.isDistinct(input)){
                if(!UtilClass.isValidInput(input)){
                    System.out.println("Invalid input. Try again.\n");
                }
                else if(!UtilClass.isDistinct(input)){
                    System.out.println("Each digit is not distinct. Try again.\n");
                }
                else if(newGame.getHistory().contains(input)){
                    System.out.println("You already tried the same combination. Try other numbers.\n");
                }
                System.out.println("Put the numbers with comma:");
                sc = new Scanner(System.in);
                input = sc.next();
            }

            if(!newGame.win){
                newGame.addHistory(input);
                usersNum = UtilClass.extractedNum(input);
                ball = newGame.ballCount(usersNum);
                strike = newGame.strikeCount(usersNum);
                if(ball == 0 && strike == 0){
                    newGame.outCount++;
                    countString = newGame.getOutCount() + " OUT";
                    newGame.addHistory(" " + countString + "\n");
                    System.out.println(countString + "\n");
                    System.out.println(newGame.history);
                }
                else if(strike == 3){
                    newGame.win = true;
                    break;
                }
                else{
                    countString = "B: " + ball + ", S: " + strike + ", O: " + newGame.outCount + "\n";
                    newGame.addHistory(" " + countString);
                    System.out.println(countString + "\n\n");
                    System.out.println(newGame.history);
                }
                System.out.println("Put the numbers with comma:");
                sc = new Scanner(System.in);
                input = sc.next();
            }
        }

        if(newGame.outCount < 3){
            System.out.println("You win.\n" + "The number is "
                    + "[" + newGame.winningNums[0] + ", " + newGame.winningNums[1]
                    + ", " + newGame.winningNums[2] + "]");
        }
        else{
            System.out.println("You lose.\n The number is "
                    + "[" + newGame.winningNums[0] + ", " + newGame.winningNums[1]
                    + ", " + newGame.winningNums[2] + "]");
        }
    }

    private int[] winningNums;
    private boolean win;
    private String history;
    private int outCount;
    public NumberBaseBallGame(){
        Random rd = new Random();

        int first = rd.nextInt(10);
        int second = rd.nextInt(10);
        while(first == second){
            second = rd.nextInt(10);
        }
        int third = rd.nextInt(10);
        while(first == third || second == third){
            third = rd.nextInt(10);
        }

        winningNums = new int[3];
        winningNums[0] = first;
        winningNums[1] = second;
        winningNums[2] = third;
        win = false;
        history = "YOUR NUMBERS\n";
        outCount = 0;
    }

    public void addHistory(String inputString){
        history += inputString;
    }

    public int[] getWinningNums(){
        return winningNums;
    }

    public String getHistory(){
        return history;
    }

    public int getOutCount(){
        return outCount;
    }

    public int ballCount(int[] usersNum){
        int ball = 0;
        if(usersNum[0] == winningNums[1] || usersNum[0] == winningNums[2]){
            ball++;
        }
        if(usersNum[1] == winningNums[0] || usersNum[1] == winningNums[2]){
            ball++;
        }
        if(usersNum[2] == winningNums[0] || usersNum[2] == winningNums[1]){
            ball++;
        }
        return ball;
    }

    public int strikeCount(int[] usersNum){
        int strike = 0;
        for(int i = 0; i < winningNums.length; i++){
            if(winningNums[i] == usersNum[i]){
                strike++;
            }
        }
        return strike;
    }


}

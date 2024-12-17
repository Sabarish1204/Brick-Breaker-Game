package BrickBreaker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board b=new Board(7,7);
        b.setBricks(2,2);
        b.setBricks(2,3);
        b.setBricks(2,4);
        b.setBricks(3,2);
        b.setBricks(3,3);
        b.setBricks(3,4);

        boolean flag=true;
        Scanner s=new Scanner(System.in);
        while(flag){
            Board.printBoard();
            System.out.println("Ball Life Remaining : "+b.getLife());
            if(b.getLife() > 0) {
                System.out.println("Enter the direction to move the ball (st/rt/lt) : ");
                String st = s.next();
                int[] pos= b.getBallPos();
                switch (st) {
                    case "st":
                        b.intiatedBall(pos[0],pos[1],-1,0);
                        break;
                    case "rt":
                        b.intiatedBall(pos[0],pos[1],-1, +1);
                        break;
                    case "lt":
                        b.intiatedBall(pos[0],pos[1],-1, -1);
                        break;
                    default:
                        System.out.print("Enter a valid move");
                        break;
                }
            }
            else{
                flag=false;
                System.out.println("Ball Life is over");
            }
        }
    }
}

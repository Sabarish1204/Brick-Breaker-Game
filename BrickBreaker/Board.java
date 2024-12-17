package BrickBreaker;

import java.util.HashMap;

public class Board {
    private  int life=5;
    private static String[][] arr;
    private static int[] ballPos=null;
    private static HashMap<Integer,Integer> brickTrack=new HashMap<>();

    public Board(int row,int col){
        arr=new String[row][col];
//        ballPos[0]=row/2;
//        ballPos[1]=col/2;
        assignBoard(arr,row,col);
        ballPos=new int[]{row-1,col/2};
        arr[row-1][col/2]="O";
    }
    static void assignBoard(String[][] arr,int row,int col){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i==0 || j==0 || j==col-1){
                    arr[i][j]="w";
                }
                else arr[i][j]=" ";
            }
        }
        for (int i = 0; i < col; i++) {
            arr[row-1][i]="g";
        }
    }
    public void setBricks(int row,int col){
        arr[row][col]="1";
        int exactpos=getExactPos(row, col);
        brickTrack.put(exactpos,1);
    }
    public void intiatedBall(int ballrow,int ballcol,int rowdir,int coldir){
        arr[ballrow][ballcol]="g";
        moveBall(ballrow,ballcol,rowdir,coldir);
        life--;
    }
    public void moveBall(int ballrow,int ballcol,int rowDir,int colDir){
        while(!arr[ballrow][ballcol].equals("w")){
            if(arr[ballrow][ballcol].equals("1")){
                brickBreak(ballrow,ballcol);
                ballGoesDown(ballrow,ballcol);
                arr[ballPos[0]][ballPos[1]]="O";
                return;
            }
            ballrow+=rowDir;
            ballcol+=colDir;
        }
        rowDir=0;
        colDir=colDir*-1;
        if(colDir==0){
            ballGoesDown(ballrow+1,ballcol);
        }
        else{
            moveBall(ballrow,ballcol+colDir,rowDir,colDir);
        }
    }
    static void brickBreak(int row,int col){
        int exactpos=getExactPos(row, col);
        brickTrack.remove(exactpos);
        arr[row][col]=" ";
    }
    static void ballGoesDown(int ballrow,int ballcol){
        while(ballrow < arr.length){
            if(arr[ballrow][ballcol].equals("1")){
                brickBreak(ballrow,ballcol);
            }
            ballrow++;
        }
        ballPos[0]=ballrow-1;
        ballPos[1]=ballcol;
        arr[ballPos[0]][ballPos[1]]="O";
    }
    static void printBoard(){
        for (int i = 0; i < arr.length; i++) {
            for (int j=0;j< arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    static int getExactPos(int row,int col){
        int pos=(row*arr.length)+col+1;
        return pos;
    }
    public int[] getBallPos(){
        return ballPos;
    }
    public int getLife(){
        return this.life;
    }
}

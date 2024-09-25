import java.util.Random;
import java.util.Scanner;

public class XOX {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] tahta = new String[3][3];
        tahta[0][0]="-"; tahta[0][1]="-"; tahta[0][2]="-";
        tahta[1][0]="-"; tahta[1][1]="-"; tahta[1][2]="-";
        tahta[2][0]="-"; tahta[2][1]="-"; tahta[2][2]="-";
        System.out.println("-Hi, welcome to the XOX! Please enter the names" +
                        " of the players along with their guesses.");
        String str1=sc.nextLine();
        String str2=sc.nextLine();
        String name1="";
        String name2="";
        int sayi1=0;
        int sayi2 = 0;
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)==' '){
                name1=str1.substring(0,i);
                String a=str1.substring(i);
                a=a.replaceAll(" ","");
                sayi1=Integer.parseInt(a);
            }
        }
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i)==' '){
                name2=str2.substring(0,i);
                String a2=str2.substring(i);
                a2=a2.replaceAll(" ","");
                sayi2=Integer.parseInt(a2);
            }
        }

        String currentplayer="";
        String bekleyenplayer="";
        Random a = new Random();
        int tahmin=a.nextInt(100);
        //System.out.println("tahmin:" +tahmin);
        if(Math.abs(tahmin-sayi1) < Math.abs(tahmin-sayi2)){
            currentplayer=name1;
            bekleyenplayer=name2;
        }
        else {
            currentplayer=name2;
            bekleyenplayer=name1;
        }
        System.out.println("The randomly generated number is "+ tahmin+". Thus,"+currentplayer+
                " starts! It’s "+currentplayer+"’s turn");
        String currentMarker="X";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(tahta[i][j]+" ");
            }
            System.out.println("");
        }

        while(true){

            String cevap=sc.nextLine();
            if(cevap.contains("uppe") && !(cevap.contains("upper")) ){
                for(int j=4;j<cevap.length()-2;j++){
                    if(cevap.charAt(j)=='e' && cevap.charAt(j-1)=='p' &&
                        cevap.charAt(j-2)=='p' && cevap.charAt(j-3)=='u' ){
                        if(cevap.charAt(j+2)=='r'){
                            cevap=cevap.substring(0,j+1) + cevap.substring(j+2) +"";
                        }
                    }
                }
            }
            if(cevap.contains("bot") && !(cevap.contains("bottom")) ){
                for(int j=4;j<cevap.length()-2;j++){
                    if(cevap.charAt(j)=='t' && cevap.charAt(j-1)=='o' && cevap.charAt(j-2)=='b' &&
                        cevap.charAt(j+2)=='t' && cevap.charAt(j+3)=='o' && cevap.charAt(j+4)=='m' ){
                            cevap=cevap.substring(0,j+1) + cevap.substring(j+2) +"";
                    }
                }
            }


            //I will placeb my markerr to twop rightg otlherwisem Utku will win
            //It’s bestc to placef my markezr to middlew.
            if(cevap.contains("t") && !(cevap.contains("top")) ){
                for(int j=2;j<cevap.length()-3;j++){
                    if(cevap.charAt(j)=='t' && cevap.charAt(j+2)=='o' && cevap.charAt(j+3)=='p' ){
                         cevap=cevap.substring(0,j+1) +cevap.substring(j+2);
                    }
                }
            }
            if(cevap.contains("le") && !(cevap.contains("left")) ){
                for(int j=2;j<cevap.length()-3;j++){
                    if(cevap.charAt(j)=='e' && cevap.charAt(j-1)=='l' ){
                        if(cevap.charAt(j+3)=='t' && cevap.charAt(j+2)=='f'){
                            cevap=cevap.substring(0,j+1) +cevap.substring(j+2);
                        }
                    }
                }
            }
            //System.out.println(cevap);
            if(cevap.contains("upper left") ||  cevap.contains("top left")){
                tahta[0][0]=currentMarker;
            }else if(cevap.contains("upper middle") || cevap.contains("top middle")){
                tahta[0][1]=currentMarker;
            }else if(cevap.contains("upper right") || cevap.contains("top right") ){
                tahta[0][2]=currentMarker;
            }else if(cevap.contains("middle left")){
                tahta[1][0]=currentMarker;
            }else if(cevap.contains("middle right")){
                tahta[1][2]=currentMarker;
            }else if(cevap.contains("middle")){
                tahta[1][1]=currentMarker;
            }else if(cevap.contains("bottom left")){
                tahta[2][0]=currentMarker;
            }else if(cevap.contains("bottom right")){
                System.out.println("eeerdemmm");
                tahta[2][2]=currentMarker;
            }else if(cevap.contains("bottom middle")){
                tahta[2][1]=currentMarker;
            }
            boolean var=isFinished(tahta,currentMarker);
            boolean flag=true;
            if(var){
                System.out.println("Congratulations "+currentplayer+", you won!.");
                flag=false;
            }else{
                String temp=currentplayer;
                currentplayer=bekleyenplayer;
                bekleyenplayer=temp;
                if(currentMarker.equals("X")) {
                    currentMarker = "O";
                }
                else if(currentMarker.equals("O")) {
                    currentMarker = "X";
                }

            }

            // bitmediyse swap current bekleyen
            if(flag){
                System.out.println("The current state of the game is shown below. It’s " + currentplayer + "’s turn.");
            }

            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    System.out.print(tahta[i][j]+" ");
                }
                System.out.println("");
            }
            if(!flag){
                break;
            }



        }


    }

    private static boolean isFinished(String[][] tahta,String marker) {
        if(tahta[0][0].equals(marker) && tahta[0][1].equals(marker) && tahta[0][2].equals(marker)  ){
            return true;
        }
        if(tahta[1][0].equals(marker) && tahta[1][1].equals(marker) && tahta[1][2].equals(marker)  ){
            return true;
        }
        if(tahta[2][0].equals(marker) && tahta[2][1].equals(marker) && tahta[2][2].equals(marker)  ){
            return true;
        }
        if(tahta[0][0].equals(marker) && tahta[1][0].equals(marker) && tahta[2][0].equals(marker)  ){
            return true;
        }
        if(tahta[0][1].equals(marker) && tahta[1][1].equals(marker) && tahta[2][1].equals(marker)  ){
            return true;
        }
        if(tahta[0][2].equals(marker) && tahta[1][2].equals(marker) && tahta[2][2].equals(marker)  ){
            return true;
        }
        if(tahta[0][0].equals(marker) && tahta[1][1].equals(marker) && tahta[2][2].equals(marker)  ){
            return true;
        }
        if(tahta[0][2].equals(marker) && tahta[1][1].equals(marker) && tahta[2][0].equals(marker)  ){
            return true;
        }
        return false;

    }
}
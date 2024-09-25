
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(": :WELCOME: :");
        System.out.println("Please enter the sizes of the board (m x n ) : ");
        String sizes=sc.nextLine();
        int index=sizes.indexOf('x');
        String b=sizes.substring(0,index-1);
        b=b.replaceAll(" ","");
        String a=sizes.substring(index+1);
        a=a.replaceAll(" ","");
        int m=Integer.parseInt(b);
        int n=Integer.parseInt(a);
        System.out.println("Please select the difficulty (E,M,H) :");
        String difficulity= sc.nextLine();
        difficulity=difficulity.replaceAll(" ","");
        int deger = 0;



        if(difficulity.equals("E")){
            deger= (int) ((int)(m*n) * 0.15);
        } else if (difficulity.equals("M")) {
            deger= (int) ((int)(m*n) * 0.25);
        } else if (difficulity.equals("H")) {
            deger= (int) ((int)(m*n) * 0.40);
        }
        System.out.println(deger);
        String[][] tahta = new String[m][n];
        for(int i=0;i< tahta.length;i++){
            for(int j=0;j<tahta[0].length;j++){
               tahta[i][j]="o";
            }

        }
        int[][] tahtaKomsuSayilari = new int[m][n];
        int tekrar=0;

        while(true){
            Random a3 = new Random();
            Random a4= new Random();
            int deg=a3.nextInt(m);
            int deg2=a4.nextInt(n);
            if(tahtaKomsuSayilari[deg][deg2]!=-5){
                tahtaKomsuSayilari[deg][deg2]=-5;
                komsulariYerlestir(tahtaKomsuSayilari,deg,deg2);
                tekrar++;
            }
            if(tekrar==deger){
                break;
            }

        }
        for(int i=0;i< tahtaKomsuSayilari.length;i++){
            for(int j=0;j<tahtaKomsuSayilari[0].length;j++){
                System.out.print(tahtaKomsuSayilari[i][j] +"   ");
            }
            System.out.println("");
        }

        boolean control =true;
        int satirNo=0;
        int sutunNo=0;
        int durum=0;
        int flagged=0;
        int unflagged=0;
        while(control) {

            System.out.println("Please make a move:");
            String koordinat = sc.nextLine();
            if (koordinat.contains("F")) {
                flagged = 1;
                int index2 = koordinat.indexOf('F');
                koordinat = koordinat.substring(0, index2-1);
               // koordinat=koordinat.replaceAll(" ","");
                System.out.println(koordinat);
            }
            if (koordinat.contains("U")) {
                unflagged = 1;
                int index3 = koordinat.indexOf('U');
                //koordinat=koordinat.replaceAll(" ","");
                System.out.println(koordinat);
                koordinat = koordinat.substring(0, index3-1);
            }
            satirNo = koordinatCeviriciSatirNo(koordinat, m);
            sutunNo = koordinatCeviriciSutunNo(koordinat, n);

            if (flagged == 1) {
                if (tahta[satirNo][sutunNo].equals("o")) {
                    tahta[satirNo][sutunNo] = "F";
                } else {
                    System.out.println("Open cells cannot be flagged .");
                }

            } else if (unflagged == 1) {
                if (tahta[satirNo][sutunNo].equals("o")) {
                    System.out.println("Only flagged cells can be unflagged .");
                }else {
                    if (tahta[satirNo][sutunNo].equals("-")) {
                        System.out.println("Opened cells cannot be unflagged .");
                    } else {
                        tahta[satirNo][sutunNo] = "o";
                        System.out.println("Cell is unflagged");

                    }
                }

            }
            else { // diger durumlar

                if (tahtaKomsuSayilari[satirNo][sutunNo] == -5) { //kaybetme

                    for (int i = 0; i < tahtaKomsuSayilari.length; i++) {
                        for (int j = 0; j < tahtaKomsuSayilari[0].length; j++) {
                            if (tahtaKomsuSayilari[i][j] == -5) {
                                tahta[i][j] = "X";
                            }
                        }
                    }
                    printTablo(tahta);
                    System.out.println("You lost ,better luck next time.");
                    break;
                }


                if (tahta[satirNo][sutunNo].equals("o")) {
                    if (tahtaKomsuSayilari[satirNo][sutunNo] == 0) {
                        tahta[satirNo][sutunNo] = "-";
                        openNeighbours(tahta,satirNo,sutunNo,tahtaKomsuSayilari);
                        //openNeighborsAutomatically
                    }else {
                        tahta[satirNo][sutunNo] = tahtaKomsuSayilari[satirNo][sutunNo] + "";
                    }
                } else {
                    System.out.println("Cell is already open");
                }

                if(kazandiMi(deger,tahta,tahtaKomsuSayilari)){
                    printTablo(tahta);
                    System.out.println("Congratulations , you won.\n");
                    break;
                }

            }

            durum=0;
            printTablo(tahta);
            flagged=0;
            unflagged=0;

        }



    } // main

    private static void openNeighbours(String[][] tahta, int satir, int sutun, int[][] tahtaKomsuSayilari){
        if (tahtaKomsuSayilari[satir][sutun] == 0) {
            tahta[satir][sutun] = "-"; //gelenlerin hepsi 0a esit olacak
       }

        if(satir-1>=0 ){ //yukari
            if( !(tahta[satir - 1][sutun].equals("-")) ) {
                if (tahtaKomsuSayilari[satir - 1][sutun] != 0 ) {
                    tahta[satir - 1][sutun] = tahtaKomsuSayilari[satir - 1][sutun] + "";
                } else {
                    openNeighbours(tahta, satir - 1, sutun, tahtaKomsuSayilari);
                }
            }
        }
        if (satir+1<tahtaKomsuSayilari.length) { //asagi
            if(!(tahta[satir+1][sutun].equals( "-"))) {
                if (tahtaKomsuSayilari[satir + 1][sutun] != 0) {
                    tahta[satir + 1][sutun] = tahtaKomsuSayilari[satir + 1][sutun] + "";
                } else {
                    openNeighbours(tahta, satir + 1, sutun, tahtaKomsuSayilari);
                }
            }
        }
        if(sutun+1< tahtaKomsuSayilari[0].length){ //saga
            if( !(tahta[satir][sutun+1].equals("-")) ) {
                if (tahtaKomsuSayilari[satir][sutun + 1] != 0) {
                    tahta[satir][sutun + 1] = tahtaKomsuSayilari[satir][sutun + 1] + "";
                } else {
                    openNeighbours(tahta, satir, sutun + 1, tahtaKomsuSayilari);
                }
            }
        }
        if (sutun-1>=0) { //sola
            if( !(tahta[satir][sutun-1].equals("-")) ) {
                if (tahtaKomsuSayilari[satir][sutun - 1] != 0) {
                    tahta[satir][sutun - 1] = tahtaKomsuSayilari[satir][sutun - 1] + "";
                } else {
                    openNeighbours(tahta, satir, sutun - 1, tahtaKomsuSayilari);
                }
            }
        }
        if (sutun-1>=0 && satir-1>=0) { // sol ust
            if( !(tahta[satir-1][sutun-1].equals("-")) ) {
                if (tahtaKomsuSayilari[satir - 1][sutun - 1] != 0) {
                    tahta[satir - 1][sutun - 1] = tahtaKomsuSayilari[satir - 1][sutun - 1]+"";
                } else {
                    openNeighbours(tahta, satir - 1, sutun - 1, tahtaKomsuSayilari);
                }
            }
        }
        if (sutun+1< tahtaKomsuSayilari[0].length && satir-1>=0) {  // sag ust
            if( !(tahta[satir-1][sutun+1].equals("-")) ) {
                if (tahtaKomsuSayilari[satir - 1][sutun + 1] != 0) {
                    tahta[satir - 1][sutun + 1] = tahtaKomsuSayilari[satir - 1][sutun + 1] + "";
                } else {
                    openNeighbours(tahta, satir - 1, sutun + 1, tahtaKomsuSayilari);
                }
            }
        }
        if (sutun+1< tahtaKomsuSayilari[0].length && satir+1<tahtaKomsuSayilari.length) { // SAG ALT
            if( !(tahta[satir+1][sutun+1].equals("-")) ) {
                if (tahtaKomsuSayilari[satir + 1][sutun + 1] != 0) {
                    tahta[satir + 1][sutun + 1] = tahtaKomsuSayilari[satir + 1][sutun + 1] + "";
                } else {
                    openNeighbours(tahta, satir + 1, sutun + 1, tahtaKomsuSayilari);
                }
            }
        }
        if (sutun-1>=0 && satir+1<tahtaKomsuSayilari.length ) { // sol alt
            if( !(tahta[satir+1][sutun-1].equals("-")) ) {
                if (tahtaKomsuSayilari[satir + 1][sutun - 1] != 0) {
                    tahta[satir + 1][sutun - 1] = tahtaKomsuSayilari[satir + 1][sutun - 1] + "";
                } else {
                    openNeighbours(tahta, satir + 1, sutun - 1, tahtaKomsuSayilari);
                }
            }
        }





    }

    private static boolean kazandiMi(int deger, String[][] tahta,int[][]tahtakomsuSayilari) {
        int count=0;
        for(int i=0;i< tahta.length;i++){
            for(int j=0;j< tahta.length;j++){
                if(tahta[i][j].equals("o") ){
                    count++;
                }
            }
        }
        if(count==deger){
            return true;
        }
        return false;
    }

    private static void printTablo(String[][] tahta) {
        for(int i=0;i< tahta.length;i++){
            for(int j=0;j<tahta[0].length;j++){
                System.out.print(tahta[i][j] +" ");
            }
            System.out.println("");
        }
    }

    private static int koordinatCeviriciSutunNo(String koordinat,int sutunsayisi) {
        int index=koordinat.indexOf(',');
        String her=koordinat.substring(index+1);
        her=her.replaceAll(" ","");
        return Integer.parseInt(her)-1;
    }

    private static int koordinatCeviriciSatirNo(String koordinat,int satirsayisi) {
        int index=koordinat.indexOf(',');
        String her2=koordinat.substring(0,index);
        her2=her2.replaceAll(" ","");
        System.out.println(her2 + " "+ her2.length());
        return satirsayisi - Integer.parseInt(her2);
    }

    private static void komsulariYerlestir(int[][] tahtaKomsuSayilari, int satir, int sutun) {
        if(satir-1>=0 ){ //yukari
            if(tahtaKomsuSayilari[satir-1][sutun]!=-5) {
                tahtaKomsuSayilari[satir - 1][sutun]++;
            }
        }
        if (satir+1<tahtaKomsuSayilari.length) { //asagi
            if(tahtaKomsuSayilari[satir+1][sutun]!=-5) {
                tahtaKomsuSayilari[satir + 1][sutun]++;
            }
        }
        if(sutun+1< tahtaKomsuSayilari[0].length){ //saga
            if(tahtaKomsuSayilari[satir][sutun+1]!=-5) {
                tahtaKomsuSayilari[satir][sutun + 1]++;
            }
        }
        if (sutun-1>=0) { //sola
            if(tahtaKomsuSayilari[satir][sutun-1]!=-5) {
                tahtaKomsuSayilari[satir][sutun - 1]++;
            }
        }
        if (sutun-1>=0 && satir-1>=0) { // sol ust
            if(tahtaKomsuSayilari[satir-1][sutun-1]!=-5) {
                tahtaKomsuSayilari[satir - 1][sutun - 1]++;
            }
        }
        if (sutun+1< tahtaKomsuSayilari[0].length && satir-1>=0) {  // sag ust
            if(tahtaKomsuSayilari[satir-1][sutun+1]!=-5) {
                tahtaKomsuSayilari[satir - 1][sutun + 1]++;
            }
        }
        if (sutun+1< tahtaKomsuSayilari[0].length && satir+1<tahtaKomsuSayilari.length) { // SAG ALT
            if(tahtaKomsuSayilari[satir+1][sutun+1]!=-5) {
                tahtaKomsuSayilari[satir + 1][sutun + 1]++;
            }
        }
        if (sutun-1>=0 && satir+1<tahtaKomsuSayilari.length ) { // sol alt
            if(tahtaKomsuSayilari[satir+1][sutun-1]!=-5) {
                tahtaKomsuSayilari[satir + 1][sutun - 1]++;
            }
        }

        }

    }



package service;

public class KonversiNilai {

    public static String getHuruf(double nilai){

        if(nilai>=85)
            return "A";

        if(nilai>=80)
            return "A-";

        if(nilai>=75)
            return "B+";

        if(nilai>=70)
            return "B";

        if(nilai>=65)
            return "B-";

        if(nilai>=60)
            return "C+";

        if(nilai>=55)
            return "C";

        if(nilai>=40)
            return "D";

        return "E";

    }

}
package com.employee.app.base;

public class Utils {

    private Utils() {}

    public static String generateAlphabetPoint(Double avg, Double point) {
        double percent;
        Double different = point - avg;

        String grade;
        if (different > 0) {
            percent = different/avg*100;
            if (percent > 20.0) {
                grade = "A";
            }else {
                grade = "B";
            }
        }else {
            percent = Math.abs(different)/avg*100;
            if (percent <= 10.0) {
                grade = "C";
            }else if(percent > 10 && percent<=20){
                grade = "D";
            }else {
                grade = "E";
            }
        }
        return grade;
    }
}

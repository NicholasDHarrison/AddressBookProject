package com.tts;

public class Validation {
    public static void main(String[] args) {
        System.out.println(validateEmailAddress("@gmail.com"));
        System.out.println(formatPhoneNumber("3095308545"));

    }

    public static boolean validatePhoneNumber(String phoneNum) {
        String pattern = "\\d{10}";//1234567890
        // current pattern recognizes any string of digits \d for numbers
        if (phoneNum.matches(pattern))
            return true;
        return false;
    }

    public static boolean validateEmailAddress(String email) {
        String pattern = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";

        if (email.matches(pattern))
            return true;
        return false;


    }
    public static String formatPhoneNumber(String phone){
        String formatPhone = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        return  formatPhone;
    }
    public static boolean validateFirstName(String fname){
        String pattern = "^[A-Za-z\\s]+$";;// Accept only  String and  space
        if(fname.matches(pattern))
            return true;
        return false;

    }
    public static boolean validateLastName(String lname){
        String pattern = "^[A-Za-z\\s]+$";;// Accept only  String and  space
        if(lname.matches(pattern))
            return true;
        return false;

    }
    public static boolean exitApp(String input){
        if(input.equalsIgnoreCase("q")||input.equalsIgnoreCase("quit"))
            return true;
        return false;
    }
}

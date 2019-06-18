package com.rayanandisheh.peysepar.passenger.helpers;

public class Validate {

    public static boolean mobile (String string) {
        return string.length() == 11 && string.startsWith("09");
    }

    public static boolean password (String string) {
        return string.length() > 3;
    }

    public static boolean firstName(String firstName) {
        return firstName.length() > 2;
    }


    public static boolean lastName(String lastName) {
        return lastName.length() > 2;
    }

    public static boolean nationalCode(String nCode) {
        return nCode.length() == 10;
    }

    public static boolean validityAmount(String validityAmount){
        return validityAmount.length()>4;
    }


    public static boolean repeatPassword(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static boolean activationCode(String string) {
        return string.length() > 2;
    }

    public static boolean url(String url) {
        return url != null
                && url.length() > 5
                && (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://"))
                && !(url.endsWith("/") || url.endsWith("\\"))
                && url.contains(".")
                && !url.contains(" ")
                && !url.contains("_");
    }
    public static boolean downloadUrl(String url) {
        return url != null
                && url.length() > 5
                && (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://"))
                && url.contains(".")
                && !url.contains(" ");
    }
}

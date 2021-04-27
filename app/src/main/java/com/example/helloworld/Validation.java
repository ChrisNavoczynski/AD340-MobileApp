package com.example.helloworld;

import android.widget.EditText;
import java.util.regex.Pattern;
import java.util.Calendar;
import static java.lang.Integer.parseInt;

public class Validation {

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValidAge(EditText editText, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        String [] dateParts = text.split("/");

        if (dateParts.length == 3) {
            String year = dateParts[2];
            int curYear = Calendar.getInstance().get(Calendar.YEAR);
            int findAge = curYear - parseInt(year);
            // clearing the error, if it was previously set by some other values
            editText.setError(null);

            // text required and editText is blank, so return false
            if ( required && !hasText(editText) ) return false;

            // pattern doesn't match so returning false
            if (required && (findAge < 18)) {
                editText.setError(errMsg);
                return false;
            };
        }
        return true;
    }

    public static String calculateAge(EditText editText) {

        String text = editText.getText().toString().trim();
        String [] dateParts = text.split("/");
        int findAge = 0;

        if (dateParts.length == 3) {
            String year = dateParts[2];
            int curYear = Calendar.getInstance().get(Calendar.YEAR);
            findAge = curYear - parseInt(year);
        }
        return Integer.toString(findAge);
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(Constants.REQUIRED_MSG);
            return false;
        }
        return true;
    }
}

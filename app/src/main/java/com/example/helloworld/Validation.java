package com.example.helloworld;

import android.widget.EditText;
import java.util.Calendar;
import static java.lang.Integer.parseInt;

public class Validation {

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

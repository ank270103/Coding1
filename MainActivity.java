package com.example.calculatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        int[] buttons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonDecimal, R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply,
                R.id.buttonDivide, R.id.buttonEquals, R.id.buttonClear
        };

        for (int id : buttons) {
            findViewById(id).setOnClickListener(v -> onDigit((Button) v));
        }

        findViewById(R.id.buttonClear).setOnClickListener(v -> onClear());
        findViewById(R.id.buttonEquals).setOnClickListener(v -> onEqual());
    }

    private void onDigit(Button view) {
        if (stateError) {
            editText.setText(view.getText());
            stateError = false;
        } else {
            editText.append(view.getText());
        }

        lastNumeric = true;
    }

    private void onClear() {
        editText.setText("");
        lastNumeric = false;
        stateError = false;
        lastDot = false;
    }

    private void onEqual() {
        if (lastNumeric) {
            String txt = editText.getText().toString();
            try {
                double result = evaluate(txt);
                editText.setText(Double.toString(result));
                lastDot = true;
            } catch (Exception e) {
                editText.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }

    private double evaluate(String expression) throws Exception {
        // This is a simple implementation for the sake of this example.
        // A more comprehensive approach would involve parsing and evaluating the expression correctly.
        return Double.parseDouble(expression);
    }
}

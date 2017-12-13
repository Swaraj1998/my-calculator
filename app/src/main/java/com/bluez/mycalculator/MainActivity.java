package com.bluez.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    String exp = "";
    private int i;

    private void pushValue(char[] chExp, Stack<Float> values) {
        StringBuilder num = new StringBuilder();
        int countPeriod = 0;
        if(chExp[i] == '-'){
            num.append('-');
            i++;
        }
        while(i < chExp.length && (chExp[i] >= '0' &&
                chExp[i] <= '9' ||
                chExp[i] == '.' ||
                chExp[i] == 'E')) {
            if(chExp[i] == '.') countPeriod++;
            num.append(chExp[i++]);
        }
        if(countPeriod > 1) {
            exp = "";
            Toast.makeText(getApplicationContext(),
                    R.string.multiple_periods,
                    Toast.LENGTH_SHORT)
                    .show();
        }
        else
            values.push(Float.parseFloat(num.toString()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toast.makeText(getApplicationContext(), R.string.dev, Toast.LENGTH_SHORT).show();

        final TextView expView = findViewById(R.id.textView);
        Button bn0 = findViewById(R.id.button0);
        Button bn1 = findViewById(R.id.button1);
        Button bn2 = findViewById(R.id.button2);
        Button bn3 = findViewById(R.id.button3);
        Button bn4 = findViewById(R.id.button4);
        Button bn5 = findViewById(R.id.button5);
        Button bn6 = findViewById(R.id.button6);
        Button bn7 = findViewById(R.id.button7);
        Button bn8 = findViewById(R.id.button8);
        Button bn9 = findViewById(R.id.button9);
        Button bnAdd = findViewById(R.id.button_add);
        Button bnSub =  findViewById(R.id.button_sub);
        Button bnEqual = findViewById(R.id.button_equal);
        Button bnMult = findViewById(R.id.button_mult);
        Button bnDiv = findViewById(R.id.button_div);
        Button bnC = findViewById(R.id.button_c);

        expView.setText(exp);

        bn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "0";
                expView.setText(exp);
            }
        });

        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "1";
                expView.setText(exp);
            }
        });

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "2";
                expView.setText(exp);
            }
        });

        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "3";
                expView.setText(exp);
            }
        });

        bn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "4";
                expView.setText(exp);
            }
        });

        bn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "5";
                expView.setText(exp);
            }
        });

        bn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "6";
                expView.setText(exp);
            }
        });

        bn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "7";
                expView.setText(exp);
            }
        });

        bn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "8";
                expView.setText(exp);
            }
        });

        bn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "9";
                expView.setText(exp);
            }
        });

        bnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char[] chExp = exp.toCharArray();
                if (chExp.length != 0) {
                    if (chExp[chExp.length - 1] == '+' ||
                            chExp[chExp.length - 1] == '/' ||
                            chExp[chExp.length - 1] == '*' ||
                            chExp[chExp.length - 1] == '-') {
                        exp = exp.substring(0, exp.length() - 1);
                    }
                }
                exp += "+";
                expView.setText(exp);
            }
        });

        bnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char[] chExp = exp.toCharArray();
                if(chExp.length != 0) {
                    if(chExp[chExp.length - 1] == '+') {
                        exp = exp.substring(0, exp.length()-1);
                        exp += '-';
                    }
                    else if (chExp[chExp.length - 1] != '-') {
                        exp += '-';
                    }
                }
                else
                    exp += '-';
                expView.setText(exp);
            }
        });

        bnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char[] chExp = exp.toCharArray();
                if(chExp.length != 0) {
                    if (chExp[chExp.length - 1] == '+' ||
                            chExp[chExp.length - 1] == '/' ||
                            chExp[chExp.length - 1] == '*' ||
                            chExp[chExp.length - 1] == '-') {
                        exp = exp.substring(0, exp.length() - 1);
                    }
                    exp += "*";
                    expView.setText(exp);
                }
            }
        });

        bnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char[] chExp = exp.toCharArray();
                if(chExp.length != 0) {
                    if (chExp[chExp.length - 1] == '+' ||
                            chExp[chExp.length - 1] == '/' ||
                            chExp[chExp.length - 1] == '*' ||
                            chExp[chExp.length - 1] == '-') {
                        exp = exp.substring(0, exp.length() - 1);
                    }
                    exp += "/";
                    expView.setText(exp);
                }
            }
        });

        bnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Evaluation of Expression

                char[] chExp = exp.toCharArray();
                Stack<Float> values = new Stack<>();
                Stack<Character> opr = new Stack<>();
                i = 0;

                if(!exp.equals("")) {
                    if(chExp[0] == '-' || chExp[0] == '.')
                        pushValue(chExp, values);
                    if(chExp[0] == '+')
                        i++;
                    if (!(chExp[chExp.length - 1] >= '0' && chExp[chExp.length - 1] <= '9'))
                        chExp = Arrays.copyOfRange(chExp, 0, chExp.length - 1);
                }

                for(; i < chExp.length; i++) {

                    if(chExp[i] >= '0' && chExp[i] <= '9' || chExp[i] == '.') {
                        pushValue(chExp, values);
                        if(i == chExp.length) break;
                    }

                    if(chExp[i] == '+' || chExp[i] == '-') {
                        opr.push(chExp[i]);
                        continue;
                    }

                    if(chExp[i] == '*') {
                        i++;
                        pushValue(chExp, values);
                        values.push(values.pop()*values.pop());
                        if(i == chExp.length) break;
                        i--;
                    }

                    if(chExp[i] == '/') {
                        i++;
                        pushValue(chExp, values);
                        i--;
                        float n = values.pop();
                        float m = values.pop();
                        if(n != 0)
                            values.push(m/n);
                        else {
                            Toast.makeText(getApplicationContext(),
                                    R.string.div0,
                                    Toast.LENGTH_SHORT)
                                    .show();
                            exp = "";
                            break;
                        }
                    }
                }

                while(!opr.empty() && !(exp.equals(""))) {

                    switch (opr.pop()) {
                        case '+':
                            values.push(values.pop()+values.pop());
                            break;
                        case '-':
                            float n = values.pop();
                            float m = values.pop();
                            values.push(m-n);
                            break;
                    }
                }
                if(!exp.equals("")) {
                    exp = values.pop().toString();
                    expView.setText(exp);
                }
            }
        });

        bnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!exp.equals(""))
                    exp = exp.substring(0, exp.length() - 1);
                expView.setText(exp);
            }
        });

        bnC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                exp = "";
                expView.setText(exp);
                return true;
            }
        });

        bn0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                    exp += '.';
                    expView.setText(exp);
                return true;
            }
        });

    }

}

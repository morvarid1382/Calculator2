package com.pouya11.calculator3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button[] btnNumbers = new Button[10];
    Button btnDot;
    Button btnClear;
    Button btnClearEverything;
    Button btnSum;
    Button btnSub;
    Button btnDivide;
    Button btnMultiply;
    Button btnResult;
    Button btnExit;
    EditText txtNumber;

    double dMemory = 0;
    int iState = MainActivity.STATE_NA;

    protected static final int STATE_NA = 0;
    protected static final int STATE_SUM = 1;
    protected static final int STATE_SUB = 2;
    protected static final int STATE_MULTIPLY = 3;
    protected static final int STATE_DIVIDE = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNumbers[0] = (Button) findViewById(R.id.button10);
        btnNumbers[1] = (Button) findViewById(R.id.button);
        btnNumbers[2] = (Button) findViewById(R.id.button2);
        btnNumbers[3] = (Button) findViewById(R.id.button3);
        btnNumbers[4] = (Button) findViewById(R.id.button4);
        btnNumbers[5] = (Button) findViewById(R.id.button5);
        btnNumbers[6] = (Button) findViewById(R.id.button6);
        btnNumbers[7] = (Button) findViewById(R.id.button7);
        btnNumbers[8] = (Button) findViewById(R.id.button8);
        btnNumbers[9] = (Button) findViewById(R.id.button9);
        btnDot = (Button) findViewById(R.id.button17);
        btnClear = (Button) findViewById(R.id.button16);
        btnClearEverything = (Button) findViewById(R.id.button15);
        btnSum = (Button) findViewById(R.id.button11);
        btnSub = (Button) findViewById(R.id.button12);
        btnDivide = (Button) findViewById(R.id.button14);
        btnMultiply = (Button) findViewById(R.id.button13);
        btnResult = (Button) findViewById(R.id.button18);
        btnExit = (Button) findViewById(R.id.button19);
        txtNumber = (EditText) findViewById(R.id.editText);

        for(Button b: btnNumbers) {
            b.setOnClickListener((View.OnClickListener) new BtnNumbersClicked());
        }
        btnClear.setOnClickListener(new BtnClearClicked());
        btnSum.setOnClickListener(new BtnOperationClicked());
        btnSub.setOnClickListener(new BtnOperationClicked());
        btnMultiply.setOnClickListener(new BtnOperationClicked());
        btnDivide.setOnClickListener(new BtnOperationClicked());
    }

    protected void clear(){
        txtNumber.setText("");
    }

    protected double getNumberAndClear(){
        double result = 0;
        try {
            result = Double.parseDouble(txtNumber.getText().toString());
            MainActivity.this.clear();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,
                    R.string.something_wrong,
                    Toast.LENGTH_SHORT)
                    .show();
        }
        return result;
    }

    protected class BtnNumbersClicked implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            MainActivity.this.txtNumber.append(((Button) v).getText());
        }
    }

    protected class BtnClearClicked implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button15:
                    MainActivity.this.iState =MainActivity.STATE_NA;
                    MainActivity.this.dMemory = 0;
                case R.id.button16:

                    MainActivity.this.clear();
            }
        }
    }

    protected class BtnOperationClicked implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button11:
                    MainActivity.this.iState = MainActivity.STATE_SUM;
                    break;
                case R.id.button12:
                    MainActivity.this.iState = MainActivity.STATE_SUB;
                    break;
                case R.id.button13:
                    MainActivity.this.iState = MainActivity.STATE_MULTIPLY;
                    break;
                case R.id.button14:
                    MainActivity.this.iState = MainActivity.STATE_DIVIDE;
                    break;
                default:
                    MainActivity.this.iState = MainActivity.STATE_NA;
            }

            MainActivity.this.dMemory = MainActivity.this.getNumberAndClear();


        }

        protected class BtnResultClicked implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                double result = 0;
                switch (MainActivity.this.iState){
                    case MainActivity.STATE_SUM:
                        result = MainActivity.this.getNumberAndClear()
                                + MainActivity.this.dMemory;
                        break;
                    case MainActivity.STATE_SUB:
                        result = MainActivity.this.dMemory
                                - MainActivity.this.getNumberAndClear();
                        break;
                    case MainActivity.STATE_MULTIPLY:
                        result = MainActivity.this.getNumberAndClear()
                                * MainActivity.this.dMemory;
                        break;
                    case MainActivity.STATE_DIVIDE:
                        result = MainActivity.this.dMemory
                                / MainActivity.this.getNumberAndClear();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,
                                R.string.something_wrong,
                                Toast.LENGTH_LONG)
                                .show();
                }

                txtNumber.setText(result + "");
            }
        }
    }
    }


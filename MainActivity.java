package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.location.SettingInjectorService;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * /Окно калькулятора
 */
public class MainActivity extends AppCompatActivity {

    Button mBtn0;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;
    TextView mDisplay;
    Button mBack;
    Button mClear;
    Button mComma;
    Button mSign;
    Button mPlus;
    Button mMinus;
    Button mDiv;
    Button mMul;
    Button mResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn0=findViewById(R.id.btn0);
        mBtn1=findViewById(R.id.btn1);
        mBtn2=findViewById(R.id.btn2);
        mBtn3=findViewById(R.id.btn3);
        mBtn4=findViewById(R.id.btn4);
        mBtn5=findViewById(R.id.btn5);
        mBtn6=findViewById(R.id.btn6);
        mBtn7=findViewById(R.id.btn7);
        mBtn8=findViewById(R.id.btn8);
        mBtn9=findViewById(R.id.btn9);
        mDisplay=findViewById(R.id.Display);
        mBack=findViewById(R.id.btnBACK);
        mClear=findViewById(R.id.btnC);
        mComma=findViewById(R.id.btnTochka);
        mSign=findViewById(R.id.btnABS);
        mPlus=findViewById(R.id.btnPlus);
        mMul=findViewById(R.id.btnumnogenie);
        mMinus=findViewById(R.id.btnMinus);
        mDiv=findViewById(R.id.btnDelenie);
        mResult=findViewById(R.id.btnRavno);
        View.OnClickListener numberListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        };
        mBtn0.setOnClickListener(numberListener);
        mBtn1.setOnClickListener(numberListener);
        mBtn2.setOnClickListener(numberListener);
        mBtn3.setOnClickListener(numberListener);
        mBtn4.setOnClickListener(numberListener);
        mBtn5.setOnClickListener(numberListener);
        mBtn6.setOnClickListener(numberListener);
        mBtn7.setOnClickListener(numberListener);
        mBtn8.setOnClickListener(numberListener);
        mBtn9.setOnClickListener(numberListener);
        mComma.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onOperatorListener(v);
            }
        };
        View.OnClickListener CListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onCListener(v);
            }
        };
        View.OnClickListener BACKListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBACKClick(v);
            }
        };
        View.OnClickListener ABSListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onABSClick(v);
            }
        };
        mClear.setOnClickListener(CListener);
        mPlus.setOnClickListener(operatorListener);
        mMinus.setOnClickListener(operatorListener);
        mMul.setOnClickListener(operatorListener);
        mDiv.setOnClickListener(operatorListener);
        mBack.setOnClickListener(BACKListener);
        mSign.setOnClickListener(ABSListener);

        View.OnClickListener resultListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onResultListener(v);
            }
        };

        mResult.setOnClickListener(resultListener);
    }
    /**
     * обработка нажатия на числовую кнопку
     * @param button - кнопка
     */
    public void onCListener(View button){
        mOperator="";
        mValue=0f;
       mDisplay.setText("0");
    }
    public void onNumberClick(View button){
        String number = ((Button)button).getText().toString();
        String display = mDisplay.getText().toString();
        if(display.equals("0"))
            display = number;
        else
            display += number;
        mDisplay.setText(display);
    }
    public void onABSClick(View button){
        String display = mDisplay.getText().toString();
        float value = Float.parseFloat(display);
        value=-1*value;
        DecimalFormat format = new DecimalFormat("0.######");
        format.setRoundingMode(RoundingMode.DOWN);
        String resultText = format.format(value);
        //5
        mDisplay.setText(resultText);
    }
    public void onBACKClick(View button){
        String display = mDisplay.getText().toString();
        mDisplay.setText(display.substring(0,display.length()-1));
    }


//Состояние калькулятора
    float mValue = 0;
    String mOperator = "";
    /**
     * обработка нажатия на оператор
     * @param button - кнопка
     */
    public void onOperatorListener(View button){
        //1
        String operator =((Button)button).getText().toString();
        mOperator = operator;
        //2
        String display = mDisplay.getText().toString();
        mValue = Float.parseFloat(display);
        //3
        mDisplay.setText("0");

    }

    public void onResultListener(View button){
        //1
        String display = mDisplay.getText().toString();
        float value = Float.parseFloat(display);
        //2
        float result = value;
        //3
        switch (mOperator)
        {
            case"+":
            {
                result = value + mValue;
                break;
            }
            case"-":
            {
                result = mValue - value ;
                break;
            }
            case"/":
            {
                result =mValue / value;
                break;
            }
            case"*":
            {
                result = value * mValue;
                break;
            }
            case"=":
            {
                result = value;
                break;
            }

            //TODO: другие операторы
        }
        //4
        DecimalFormat format = new DecimalFormat("0.######");
        format.setRoundingMode(RoundingMode.DOWN);
        String resultText = format.format(result);
        //5
        mDisplay.setText(resultText);
        //6
        mValue = result;
        mOperator = "";
    }
}

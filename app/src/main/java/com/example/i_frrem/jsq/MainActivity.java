package com.example.i_frrem.jsq;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    private Button btn_0, btn_1,  btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    private Button btn_point, btn_clear, btn_del, btn_plus, btn_minus, btn_multiply, btn_divide, btn_equal;
    private EditText et_input;
    private boolean clear_flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化控件
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_point = findViewById(R.id.btn_point);
        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_del);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_equal = findViewById(R.id.btn_equal);
        et_input = findViewById(R.id.et_input);

        //加上监听器
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(clear_flag){
                    clear_flag =false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button)v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(clear_flag){
                    clear_flag =false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button)v).getText() + " ");
                break;
            case R.id.btn_del:
                if(clear_flag){
                    clear_flag =false;
                    et_input.setText("");
                }else if(str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0, str.length()-1));
                }
                break;
            case R.id.btn_clear:
                clear_flag =false;
                et_input.setText("");
                break;
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    private void getResult(){
        String exp = et_input.getText().toString();
        if(exp==null||exp.equals("")){
            return;
        }

        if(!exp.contains(" ")){
            return;
        }

        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;

        double result = 0;
        String s1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ")+1, exp.indexOf(" ")+2);
        String s2 = exp.substring(exp.indexOf(" ")+3);

        if(!s1.equals("")&&!s2.equals("")){
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);

            if(op.equals("+")){
                result = d1 + d2;
            }else if(op.equals("-")){
                result = d1 - d2;
            }else if(op.equals("×")){
                result = d1 * d2;
            }else if(op.equals("÷")){
                if(d2 == 0){
                    result = 0;
                }else{
                    result = d1 / d2;
                }
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")){
                long r = (long) result;
                et_input.setText(r+"");
            }else{
                et_input.setText(result+"");
            }
        }else if(!s1.equals("")&&s2.equals("")){
            et_input.setText(exp);
        }else if(s1.equals("")&&!s2.equals("")){
            double d2 = Double.parseDouble(s2);

            if(op.equals("+")){
                result = d2;
            }else if(op.equals("-")){
                result = 0 - d2;
            }else if(op.equals("×")){
                result = 0;
            }else if(op.equals("÷")){
                result = 0;
            }
            if(!s1.contains(".")&&!s2.contains(".")){
                int r = (int)result;
                et_input.setText(r+"");
            }else{
                et_input.setText(result+"");
            }
        }else{
            et_input.setText("");
        }
    }
}

package com.example.zgfei.spannablestringtest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.BaseMovementMethod;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.grantland.widget.AutofitTextView;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT = "ClickableSpan，设置可点击的文本，设置这个hahahahahaha用户点击事件，至于点击事件用户可以自定义，*****就像效果图显示一样，SpannableString spannableString = new SpannableString(\"为文字设置点击事件\");";
    private TextView textViewDefault;
    private TextView textView;
    private TextView textView2;
    private AutofitTextView autofitTextView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDefaultTextView();
        setTextView();
        setText(false, textView, TEXT);
        setTextView2();
        setText(true, textView2, TEXT);

        setAutoFitTextView();
        setText(false, autofitTextView, TEXT);

        setEditText();
    }

    private void setDefaultTextView() {
        textViewDefault = findViewById(R.id.text_view_default);
        textViewDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "default 点击了全部文字", Toast.LENGTH_SHORT).show();
                Log.e("zgf", "default 1111111");
            }
        });
        textViewDefault.setText(TEXT);
    }

    private void setTextView() {
        textView = findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了全部文字", Toast.LENGTH_SHORT).show();
                Log.e("zgf", "1111111");
            }
        });
    }

    private void setTextView2() {
        textView2 = findViewById(R.id.text_view_two);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了全部文字", Toast.LENGTH_SHORT).show();
                Log.e("zgf", "1111111");
            }
        });
    }

    private void setAutoFitTextView() {
        autofitTextView = findViewById(R.id.auto_fix_text_view);
        autofitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了全部文字", Toast.LENGTH_SHORT).show();
                Log.e("zgf", "auto fit text view");
            }
        });
    }

    private void setText(boolean isSetString, TextView textView, String string) {
        // 设置点击后文字选中的颜色
        textView.setHighlightColor(Color.parseColor("#00000000"));
        textView.setText(setSpannableString(isSetString, string));
        textView.setMovementMethod(ClickableMovementMethod.getInstance());
        // textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnTouchListener(new LinkMovementMethodOverride());
    }

    private void setEditText() {
        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                Log.e("zgf", "====string====" + string);
                setText(false, textView, string);
                setText(true, textView2, string);           }
        });
    }

    private SpannableString setSpannableString(boolean isSetString, String str) {
        String string;
        if (isSetString) {
            string = setString(str);
        } else {
            string = str;
        }

        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new MyClickSpan(this), 0, 13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new MyClickSpan(this), 14, 22, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new MyClickSpan(this), 23, 38, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public String setString(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    private class MyClickSpan extends ClickableSpan {
        private Context context;

        public MyClickSpan(Context context) {
            this.context = context;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            // ds.setColor(Color.BLUE);
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(context, "点击了字体", Toast.LENGTH_SHORT).show();
            Log.e("zgf", "22222222");
        }
    }
}

package com.xingtam.testmain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xingtam.tamutils.R;
import com.xingtam.thirdparty.inputcheck.FireEye;
import com.xingtam.thirdparty.inputcheck.FireEyeEnv;
import com.xingtam.thirdparty.inputcheck.Form;
import com.xingtam.thirdparty.inputcheck.StaticPattern;
import com.xingtam.thirdparty.inputcheck.TextViewLoader;
import com.xingtam.thirdparty.inputcheck.ValuePattern;

public class InputCheckActivity extends Activity{

    FireEye fireEye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_check);

        View formView = findViewById(R.id.form);
        Form form = new Form(formView);

        fireEye = new FireEye(this);
        fireEye.add(form.byId(R.id.form_field_1), StaticPattern.Required.setMessage("请填写您的手机号"), StaticPattern.Mobile);
        fireEye.add(form.byId(R.id.form_field_2), StaticPattern.BankCard);
//
        fireEye.add(form.byId(R.id.form_field_3), StaticPattern.Required, StaticPattern.Digits.setMessage("数字专用"));
        //fireEye.add(form.byId(R.id.form_field_3), ValuePattern.MaxLength.setValue(20));
//
        fireEye.add(form.byId(R.id.form_field_4), StaticPattern.Required, StaticPattern.Email);
        fireEye.add(form.byId(R.id.form_field_5), ValuePattern.Required.setMessage("请再输入一次"), ValuePattern.EqualsTo.lazy(new TextViewLoader(form.byId(R.id.form_field_4))));
        fireEye.add(form.byId(R.id.form_field_6), StaticPattern.Host);
        fireEye.add(form.byId(R.id.form_field_7), StaticPattern.URL);
        fireEye.add(form.byId(R.id.form_field_8), ValuePattern.MaxLength.setValue(5));
        fireEye.add(form.byId(R.id.form_field_9), ValuePattern.MinLength.setValue(4));
        fireEye.add(form.byId(R.id.form_field_10), ValuePattern.RangeLength.setFirstValue(4L).setSecondValue(8L));
        fireEye.add(form.byId(R.id.form_field_11), StaticPattern.NotBlank);
        fireEye.add(form.byId(R.id.form_field_12), StaticPattern.Numeric);
        fireEye.add(form.byId(R.id.form_field_13), ValuePattern.MaxValue.setValue(100));
        fireEye.add(form.byId(R.id.form_field_14), ValuePattern.MinValue.setValue(20));
        fireEye.add(form.byId(R.id.form_field_15), ValuePattern.RangeValue.setFirstValue(18L).setSecondValue(30L));
        fireEye.add(form.byId(R.id.form_field_16), StaticPattern.VIN);

        FireEyeEnv.setDebug(true);
        FireEyeEnv.setVerbose(true);

        final Button formCommit = (Button) findViewById(R.id.form_commit);
        formCommit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InlinedApi")
			@Override
            public void onClick(View v) {
                fireEye.dump();
                int color = fireEye.test(true).passed ?
                        android.R.color.holo_green_dark : android.R.color.holo_red_dark;
                formCommit.setTextColor(getResources().getColor(color));
                formCommit.setTag(color);

            }
        });
    }
}

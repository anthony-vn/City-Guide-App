package com.example.cityguideapp.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguideapp.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Signup3ndClass extends AppCompatActivity {
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup3nd_class);

        //Hooks
        scrollView = findViewById(R.id.signup_3rd_screen_scroll_view);
        phoneNumber = findViewById(R.id.signup_phone_number);
        countryCodePicker = findViewById(R.id.country_code_picker);
    }

    public void callVerifyOTPScreen(View view) {

        //Validate fields
        if (!validatePhoneNumber()) {
            Toast.makeText(Signup3ndClass.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
            return;
        }//Validation success and now move to next screen to verify phone number and save data
        else {


            //Get all values passed from previous screens using Intent
            String _fullname = getIntent().getStringExtra("fullname");
            String _emai = getIntent().getStringExtra("email");
            String _username = getIntent().getStringExtra("username");
            String _password = getIntent().getStringExtra("password");
            String _date = getIntent().getStringExtra("date");
            String _gender = getIntent().getStringExtra("gender");

            String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();//Get phonNumber

            if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
                _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
            }

            String _phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;

            Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);


            //Passed and field to next activity
            intent.putExtra("fullname", _fullname);
            intent.putExtra("email", _emai);
            intent.putExtra("username", _username);
            intent.putExtra("password", _password);
            intent.putExtra("date", _date);
            intent.putExtra("gender", _gender);
            intent.putExtra("phoneNo", _phoneNo);
            intent.putExtra("whatToDO", "createNewUser");// Điều này là để xác định rằng OTP sẽ thực hiện hành động nào sau khi xác minh.

            //Add Transition
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup3ndClass.this, pairs);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    getIntent().getStringExtra("phoneNo"),
                    60,
                    TimeUnit.SECONDS,
                    Signup3ndClass.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(Signup3ndClass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            Toast.makeText(Signup3ndClass.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            phoneNumber.setError("Field can't be empty!");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}
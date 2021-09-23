package com.testaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etTanggal, etFullname, etEmail, etPassword, etComPassword;
    RadioButton radio;
    CheckBox cAgree;
    Button submit;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etTanggal     = findViewById(R.id.ettanggal);
        etFullname    = findViewById(R.id.etfullname);
        etEmail       = findViewById(R.id.etemail);
        etPassword    = findViewById(R.id.etpassword);
        etComPassword = findViewById(R.id.etcompassword);
        radio     = findViewById(R.id.radio1);
        cAgree        = findViewById(R.id.caggre);
        submit        = findViewById(R.id.btn_sub);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(createRequest());
            }
        });
    }

    private void showDateDialog() {
        Calendar calender = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
               Calendar newDate = Calendar.getInstance();
               newDate.set(year, month, dayOfMonth);
               etTanggal.setText(dateFormat.format(newDate.getTime()));
            }
        }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setFullname(etFullname.getText().toString());
        userRequest.setEmail(etEmail.getText().toString());
        userRequest.setPassword(etPassword.getText().toString());
        userRequest.setConfrimpassword(etComPassword.getText().toString());
        userRequest.setRadio(radio.getText().toString());
        userRequest.setDob(etTanggal.getText().toString());

        return userRequest;
    }

    public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Selesai",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Password tidak valid",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Password tidak valid"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
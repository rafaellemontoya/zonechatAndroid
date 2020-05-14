package com.myt.zonechat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myt.zonechat.MainActivity;
import com.myt.zonechat.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailET, passwordET;
    Button loginBTN;
    String TAG = "ERRORMYT";
    RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loading = findViewById(R.id.loadingPanel);

        emailET = findViewById(R.id.et_email);
        passwordET = findViewById(R.id.et_password);
        loginBTN = findViewById(R.id.btn_login);

        //reviso si hay user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            Intent intent = new Intent(getApplicationContext(), Admin.class);

            startActivity(intent);

        }else {

        }
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                if(emailET.getText().toString().isEmpty()&&passwordET.getText().toString().isEmpty()){
                    loading.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Enter your email and password",
                            Toast.LENGTH_SHORT).show();
                }else {
                    loginFB(emailET.getText().toString(),passwordET.getText().toString());
                }
            }
        });
    }
    public void loginFB(final String email, final String password){
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
                            loading.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), Admin.class);

                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            loading.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }


                    }
                });
        // [END sign_in_with_email]

    }
}

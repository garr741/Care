package tgprojects.xyz.care.Activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tgprojects.xyz.care.R;
import tgprojects.xyz.care.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    ActivityCreateBinding binding;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    private static final String TAG = "CreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        binding.linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        binding.btnSignup.setEnabled(false);

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        String name = binding.inputName.getText().toString();
        String email = binding.inputEmail.getText().toString();
        String address = binding.inputAddress.getText().toString();
        String dob = binding.inputDob.getText().toString();
        String phoneNum = binding.inputPhoneNumber.getText().toString();
        String password = binding.inputPassword.getText().toString();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            onSignupSuccess();

                            Toast.makeText(CreateActivity.this, "Successfully registered, auto login...", Toast.LENGTH_LONG).show();
                        } else {
                            //display some message here
                            String errorString = task.getException().toString();
                            String trunctederrorstring = errorString.substring(errorString.indexOf(":"));
                            Toast.makeText(CreateActivity.this, "Registration Error "+trunctederrorstring, Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }


    public void onSignupSuccess() {
        binding.btnSignup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        binding.btnSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name =  binding.inputName.getText().toString();
        String email =  binding.inputEmail.getText().toString();
        String password =  binding.inputPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            binding.inputName.setError("at least 3 characters");
            valid = false;
        } else {
            binding.inputName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            binding.inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            binding.inputPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            binding.inputPassword.setError(null);
        }

        return valid;
    }
}

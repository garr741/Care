package tgprojects.xyz.care.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tgprojects.xyz.care.DTO.User;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private DatabaseReference userReference;
    private User user;


    ActivityProfileBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userReference = firebaseDatabase.getReference("Users");

        // Use Current User Name As Toolbar
        userReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    user = dataSnapshot.getValue(User.class);

                    if (getSupportActionBar() != null) {
                        if (user != null && user.getName() != null) {
                            getSupportActionBar().setTitle("Welcome " + user.getName());
                        } else {
                            getSupportActionBar().setTitle("Welcome " + firebaseUser.getDisplayName());
                        }
                        getSupportActionBar().setHomeButtonEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
                    setupFields();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
//            case R.id.action_edit:
//                Intent edit = new Intent(UserProfileActivity.this, EditProfileActivity.class);
//                startActivity(edit);
//                finish();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupFields() {
        binding.inputName.setText(user.getName());
        binding.inputAddress.setText(user.getAddress());
        binding.inputEmail.setText(user.getEmail());
        binding.inputDob.setText(user.getDob());
        binding.inputPhoneNumber.setText(user.getPhoneNum());
    }
}

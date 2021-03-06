package tgprojects.xyz.care.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import io.paperdb.Paper;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import tgprojects.xyz.care.DTO.User;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.adapters.ImportantInfoAdapter;
import tgprojects.xyz.care.databinding.ActivityHomeBinding;
import tgprojects.xyz.care.models.ImportantInfo;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private DatabaseReference userReference;
    private User user;

    public static final int CAMERA_REQUEST = 1009;

    private StorageReference storageRef;
    private ImportantInfoAdapter adapter;
    private List<ImportantInfo> importantInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
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

                    if (user != null && user.getName() != null) {
                        binding.introText.setText("Hello " + user.getName().split(" ")[0]);
                    } else {
                        binding.introText.setText("Hello " + firebaseUser.getDisplayName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (importantInfos== null) {
            importantInfos = new ArrayList<>();
        }
        storageRef = FirebaseStorage.getInstance().getReference();
        binding.importantInfoRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ImportantInfoAdapter(this, importantInfos);
        binding.importantInfoRecyclerview.setAdapter(adapter);
        setupFAB();
    }

    @Override protected void onResume() {
        super.onResume();
        binding.introText.requestFocus();
    }

    private void setupFAB() {
        final Intent intent = new Intent(this, ResourceActivity.class);
        final String extraName = "resource";
        binding.fabHousing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView) view).getText());
                startActivity(intent);
            }
        });

        binding.fabEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView) view).getText());
                startActivity(intent);
            }
        });

        binding.fabEmployment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView) view).getText());
                startActivity(intent);
            }
        });

        binding.fabMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView) view).getText());
                startActivity(intent);

            }
        });
        binding.addDoc.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(imageIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            uploadBitmap(photo);
            adapter.addInfo(new ImportantInfo("Birth Certificate", "", photo));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.menu_item_onSignout:
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
            case R.id.messages:
                startActivity(new Intent(this, MessagesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getFileName() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    private void uploadBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageRef.child("image" + getFileName()).putBytes(data);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
            }
        });
    }
}

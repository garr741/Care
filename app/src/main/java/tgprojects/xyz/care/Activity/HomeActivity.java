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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tgprojects.xyz.care.R;
import tgprojects.xyz.care.adapters.ImportantInfoAdapter;
import tgprojects.xyz.care.databinding.ActivityHomeBinding;
import tgprojects.xyz.care.models.ImportantInfo;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    public static final int CAMERA_REQUEST = 1009;

    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        storageRef = FirebaseStorage.getInstance().getReference();
        binding.importantInfoRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.importantInfoRecyclerview.setAdapter(new ImportantInfoAdapter(this, mockData()));
        setupFAB();
    }


    private List<ImportantInfo> mockData() {
        ImportantInfo importantInfo1 = new ImportantInfo("One", "http://via.placeholder.com/1500x1000");
        ImportantInfo importantInfo2 = new ImportantInfo("Two", "http://via.placeholder.com/1500x1000");
        ArrayList<ImportantInfo> arrayList = new ArrayList<>();
        arrayList.add(importantInfo1);
        arrayList.add(importantInfo2);
        return arrayList;
    }

    private void setupFAB() {
        final Intent intent = new Intent(this, ResourceActivity.class);
        final String extraName = "resource";
        binding.fabHousing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView)view).getText());
                startActivity(intent);
            }
        });

        binding.fabEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView)view).getText());
                startActivity(intent);
            }
        });

        binding.fabEmployment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(extraName, ((TextView)view).getText());
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
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            uploadBitmap(photo);
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
            @Override public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
            }
        });
    }
}

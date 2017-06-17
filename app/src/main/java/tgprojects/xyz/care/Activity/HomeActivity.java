package tgprojects.xyz.care.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import tgprojects.xyz.care.adapters.ImportantInfoAdapter;
import tgprojects.xyz.care.models.ImportantInfo;

import tgprojects.xyz.care.R;

public class HomeActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 1009;

    private StorageReference storageRef;

    @BindView(R.id.important_info_recyclerview) RecyclerView importantInfoRecyclerView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        storageRef = FirebaseStorage.getInstance().getReference();
        importantInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        importantInfoRecyclerView.setAdapter(new ImportantInfoAdapter(this, mockData()));
    }


    private List<ImportantInfo> mockData() {
        ImportantInfo importantInfo1 = new ImportantInfo("One", "http://via.placeholder.com/1500x1000");
        ImportantInfo importantInfo2 = new ImportantInfo("Two", "http://via.placeholder.com/1500x1000");
        ArrayList<ImportantInfo> arrayList = new ArrayList<>();
        arrayList.add(importantInfo1);
        arrayList.add(importantInfo2);
        return arrayList;
    }

    @OnClick(R.id.add_doc) void onFabClicked() {
        Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(imageIntent, CAMERA_REQUEST);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            uploadBitmap(photo);
        }
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

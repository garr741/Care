package tgprojects.xyz.care.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tgprojects.xyz.care.DTO.Resource;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.adapters.ResourcesAdapter;
import tgprojects.xyz.care.databinding.ActivityResourceBinding;

public class ResourceActivity extends AppCompatActivity {

    private String resourceTopic;

    private ActivityResourceBinding binding;

    private DatabaseReference resourcesReference;

    private List<Resource> resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resource);

        resourceTopic = getIntent().getStringExtra("resource");

        resourcesReference =  FirebaseDatabase.getInstance().getReference("Resources");

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(resourceTopic);

        getResourcesList();
//        mockResources();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getResourcesList() {
        resourcesReference.orderByChild("subject").equalTo(resourceTopic).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Resource resource;
                resources = new ArrayList<Resource>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    resource = snapshot.getValue(Resource.class);
                    resources.add(resource);
                }
                createResourceList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void mockResources() {
        List<Resource> resourceList = new ArrayList<Resource>();
        resourcesReference.push().setValue(new Resource("https://www.ssa.gov/", "Education", "Social Security Application", "You can apply to get a social security card with this link"));
        resourcesReference.push().setValue(new Resource("https://www.ssa.gov/", "Education", "Social Security Application", "You can apply to get a social security card with this link"));
        resourcesReference.push().setValue(new Resource("https://vinelink.com/#/home", "Education", "VineLink","Track information about criminal cases and the custody status of offenders"));
        resourcesReference.push().setValue(new Resource("http://www.in.gov/dcs/3000.htm","Education", "Thinking About College?","http://www.in.gov/dcs/3872.htm"));
        ResourcesAdapter adapter = new ResourcesAdapter(resourceList,this);
        binding.listResource.setAdapter(adapter);
    }

    private void createResourceList() {
        ResourcesAdapter adapter = new ResourcesAdapter(resources,this);
        binding.listResource.setAdapter(adapter);
    }

}

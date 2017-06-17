package tgprojects.xyz.care.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import tgprojects.xyz.care.DTO.Resource;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.adapters.ResourcesAdapter;
import tgprojects.xyz.care.databinding.ActivityResourceBinding;

public class ResourceActivity extends AppCompatActivity {

    private String resourceTopic;

    private ActivityResourceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resource);

        resourceTopic = getIntent().getStringExtra("resource");

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(resourceTopic);

        mockResources();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void mockResources() {
        List<Resource> resourceList = new ArrayList<Resource>();
        resourceList.add(new Resource("https://www.ssa.gov/", "Social Security Application", "You can apply to get a social security card with this link"));
        resourceList.add((new Resource("https://vinelink.com/#/home","VineLink","Track information about criminal cases and the custody status of offenders")));
        resourceList.add((new Resource("http://www.in.gov/dcs/3000.htm","Thinking About College?","http://www.in.gov/dcs/3872.htm")));
        ResourcesAdapter adapter = new ResourcesAdapter(resourceList,this);
        binding.listResource.setAdapter(adapter);
    }

}

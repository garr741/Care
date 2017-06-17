package tgprojects.xyz.care.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.List;

import tgprojects.xyz.care.adapters.ImportantInfoAdapter;
import tgprojects.xyz.care.databinding.ActivityHomeBinding;
import tgprojects.xyz.care.models.ImportantInfo;

import tgprojects.xyz.care.R;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.importantInfoRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.importantInfoRecyclerview.setAdapter(new ImportantInfoAdapter(this, mockData()));
        setupFAB();
    }


    private List<ImportantInfo> mockData() {
        ImportantInfo importantInfo1 = new ImportantInfo("One", "http://via.placeholder.com/3500x1500");
        ImportantInfo importantInfo2 = new ImportantInfo("Two", "http://via.placeholder.com/3500x1500");
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
                intent.putExtra(extraName, ((TextView)view).getText());
                startActivity(intent);
            }
        });
    }
}

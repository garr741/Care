package tgprojects.xyz.care.Activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import tgprojects.xyz.care.adapters.ImportantInfoAdapter;
import tgprojects.xyz.care.models.ImportantInfo;

import tgprojects.xyz.care.R;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.important_info_recyclerview) RecyclerView importantInfoRecyclerView;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        importantInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        importantInfoRecyclerView.setAdapter(new ImportantInfoAdapter(this, mockData()));
    }


    private List<ImportantInfo> mockData() {
        ImportantInfo importantInfo1 = new ImportantInfo("One", "http://via.placeholder.com/3500x1500");
        ImportantInfo importantInfo2 = new ImportantInfo("Two", "http://via.placeholder.com/3500x1500");
        ArrayList<ImportantInfo> arrayList = new ArrayList<>();
        arrayList.add(importantInfo1);
        arrayList.add(importantInfo2);
        return arrayList;
    }
}

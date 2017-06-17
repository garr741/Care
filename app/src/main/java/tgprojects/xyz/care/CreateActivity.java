package tgprojects.xyz.care;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tgprojects.xyz.care.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    ActivityCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create);
    }
}

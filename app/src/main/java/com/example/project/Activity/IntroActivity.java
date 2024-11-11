package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.introBtn.setOnClickListener(view -> {
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            finish();
        });
    }
}

package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.project.Domain.ItemDomain;
import com.example.project.R;
import com.example.project.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private ItemDomain object;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        binding.titletxt.setText(object.getTitle());
        binding.pricetxt.setText("₹"+object.getPrice());
        binding.bedtxt.setText(""+object.getBed());
        binding.durationtxt.setText(object.getDistance());
        binding.descriptiontxt.setText(object.getDescription());
        binding.addresstxt.setText(object.getAddress());
        binding.ratingtxt.setText(object.getScore()+"");
        binding.ratingBar.setRating((float) object.getScore());
        binding.backbtn.setOnClickListener(view -> finish());


        Glide.with(DetailActivity.this).load(object.getPic()).into(binding.pic);

        binding.AddtoCartbtn.setOnClickListener(view -> {
            Intent intent = new Intent(DetailActivity.this,TicketActivity.class);
            intent.putExtra("object",object);
            startActivity(intent);
        });


    }

    private void getIntentExtra() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");
    }
}
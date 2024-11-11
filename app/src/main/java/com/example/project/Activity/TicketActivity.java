package com.example.project.Activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project.Domain.ItemDomain;
import com.example.project.databinding.ActivityTicketBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TicketActivity extends AppCompatActivity {
    ActivityTicketBinding binding;
    private ItemDomain object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();


        binding.button.setOnClickListener(v -> captureScreenAndDownloadAsPDF());
    }

    private void setVariable() {
        Glide.with(TicketActivity.this).load(object.getPic()).into(binding.pic);
        Glide.with(TicketActivity.this).load(object.getTourGuidePic()).into(binding.profile);

        binding.backBtn.setOnClickListener(view -> finish());

        binding.titletxt.setText(object.getTitle());
        binding.durationtxt.setText(object.getDuration());
        binding.tourGuidetxt.setText(object.getDateTour());
        binding.timetxt.setText(object.getTimeTour());
        binding.TourGuidetxt.setText(object.getTourGuideName());

        binding.messagebtn.setOnClickListener(view -> {
            // Handle sending message
        });

        binding.callbtn.setOnClickListener(view -> {
            // Handle calling
        });
    }

    private void getIntentExtra() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");
    }

    private void captureScreenAndDownloadAsPDF() {

        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);


        PdfDocument pdfDocument = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);


        page.getCanvas().drawBitmap(bitmap, 0, 0, null);
        pdfDocument.finishPage(page);

        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File pdfFile = new File(downloadsDir, "Ticket_Snapshot.pdf");

        try (FileOutputStream outputStream = new FileOutputStream(pdfFile)) {
            pdfDocument.writeTo(outputStream);
            Toast.makeText(this, "PDF saved to Downloads: " + pdfFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to create PDF", Toast.LENGTH_SHORT).show();
        } finally {
            pdfDocument.close();
            bitmap.recycle();
        }
    }
}

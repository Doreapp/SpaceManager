package com.mandin.antoine.spacemanager.v2;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mandin.antoine.spacemanager.R;
import com.mandin.antoine.spacemanager.v2.model.DirectoryElement;
import com.mandin.antoine.spacemanager.v2.model.Element;

public class Main2Activity extends AppCompatActivity {
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView tvInfo = findViewById(R.id.tv_info);
        info = "";

        new BrowsingTask().setProgressHandler(new BrowsingTask.ProgressHandler() {
            @Override
            public void onProgress(BrowsingTask.ProgressValue... progressValues) {
                tvInfo.setText("Progress : " + progressValues[0].getScannedFiles() + " files scanned\n" + info);

            }
        }).execute(Environment.getExternalStorageDirectory().getPath());

        //TODO check permissions ...
    }
}

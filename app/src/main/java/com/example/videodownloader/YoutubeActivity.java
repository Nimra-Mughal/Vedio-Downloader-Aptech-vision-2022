package com.example.videodownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class YoutubeActivity extends AppCompatActivity {

    EditText URl;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        URl = findViewById(R.id.input_ytubeoURL);
        Btn = findViewById(R.id.btn_downloadytubeVideo);

//        Btn.setOnClickListener(v -> ytvideos());
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(URl.getText().toString())){
                    Toast.makeText(YoutubeActivity.this, "Please provide url", Toast.LENGTH_SHORT).show();
                }else{
                    new YouTubeExtractor(YoutubeActivity.this) {
                        @Override
                        public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                            if (ytFiles != null) {
                                int itag = 22;
                                String downloadUrl = ytFiles.get(itag).getUrl();
                                Util.download(downloadUrl,Util.RootDirectoryYoutube,
                                        YoutubeActivity.this,
                                        "youtube"+System.currentTimeMillis()+".mp4");
                            }
                        }
                    }.extract(URl.getText().toString(),true,true);
                }

            }
        });
    }

    private void ytvideos() {


    }
}
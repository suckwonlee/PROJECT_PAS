package kr.ac.kopo.project_pas.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.ac.kopo.project_pas.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View touchArea = findViewById(R.id.main);
        touchArea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CharacterSelectionActivity.class); // ✅ 이게 맞음

            startActivity(intent);
        });
    }
}
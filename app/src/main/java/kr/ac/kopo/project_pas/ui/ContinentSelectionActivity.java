package kr.ac.kopo.project_pas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.kopo.project_pas.R;

public class ContinentSelectionActivity extends AppCompatActivity {

    private String[] continentNames = {"남대륙", "동대륙", "서대륙", "북대륙", "중앙대륙"};
    private String[] continentDescriptions = {
            "5대 대륙 중 가장 작아 '대륙'이라 부르기엔 부족하고, '섬'이라 하기엔 큰 땅. 해안선을 따라 거대한 산맥이 이어져 있고, 북쪽의 거대한 항구를 중심으로 해상 무역이 번성한다. 허나 이 평화롭지만 진입하기 어려운 환경이 마계의 악마 숭배자들이 몰려드는 것을 막아야 했던 파견군의 작전을 방해했고, 결국 이계 침략의 시초가 되었다.",
            "현재 개발 중인 대륙입니다.",
            "현재 개발 중인 대륙입니다.",
            "현재 개발 중인 대륙입니다.",
            "현재 개발 중인 대륙입니다."
    };

    // 이미지 리소스: 남대륙만 적용
    private int[] continentImageRes = {
            R.drawable.south,
            // R.drawable.east,
            // R.drawable.west,
            // R.drawable.north,
            // R.drawable.center
    };

    private int currentIndex = 0;

    private TextView tvContinentName;
    private TextView tvDescription;
    private ImageView ivContinentImage;
    private ImageButton btnLeft;
    private ImageButton btnRight;
    private Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent_selection);

        tvContinentName = findViewById(R.id.continent_name);
        tvDescription = findViewById(R.id.description_text);
        ivContinentImage = findViewById(R.id.continent_image);
        btnLeft = findViewById(R.id.arrow_left);
        btnRight = findViewById(R.id.arrow_right);
        btnNext = findViewById(R.id.btn_next_center);

        updateUI();

        btnLeft.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + continentNames.length) % continentNames.length;
            updateUI();
        });

        btnRight.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % continentNames.length;
            updateUI();
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex != 0) {
                tvDescription.setText("해당 대륙은 아직 개발 중입니다.");
                return;
            }

            Intent intent = new Intent(ContinentSelectionActivity.this, CharacterSelectionActivity.class);
            intent.putExtra("selectedContinent", continentNames[currentIndex]);
            startActivity(intent);
        });
    }

    private void updateUI() {
        tvContinentName.setText(continentNames[currentIndex]);
        tvDescription.setText(continentDescriptions[currentIndex]);

        if (currentIndex == 0) {
            ivContinentImage.setImageResource(R.drawable.south);
        } else {
            ivContinentImage.setImageDrawable(null);
        }
    }
}

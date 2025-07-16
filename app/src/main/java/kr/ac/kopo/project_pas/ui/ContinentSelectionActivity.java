package kr.ac.kopo.project_pas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import kr.ac.kopo.project_pas.R;

public class ContinentSelectionActivity extends BaseActivity {

    private String[] continentNames = {"남대륙", "동대륙", "서대륙", "북대륙", "중앙대륙"};
    private String[] continentDescriptions = {
            "5대 대륙 중 가장 작아 '대륙'이라 부르기엔 부족하고, '섬'이라 하기엔 큰 땅. 해안선을 따라 거대한 산맥이 이어져 있고, 북쪽의 거대한 항구를 중심으로 해상 무역이 번성한다. 허나 이 평화롭지만 진입하기 어려운 환경이 마계의 악마 숭배자들이 몰려드는 것을 막아야 했던 파견군의 작전을 방해했고, 결국 이계 침략의 시초가 되었다.",
            "험한 산악과 긴 겨울이 이어지는 혹한의 땅. 이곳은 다른 대륙과의 교류 없이, 강인한 투사들의 수호 아래 짐승과 괴물들과 투쟁하며 살아온 대륙이다. 그러나 적은 인구수는 치명적인 약점이 되었고, 명계의 침략으로 동대륙의 시체들이 되살아나기 시작하면서 이 대륙 또한 멸종의 위기에 놓이게 되었다.",
            "문명의 손길이 닿지 않은, 전통과 야만이 공존하는 대륙. 투박하지만 따뜻한 야만전사들과 주술사들이 대대로 지켜온 삶의 방식은, 어느 날부터인가 정체 모를 존재들의 출현으로 균열을 일으켰다. 이해할 수 없는 것들이 일상이 되고, 혼돈계의 시선이 이들을 덮친 순간부터—그들의 육신은 변이를 시작했고, 전통과 평화는 조용히, 그러나 확실히 끝을 향해 나아가고 있다.",
            "드루이드들이 수호하는 정글로 뒤덮인 대륙. 지나치게 울창한 숲은 지상에서의 삶을 거의 불가능하게 만들었고, 드루이드의 축복 아래 나무 위에서 살아가는 방식이 자연스럽게 정착되었다. 그러나 이계의 침략이 시작되며 퍼져나간 ‘심연’의 기운에 접촉한 이후, 정글은 점차 본모습을 잃어갔다. 초목은 뒤틀리고, 숲의 존재들—드루이드들, 그리고 오랫동안 잠들어 있던 야생신들마저—그 침식에서 벗어나지 못하고 있다는 소문이 들려오고 있다.",
            "강대한 제국이 지배하는 비옥한 중심지. 이계의 침공 당시, 이 땅 역시 침략의 대상이 되었으나 제국은 놀라울 만큼 빠르고 효율적으로 그 위협을 막아냈다. 그러나 그 승리는 오래지 않아 또 다른 침략의 서막이 되었다. 제국은 전쟁에서 얻은 전리품을 기반으로 다른 대륙들을 정복했고, 생존자들을 제물로 삼아 이계의 힘을 다시 끌어들이는 실험을 이어가고 있다. 그리고 그들은 지금, 자신들이 처단했던 이계 너머로 스스로 발을 들이려 한다."
    };

    private int[] continentImageRes = {
            R.drawable.south,
            R.drawable.east,
            R.drawable.west,
            R.drawable.north,
            R.drawable.central
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

        btnLeft.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + continentNames.length) % continentNames.length;
            updateUI();
        });

        btnRight.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % continentNames.length;
            updateUI();
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(this, CharacterSelectionActivity.class);
            intent.putExtra("continentId", continentNames[currentIndex]);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        updateUI();
    }

    private void updateUI() {
        tvContinentName.setText(continentNames[currentIndex]);
        tvDescription.setText(continentDescriptions[currentIndex]);
        ivContinentImage.setImageResource(continentImageRes[currentIndex]);
    }
}

package kr.ac.kopo.project_pas.shop;

import kr.ac.kopo.project_pas.R;

public class PotionData {

    public static class Potion {
        public final int grade;
        public final String name;
        public final int healAmount;
        public final int price;
        public final String description;
        public final int iconResId;

        public Potion(int grade, String name, int healAmount, int price, String description, int iconResId) {
            this.grade = grade;
            this.name = name;
            this.healAmount = healAmount;
            this.price = price;
            this.description = description;
            this.iconResId = iconResId;
        }
    }

    public static Potion getPotionByChapter(int chapter) {
        switch (chapter) {
            case 1:
                return new Potion(1, "치유 물약", 50, 20, "값싸지만 효과적인 치유 물약. 정규병들은 하나씩은 보급받아 들고 다니는게 의무라고 한다. 맛이 끔찍한게 문제.", R.drawable.potion_1);
            case 2:
                return new Potion(2, "고급 치유 물약", 80, 30, "양산 가능한 마지노선의 물약. 한때 치유 물약의 자리를 완전히 차지할 거라 기대받았으나 재료비 문제로 공존하고 있다.", R.drawable.potion_2);
            case 3:
                return new Potion(3, "잔불의 생명수", 100, 40, "이계의 침략으로 세상은 피폐해졌으나 그 에너지는 연금술을 몇 단계는 진보시켜냈다. 그 가장 앞선 면이 치유 물약이란 사실은 수많은 연금술사들에게 충격을 주었다.", R.drawable.potion_3);
            case 4:
                return new Potion(4, "세계수의 진액", 150, 50, "이계의 침략이 심화되어가고 세상이 피폐해지자 세계수가 눈물처럼 흘린 수액을 희석한 물. 잘린 팔을 재생시킬 정도로 뛰어난 재생력을 선보였지만, 회복력이 과해 부작용이 발생하자 희석하여 사용된다.", R.drawable.potion_4);
            case 5:
                return new Potion(5, "엘릭시르", 200, 50, "죽은 사람도 살려냈다는 신의 피라고 알려진 액체. 이계의 침공으로 천상과 물질계의 연결도 강해지며 물질계에 나타나기 시작했다.", R.drawable.potion_5);
            default:
                return getPotionByChapter(1);
        }
    }
}

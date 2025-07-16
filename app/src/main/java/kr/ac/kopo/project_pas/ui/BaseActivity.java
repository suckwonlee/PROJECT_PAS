package kr.ac.kopo.project_pas.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private AlertDialog exitDialog;

    @Override
    public void onBackPressed() {
        // 뒤로(취소) 버튼 눌렀을 때 종료 확인 다이얼로그 표시
        super.onBackPressed();
        if (exitDialog != null && exitDialog.isShowing()) {
            exitDialog.dismiss();
        } else {
            showExitDialog();
        }
    }

    private void showExitDialog() {
        exitDialog = new AlertDialog.Builder(this)
                .setMessage("게임을 종료하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> finish())
                .setNegativeButton("아니요", (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .create();
        exitDialog.show();
    }
}

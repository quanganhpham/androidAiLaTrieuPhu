package com.quanganh95.pham.gameailatrieuphu.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quanganh95.pham.gameailatrieuphu.R;
import com.quanganh95.pham.gameailatrieuphu.view.activity.ScreenStartActivity;
import com.quanganh95.pham.gameailatrieuphu.view.fragment.PlayGameFragment;

public class DialogReadyGame extends Dialog {
    private static final String TAG = "DialogReadyGame";
    private Context context;
    private TextView txtCachChoi;
    private Button btnPlayGame;
    private Button btnCancelGame;

    public DialogReadyGame(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ready_game);

        txtCachChoi = findViewById(R.id.txtCachChoi);
        btnPlayGame = findViewById(R.id.btnPlayGame);
        btnCancelGame = findViewById(R.id.btnCancelGame);

        btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bắt đầu chơi game
                playGame();
                dismiss();
            }
        });

        btnCancelGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Typeface face = Typeface.createFromAsset(context.getAssets(), "font/Nabila.ttf");
        txtCachChoi.setTypeface(face);
    }

    private void playGame() {
        ((ScreenStartActivity) context).openFragment(PlayGameFragment.class.getName());
        ((ScreenStartActivity) context).setIsEndGame(2);
    }

    @Override
    protected void onStart() {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        super.onStart();
    }
}

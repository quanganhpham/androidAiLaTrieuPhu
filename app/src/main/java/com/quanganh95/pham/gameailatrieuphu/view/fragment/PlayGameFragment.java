package com.quanganh95.pham.gameailatrieuphu.view.fragment;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quanganh95.pham.gameailatrieuphu.R;
import com.quanganh95.pham.gameailatrieuphu.constant.MessageContant;
import com.quanganh95.pham.gameailatrieuphu.view.activity.ScreenStartActivity;

public class PlayGameFragment extends Fragment implements View.OnClickListener {
    private TextView txtCauHoi;
    private TextView txtTimeQuestion;
    private Button[] btnDapAn;
    private ImageView[] imgCheck;
    private View rootView;
    private Typeface tfDefault;

    private static final int BUTTON_1 = 0;
    private static final int BUTTON_2 = 1;
    private static final int BUTTON_3 = 2;
    private static final int BUTTON_4 = 3;

    private Handler handler;

    // Phần người chơi
    private boolean isLost; // mặc định ban đầu là false : tức là người dùng chưa thua, nếu như thua thì sẽ là true

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_play_game, container, false);
        return rootView;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO
        initializeComponents(view);
        tfDefault = btnDapAn[0].getTypeface();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MessageContant.TIMING:
                        int number = msg.arg1;
                        txtTimeQuestion.setText(number + "");
                        break;
                    case MessageContant.TIME_OUT:
                        txtTimeQuestion.setText(0 + "");
                        Toast.makeText(getActivity(), "Đã hết giờ!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        startTiming();
    }

    private void startTiming() {
        // s chạy từ 9 về 0
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int number = 9;
                for (int i = 9; i >= 1; i--) {
                    Message message = new Message();
                    message.what = MessageContant.TIMING;
                    message.arg1 = number;
                    handler.sendMessage(message);
                    number--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(MessageContant.TIME_OUT);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void initializeComponents(View view) {
        txtCauHoi = view.findViewById(R.id.txtCauHoi);
        txtTimeQuestion = view.findViewById(R.id.txtTimeCount);
        btnDapAn = new Button[]{
                view.findViewById(R.id.btnCauTraLoi1),
                view.findViewById(R.id.btnCauTraLoi2),
                view.findViewById(R.id.btnCauTraLoi3),
                view.findViewById(R.id.btnCauTraLoi4)
        };
        imgCheck = new ImageView[]{
                view.findViewById(R.id.imgCheck1),
                view.findViewById(R.id.imgCheck2),
                view.findViewById(R.id.imgCheck3),
                view.findViewById(R.id.imgCheck4)
        };
        for (int i = 0; i < btnDapAn.length; i++) {
            btnDapAn[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        resetDefaultButton();
        switch (view.getId()) {

            case R.id.btnCauTraLoi1:
                luaChonDapAn(BUTTON_1);
                break;

            case R.id.btnCauTraLoi2:
                luaChonDapAn(BUTTON_2);
                break;

            case R.id.btnCauTraLoi3:
                luaChonDapAn(BUTTON_3);
                break;

            case R.id.btnCauTraLoi4:
                luaChonDapAn(BUTTON_4);
                break;

        }
    }

    private void resetDefaultButton() {
        for (int i = 0; i < btnDapAn.length; i++) {
            btnDapAn[i].setTextColor(getResources().getColor(R.color.colorResetTextButton));
            btnDapAn[i].setTypeface(tfDefault);
            imgCheck[i].setVisibility(View.GONE);
        }
    }

    private void luaChonDapAn(int button) {
        btnDapAn[button].setTextColor(getResources().getColor(R.color.colorButtonCheck));
        btnDapAn[button].setTypeface(btnDapAn[button].getTypeface(), Typeface.BOLD);
        imgCheck[button].setVisibility(View.VISIBLE);

    }

}

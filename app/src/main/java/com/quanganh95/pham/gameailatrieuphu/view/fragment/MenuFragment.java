package com.quanganh95.pham.gameailatrieuphu.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quanganh95.pham.gameailatrieuphu.R;
import com.quanganh95.pham.gameailatrieuphu.view.activity.ScreenStartActivity;
import com.quanganh95.pham.gameailatrieuphu.view.dialog.DialogReadyGame;

public class MenuFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MenuFragment";
    private View rootView;


    private Button[] btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO
        btn = new Button[]{
                view.findViewById(R.id.btnBatDau),
                view.findViewById(R.id.btnDiemCao),
                view.findViewById(R.id.btnBinhChon),
                view.findViewById(R.id.btnGameKhac),
                view.findViewById(R.id.btnChiaSe)
        };
        for (int i = 0; i < btn.length; i++) {
            btn[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDiemCao:
                ((ScreenStartActivity) getActivity()).openFragment(HighScoreFragment.class.getName());
                ((ScreenStartActivity) getActivity()).setIsEndGame(0);
                break;
            case R.id.btnBatDau:
                // Ban đầu cho hiện Dialog trước đã
                DialogReadyGame dialogReadyGame = new DialogReadyGame((ScreenStartActivity) getActivity());
                dialogReadyGame.show();
                break;
            default:
                break;
        }
    }

}

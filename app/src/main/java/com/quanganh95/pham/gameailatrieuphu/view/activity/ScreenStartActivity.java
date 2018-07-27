package com.quanganh95.pham.gameailatrieuphu.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.quanganh95.pham.gameailatrieuphu.R;
import com.quanganh95.pham.gameailatrieuphu.view.fragment.HighScoreFragment;
import com.quanganh95.pham.gameailatrieuphu.view.fragment.MenuFragment;
import com.quanganh95.pham.gameailatrieuphu.view.fragment.PlayGameFragment;

import java.util.List;

public class ScreenStartActivity extends AppCompatActivity {
    private static final String TAG = "ScreenStartActivity";
    private MenuFragment menuFragment;
    private HighScoreFragment highScoreFragment;
    private PlayGameFragment playGameFragment;

    private FragmentManager fm = getSupportFragmentManager();

    private int isEndGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_start);
        isEndGame = 1;
        initializeObject();
        openMenuScreen();
    }

    private void initializeObject() {
        menuFragment = new MenuFragment();
        highScoreFragment = new HighScoreFragment();
        playGameFragment = new PlayGameFragment();
    }


    public void openMenuScreen() {
        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.content, menuFragment, MenuFragment.class.getName())
                .show(menuFragment)
                .commit();
    }

    /**
     * @param tag : Fragment name
     */
    public void openFragment(String tag) {
        if (tag.equals(HighScoreFragment.class.getName().toString())) {
            addHighScore();
            return;
        }
        if (tag.equals(PlayGameFragment.class.getName().toString())) {
            addPlayGame();
            return;
        }
    }

    private void addPlayGame() {
        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.content, playGameFragment, PlayGameFragment.class.getName())
                .commit();
    }

    private void addHighScore() {
        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.content, highScoreFragment, HighScoreFragment.class.getName())
                .commit();
    }


    public int getIsEndGame() {
        return isEndGame;
    }

    public void setIsEndGame(int isEndGame) {
        this.isEndGame = isEndGame;
    }

    @Override
    public void onBackPressed() {
        if (getIsEndGame() == 0) {
            fm.beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.content, menuFragment, MenuFragment.class.getName())
                    .commit();
            setIsEndGame(1);
        } else if (getIsEndGame() == 1) {
            Toast.makeText(this, "Nhấn lần nữa để thoát khỏi Game!", Toast.LENGTH_SHORT).show();
            setIsEndGame(-1);
        } else if (getIsEndGame() == 2) {
            // Đang chơi game mà vô tình nhấn nút Back
            Toast.makeText(this, "Màn chơi được tính là bạn thua khi bạn nhấn nút thoát!", Toast.LENGTH_SHORT).show();
            setIsEndGame(0);
        } else {
            super.onBackPressed();
        }

    }
}

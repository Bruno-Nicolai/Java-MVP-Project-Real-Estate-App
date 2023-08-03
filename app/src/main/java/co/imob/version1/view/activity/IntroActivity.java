package co.imob.version1.view.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.imob.version1.R;
import co.imob.version1.view.fragment.LoginFragment;
import co.imob.version1.view.fragment.SignUpFragment;

public class IntroActivity extends AppCompatActivity {

    private TextView tab1, tab2;
    private int selectedTabNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fcv_below, LoginFragment.class, null)
                .commit();

        tab1.setOnClickListener(view -> {
            selectTab(1);
        });

        tab2.setOnClickListener(view -> {
            selectTab(2);
        });

    }

    private void selectTab(int tabNumber) {
        TextView selectedTextView, nonSelectedTextView;

        if (tabNumber == 1) {
            selectedTextView = tab1;
            nonSelectedTextView = tab2;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fcv_below, LoginFragment.class, null)
                    .commit();

        } else {
            selectedTextView = tab2;
            nonSelectedTextView = tab1;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fcv_below, SignUpFragment.class, null)
                    .commit();

        }

        float slideTo = (tabNumber - selectedTabNumber) * selectedTextView.getWidth();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, slideTo, 0, 0);
        translateAnimation.setDuration(100);
        if (selectedTabNumber == 1) {
            tab1.startAnimation(translateAnimation);
        } else if (selectedTabNumber == 2) /*{} else*/ {
            tab2.startAnimation(translateAnimation);
        }

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //SELECTED
                selectedTextView.setBackgroundResource(R.drawable.active_bg_white_round_tab);
                selectedTextView.setTypeface(null, Typeface.BOLD);
                selectedTextView.setTextColor(Color.BLACK);

                //NON SELECTED
                nonSelectedTextView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView.setTypeface(null, Typeface.NORMAL);
                nonSelectedTextView.setTextColor(Color.parseColor("#80FFFFFF"));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        selectedTabNumber = tabNumber;

    }

}

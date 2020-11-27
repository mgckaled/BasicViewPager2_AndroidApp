package com.mgckaled.viewpager2example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); // Hide toolbar

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        textView = findViewById(R.id.textView);

        setupOnboardingItens();

        ViewPager2 onboardingViewPager = findViewById(R.id.onBoardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
    }

    /* List of views /Onboarding itens -> SETUP MAIN CONTENT  */
    private void setupOnboardingItens(){

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        // FIRST ITEM
        OnboardingItem obItem_1 = new OnboardingItem();
        obItem_1.setTitle(R.string.title_obIten_1);
        obItem_1.setDescription(R.string.desc_obItem_1);
        obItem_1.setImage(R.drawable.clever);
        // SECOND ITEM
        OnboardingItem obItem_2 = new OnboardingItem();
        obItem_2.setTitle(R.string.title_obIten_2);
        obItem_2.setDescription(R.string.desc_obItem_2);
        obItem_2.setImage(R.drawable.adventure);
        // ...3
        OnboardingItem obItem_3 = new OnboardingItem();
        obItem_3.setTitle(R.string.title_obIten_3);
        obItem_3.setDescription(R.string.desc_obItem_3);
        obItem_3.setImage(R.drawable.powerful_1);
        // ...4
        OnboardingItem obItem_4 = new OnboardingItem();
        obItem_4.setTitle(R.string.title_obIten_4);
        obItem_4.setDescription(R.string.desc_obItem_4);
        obItem_4.setImage(R.drawable.friendly_1);
        // ...5
        OnboardingItem obItem_5 = new OnboardingItem();
        obItem_5.setTitle(R.string.title_obIten_5);
        obItem_5.setDescription(R.string.desc_obItem_5);
        obItem_5.setImage(R.drawable.friendly_3);
        // ...6
        OnboardingItem obItem_6 = new OnboardingItem();
        obItem_6.setTitle(R.string.title_obIten_6);
        obItem_6.setDescription(R.string.desc_obItem_6);
        obItem_6.setImage(R.drawable.funny);
        // ...7
        OnboardingItem obItem_7 = new OnboardingItem();
        obItem_7.setTitle(R.string.title_obIten_7);
        obItem_7.setDescription(R.string.desc_obItem_7);
        obItem_7.setImage(R.drawable.beauty_1);

        onboardingItems.add(obItem_1);
        onboardingItems.add(obItem_2);
        onboardingItems.add(obItem_3);
        onboardingItems.add(obItem_4);
        onboardingItems.add(obItem_5);
        onboardingItems.add(obItem_6);
        onboardingItems.add(obItem_7);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onbording_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index) {
        int childConunt = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childConunt; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onbording_indicator_inactive)
                );
            }
        }
    }
}
package com.example.chapter3.homework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    ObjectAnimator animator_alpha;
    ObjectAnimator animator_alpha_2,animator_alpha_3,animator_alpha_4,animator_alpha_5,animator_alpha_6,animator_alpha_7;
    TextView tv1,tv2,tv3,tv4,tv5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        animationView = container.findViewById(R.id.animation_view_2);
        animator_alpha = ObjectAnimator.ofFloat(container.findViewById(R.id.animation_view_2),
                "alpha",1f,0f);
        animator_alpha_2 = ObjectAnimator.ofFloat(container.findViewById(R.id.tv1),
                "alpha",0f,1f);
        animator_alpha_3 = ObjectAnimator.ofFloat(container.findViewById(R.id.tv2),
                "alpha",0f,1f);
        animator_alpha_4 = ObjectAnimator.ofFloat(container.findViewById(R.id.tv3),
                "alpha",0f,1f);
        animator_alpha_5 = ObjectAnimator.ofFloat(container.findViewById(R.id.tv4),
                "alpha",0f,1f);
        animator_alpha_6 = ObjectAnimator.ofFloat(container.findViewById(R.id.tv5),
                "alpha",0f,1f);
        animator_alpha_7 = ObjectAnimator.ofFloat(container.findViewById(R.id.tv1),
                "alpha",0f,1f);

        animator_alpha.setDuration(1000);
        animator_alpha_2.setDuration(1000);
        animator_alpha_3.setDuration(1000);
        animator_alpha_4.setDuration(1000);
        animator_alpha_5.setDuration(1000);
        animator_alpha_6.setDuration(1000);
        animator_alpha_7.setDuration(1000);

        tv1=container.findViewById(R.id.tv1);
        tv2=container.findViewById(R.id.tv2);
        tv3=container.findViewById(R.id.tv3);
        tv4=container.findViewById(R.id.tv4);
        tv5=container.findViewById(R.id.tv5);

        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator_alpha,animator_alpha_2,animator_alpha_3,animator_alpha_4,animator_alpha_5,animator_alpha_6,animator_alpha_7);
                animatorSet.start();

            }
        }, 5000);
    }
}

package com.felix.tools.view.loading;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import android.widget.ImageView;
import com.felix.tools.view.R;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RELATIVE_TO_SELF;


public class DefaultStyle implements UIStatus.StatusAdapter {

    @Override
    public View getLoadingView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_status_loading, parent, false);
        ImageView ivLoading = loadingView.findViewById(R.id.image);
        RotateAnimation rotate = new RotateAnimation(0f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(INFINITE);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());
        ivLoading.startAnimation(rotate);
        return loadingView;
    }

    @Override
    public View getErrorView(ViewGroup parent) {
        View errorView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_status_error, parent, false);
        return errorView;
    }

    @Override
    public View getEmptyView(ViewGroup parent) {
        View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_status_empty, parent, false);
        return emptyView;
    }

    @Override
    public void onChange(int oldStatus, int newStatus) {

    }
}

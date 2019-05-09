package com.felix.tools.view.loading;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import android.widget.ImageView;
import com.felix.tools.view.R;
import com.github.ybq.android.spinkit.style.Circle;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RELATIVE_TO_SELF;


public class DefaultAdapter implements UIStatus.StatusAdapter {

    @Override
    public View getLoadingView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_status_loading, parent, false);
        ImageView ivLoading = loadingView.findViewById(R.id.image);
        Circle circleDrawable = new Circle();
        circleDrawable.setColor(Color.RED);


        circleDrawable.start();
        ivLoading.setImageDrawable(circleDrawable);
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

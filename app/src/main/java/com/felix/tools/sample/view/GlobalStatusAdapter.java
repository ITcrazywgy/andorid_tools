package com.felix.tools.sample.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.felix.tools.sample.R;
import com.felix.tools.view.loading.UIStatus;


public class GlobalStatusAdapter implements UIStatus.StatusAdapter {

    @Override
    public View getLoadingView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_status_loading, parent, false);
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

package com.felix.tools.view.loading;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class UIStatus {
    private static final int STATUS_NONE = -1;
    private static final int STATUS_LOADING = 1;
    private static final int STATUS_CONTENT = 2;
    private static final int STATUS_ERROR = 3;
    private static final int STATUS_EMPTY = 4;
    private int mStatus = STATUS_NONE;
    private FrameLayout mRootView;

    private View mContentView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public interface StatusAdapter {
        View getLoadingView(ViewGroup parent);

        View getErrorView(ViewGroup parent);

        View getEmptyView(ViewGroup parent);

        void onChange(int oldStatus, int newStatus);
    }


    private UIStatus() {
    }

    private Builder mBuilder;

    private UIStatus(Builder builder) {
        this.mBuilder = builder;
        this.mContentView = this.mBuilder.mContentView;
        this.mRootView = wrap(this.mBuilder.mContentView);
    }

    public FrameLayout getRootView() {
        return this.mRootView;
    }

    private FrameLayout wrap(View view) {
        FrameLayout wrapper = new FrameLayout(view.getContext());
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            wrapper.setLayoutParams(lp);
        }
        if (view.getParent() != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            int index = parent.indexOfChild(view);
            parent.removeView(view);
            parent.addView(wrapper, index);
        }
        FrameLayout.LayoutParams newLp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        wrapper.addView(view, newLp);
        view.setVisibility(View.INVISIBLE);
        return wrapper;
    }


    public void showLoading() {
        if (null == this.mLoadingView) {
            this.mLoadingView = this.mBuilder.mStatusAdapter.getLoadingView(getRootView());
        }
        if (this.mLoadingView != null) {
            mRootView.addView(this.mLoadingView, 0);
            showView(this.mLoadingView);
            changeViewStatus(STATUS_LOADING);
        }
    }


    public void showContent() {
        if (this.mContentView != null) {
            showView(this.mContentView);
            changeViewStatus(STATUS_CONTENT);
        }
    }

    public void showError() {
        if (null == this.mErrorView) {
            this.mErrorView = this.mBuilder.mStatusAdapter.getErrorView(getRootView());
        }
        if (this.mErrorView != null) {
            mRootView.addView(this.mErrorView, 0);
            showView(this.mErrorView);
            changeViewStatus(STATUS_ERROR);
        }
    }


    public void showEmpty() {
        if (null == this.mEmptyView) {
            this.mEmptyView = this.mBuilder.mStatusAdapter.getEmptyView(getRootView());
        }
        if (this.mEmptyView != null) {
            mRootView.addView(this.mEmptyView, 0);
            showView(this.mEmptyView);
            changeViewStatus(STATUS_EMPTY);
        }
    }


    private void changeViewStatus(int newStatus) {
        if (mStatus == newStatus) {
            return;
        }
        if (null != mBuilder.mStatusAdapter) {
            mBuilder.mStatusAdapter.onChange(mStatus, newStatus);
        }
        mStatus = newStatus;
    }


    private void showView(View statusView) {
        final int childCount = mRootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = mRootView.getChildAt(i);
            if (statusView == child) {
                child.setVisibility(View.VISIBLE);
            } else {
                child.setVisibility(View.GONE);
            }
        }
    }

    public static class Builder {
        View mContentView;
        StatusAdapter mStatusAdapter;

        public Builder content(View contentView) {
            this.mContentView = contentView;
            return this;
        }

        public Builder statusViewAdapter(StatusAdapter adapter) {
            this.mStatusAdapter = adapter;
            return this;
        }


        public UIStatus build() {
            if (mContentView == null) {
                throw new IllegalStateException("ContentView is not specified.");
            }
            if (mStatusAdapter == null) {
                mStatusAdapter = new DefaultAdapter();
            }
            return new UIStatus(this);
        }

    }


}

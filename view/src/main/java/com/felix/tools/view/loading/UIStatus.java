package com.felix.tools.view.loading;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.felix.tools.view.BuildConfig;

public class UIStatus {
    private static boolean DEBUG = BuildConfig.DEBUG;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_LOAD_SUCCESS = 2;
    public static final int STATUS_LOAD_FAILED = 3;
    public static final int STATUS_EMPTY_DATA = 4;
    private SparseArray<View> mStatusViews = new SparseArray<>(4);

    private ViewGroup mRootView;

    public interface StatusViewAdapter {
        View getView(int status);
    }

    private UIStatus() {
    }

    private Builder mBuilder;

    private UIStatus(Builder builder) {
        this.mBuilder = builder;
        this.mRootView = wrap(this.mBuilder.mContentView);
        this.mStatusViews.put(STATUS_LOAD_SUCCESS, this.mRootView);
    }

    private ViewGroup wrap(View view) {
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
        LayoutParams newLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        wrapper.addView(view, newLp);
        return wrapper;
    }


    public void showLoading() {
        View loadingView = mStatusViews.get(STATUS_LOADING);
        if (null == loadingView) {
            loadingView = this.mBuilder.mStatusViewAdapter.getView(STATUS_LOADING);
            mStatusViews.put(STATUS_LOADING, loadingView);
        }
        showViewByStatus(STATUS_LOADING);
    }


    public void showLoadSuccess() {
        showViewByStatus(STATUS_LOAD_SUCCESS);
    }


    public void showLoadFailed() {
        showViewByStatus(STATUS_LOAD_FAILED);
    }


    public void showEmpty() {
        showViewByStatus(STATUS_EMPTY_DATA);
    }


    public void showViewByStatus(int status) {
        final int childCount = mStatusViews.size();
        for (int i = 0; i < childCount; i++) {
            if (status == mStatusViews.keyAt(i)) {
                mStatusViews.valueAt(i).setVisibility(View.VISIBLE);
            } else {
                mStatusViews.valueAt(i).setVisibility(View.GONE);
            }
        }
    }


    public static class Builder {
        public Runnable mRetryTask;
        public View mContentView;
        public StatusViewAdapter mStatusViewAdapter;

        public Builder content(View contentView) {
            this.mContentView = contentView;
            return this;
        }

        public Builder statusViewAdapter(StatusViewAdapter adapter) {
            this.mStatusViewAdapter = adapter;
            return this;
        }

        public Builder withRetry(Runnable task) {
            mRetryTask = task;
            return this;
        }

        public UIStatus build() {
            validate();
            return new UIStatus(this);
        }


        private void validate() {
            if (mStatusViewAdapter == null) {
                throw new IllegalStateException("StatusViewAdapter is not specified.");
            }
            if (mContentView == null) {
                throw new IllegalStateException("ContentView is not specified.");
            }
        }


    }


}

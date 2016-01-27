package io.amosbake.learnrecyclerview.adapter;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 适配RecycleView.ViewHolder的基类
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2015-10-12
 * Time: 15:35
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {
    private final static int MAX_VIEW_NUMBER = 8;//布局中有id的view数 不超过8个
    private final SparseArray<View> mViews;


    public RecyclerHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>(MAX_VIEW_NUMBER);
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerHolder setText(int viewId, @NonNull String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public RecyclerHolder setImage(int viewId, int imgResId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(imgResId);
        return this;
    }

    public RecyclerHolder setImage(int viewId, @NonNull Drawable drawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    public RecyclerHolder setImage(int viewId, @NonNull Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }


    /**
     * 设置控件的左margin
     *
     * @param xPos 左margin像素大小
     */
    public RecyclerHolder setXPos(int viewId, int xPos) {
        View view = getView(viewId);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams tLp = (RelativeLayout.LayoutParams) lp;
            tLp.setMargins(xPos, tLp.topMargin, tLp.rightMargin, tLp.bottomMargin);
        }
        if (lp instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams tLp = (LinearLayout.LayoutParams) lp;
            tLp.setMargins(xPos, tLp.topMargin, tLp.rightMargin, tLp.bottomMargin);
        }
        return this;
    }

    /**
     * 设置item的高度
     */
    public RecyclerHolder setItemHeight(int height) {
        itemView.getLayoutParams().height = height;
        return this;
    }

    /**
     * 设置子控件的高度
     */
    public RecyclerHolder setSize(int viewId, int width, int height) {
        View view = getView(viewId);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        lp.width = width;
        view.setLayoutParams(lp);
        return this;
    }

    /**
     * 设置子控件可见性
     */
    public void setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
    }

    /**
     * 设置字体
     */
    public void setTypeFace(int viewId, Typeface typeFace) {
        ((TextView) getView(viewId)).setTypeface(typeFace);
    }

    /**
     * 设置控件可见
     *
     * @param viewId
     * @param isVisible
     */
    public void setVisible(int viewId, boolean isVisible) {
        getView(viewId).setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}

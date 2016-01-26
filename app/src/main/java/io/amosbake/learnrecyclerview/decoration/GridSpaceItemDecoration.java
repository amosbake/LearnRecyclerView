package io.amosbake.learnrecyclerview.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2015-11-27
 * Time: 16:04
 * RecyclerView间隔生成器
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int spanCount;//列数
    private final int horizontalOuterSpacing;//水平方向上外部间距
    private final int verticalOuterSpacing;//竖直方向上外部间距
    private final int horizontalInnerSpacing;//水平方向上内部间距
    private final int verticalInnerSpacing;//竖直方向上内部间距

    public GridSpaceItemDecoration(int spanCount, int horizontalOuterSpacing, int verticalOuterSpacing, int horizontalInnerSpacing, int verticalInnerSpacing) {
        this.spanCount = spanCount;
        this.horizontalOuterSpacing = horizontalOuterSpacing;
        this.verticalOuterSpacing = verticalOuterSpacing;
        this.horizontalInnerSpacing = horizontalInnerSpacing;
        this.verticalInnerSpacing = verticalInnerSpacing;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;
        if (0 == column) {
            outRect.left = horizontalOuterSpacing;
        } else {
            outRect.left = horizontalInnerSpacing / 2;
        }
        if (spanCount - 1 == column) {
            outRect.right = horizontalOuterSpacing;
        } else {
            outRect.right = horizontalInnerSpacing / 2;
        }
        if (position < spanCount) {
            outRect.top = verticalOuterSpacing;
        } else {
            outRect.top = verticalInnerSpacing;
        }
    }

    public static class Builder {
        private Resources mResources;
        private int spanCount;//列数
        private int horizontalOuterSpacing;//水平方向上外部间距
        private int verticalOuterSpacing;//竖直方向上外部间距
        private int horizontalInnerSpacing;//水平方向上内部间距
        private int verticalInnerSpacing;//竖直方向上内部间距

        public Builder(Context context) {
            mResources = context.getResources();
            spanCount = 1;
            horizontalOuterSpacing = 0;
            verticalOuterSpacing = 0;
            horizontalInnerSpacing = 0;
            verticalInnerSpacing = 0;
        }

        public Builder setSpanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }

        public Builder setHOuter(float pixels) {
            this.horizontalOuterSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setHOuter(@DimenRes int resId) {
            this.horizontalOuterSpacing = mResources.getDimensionPixelSize(resId);
            return this;
        }

        public Builder setVOuter(float pixels) {
            this.verticalOuterSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setVOuter(@DimenRes int resId) {
            this.verticalOuterSpacing = mResources.getDimensionPixelSize(resId);
            return this;
        }

        public Builder setHInner(float pixels) {
            this.horizontalInnerSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setHInner(@DimenRes int resId) {
            this.horizontalInnerSpacing = mResources.getDimensionPixelSize(resId);
            return this;
        }

        public Builder setVInner(float pixels) {
            this.verticalInnerSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setVInner(int resId) {
            this.verticalInnerSpacing = mResources.getDimensionPixelSize(resId);
            return this;
        }

        public GridSpaceItemDecoration build() {
            return new GridSpaceItemDecoration(spanCount, horizontalOuterSpacing, verticalOuterSpacing, horizontalInnerSpacing, verticalInnerSpacing);
        }
    }

}

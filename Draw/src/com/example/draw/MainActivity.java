package com.example.draw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Picture;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends GraphicsActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new SampleView(this));
  }

  private static class SampleView extends View {
    private AnimateDrawable mDrawable;

    public SampleView(Context context) {
      super(context);
      setFocusable(true);
      setFocusableInTouchMode(true);

      Drawable dr = context.getResources().getDrawable(R.drawable.ic_launcher);
      dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());

      Animation an = new TranslateAnimation(0, 100, 0, 200);
      an.setDuration(2000);
      an.setRepeatCount(-1);
      an.initialize(10, 10, 10, 10);

      mDrawable = new AnimateDrawable(dr, an);
      an.startNow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
      canvas.drawColor(Color.WHITE);

      mDrawable.draw(canvas);
      invalidate();
    }
  }
}

class ProxyDrawable extends Drawable {

  private Drawable mProxy;
  private boolean mMutated;

  public ProxyDrawable(Drawable target) {
    mProxy = target;
  }

  public Drawable getProxy() {
    return mProxy;
  }

  public void setProxy(Drawable proxy) {
    if (proxy != this) {
      mProxy = proxy;
    }
  }

  @Override
  public void draw(Canvas canvas) {
    if (mProxy != null) {
      mProxy.draw(canvas);
    }
  }

  @Override
  public int getIntrinsicWidth() {
    return mProxy != null ? mProxy.getIntrinsicWidth() : -1;
  }

  @Override
  public int getIntrinsicHeight() {
    return mProxy != null ? mProxy.getIntrinsicHeight() : -1;
  }

  @Override
  public int getOpacity() {
    return mProxy != null ? mProxy.getOpacity() : PixelFormat.TRANSPARENT;
  }

  @Override
  public void setFilterBitmap(boolean filter) {
    if (mProxy != null) {
      mProxy.setFilterBitmap(filter);
    }
  }

  @Override
  public void setDither(boolean dither) {
    if (mProxy != null) {
      mProxy.setDither(dither);
    }
  }

  @Override
  public void setColorFilter(ColorFilter colorFilter) {
    if (mProxy != null) {
      mProxy.setColorFilter(colorFilter);
    }
  }

  @Override
  public void setAlpha(int alpha) {
    if (mProxy != null) {
      mProxy.setAlpha(alpha);
    }
  }

  @Override
  public Drawable mutate() {
    if (mProxy != null && !mMutated && super.mutate() == this) {
      mProxy.mutate();
      mMutated = true;
    }
    return this;
  }
}

class AnimateDrawable extends ProxyDrawable {

  private Animation mAnimation;
  private Transformation mTransformation = new Transformation();

  public AnimateDrawable(Drawable target) {
    super(target);
  }

  public AnimateDrawable(Drawable target, Animation animation) {
    super(target);
    mAnimation = animation;
  }

  public Animation getAnimation() {
    return mAnimation;
  }

  public void setAnimation(Animation anim) {
    mAnimation = anim;
  }

  public boolean hasStarted() {
    return mAnimation != null && mAnimation.hasStarted();
  }

  public boolean hasEnded() {
    return mAnimation == null || mAnimation.hasEnded();
  }

  @Override
  public void draw(Canvas canvas) {
    Drawable dr = getProxy();
    if (dr != null) {
      int sc = canvas.save();
      Animation anim = mAnimation;
      if (anim != null) {
        anim.getTransformation(
            AnimationUtils.currentAnimationTimeMillis(),
            mTransformation);
        canvas.concat(mTransformation.getMatrix());
      }
      dr.draw(canvas);
      canvas.restoreToCount(sc);
    }
  }
}

class GraphicsActivity extends Activity {
  // set to true to test Picture
  private static final boolean TEST_PICTURE = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void setContentView(View view) {
    if (TEST_PICTURE) {
      ViewGroup vg = new PictureLayout(this);
      vg.addView(view);
      view = vg;
    }

    super.setContentView(view);
  }
}

class PictureLayout extends ViewGroup {
  private final Picture mPicture = new Picture();

  public PictureLayout(Context context) {
    super(context);
  }

  public PictureLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public void addView(View child) {
    if (getChildCount() > 1) {
      throw new IllegalStateException(
          "PictureLayout can host only one direct child");
    }

    super.addView(child);
  }

  @Override
  public void addView(View child, int index) {
    if (getChildCount() > 1) {
      throw new IllegalStateException(
          "PictureLayout can host only one direct child");
    }

    super.addView(child, index);
  }

  @Override
  public void addView(View child, LayoutParams params) {
    if (getChildCount() > 1) {
      throw new IllegalStateException(
          "PictureLayout can host only one direct child");
    }

    super.addView(child, params);
  }

  @Override
  public void addView(View child, int index, LayoutParams params) {
    if (getChildCount() > 1) {
      throw new IllegalStateException(
          "PictureLayout can host only one direct child");
    }

    super.addView(child, index, params);
  }

  @Override
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.MATCH_PARENT);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    final int count = getChildCount();

    int maxHeight = 0;
    int maxWidth = 0;

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
      }
    }

    maxWidth += getPaddingLeft() + getPaddingRight();
    maxHeight += getPaddingTop() + getPaddingBottom();

    Drawable drawable = getBackground();
    if (drawable != null) {
      maxHeight = Math.max(maxHeight, drawable.getMinimumHeight());
      maxWidth = Math.max(maxWidth, drawable.getMinimumWidth());
    }

    setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec),
        resolveSize(maxHeight, heightMeasureSpec));
  }

  private void drawPict(Canvas canvas, int x, int y, int w, int h, float sx,
      float sy) {
    canvas.save();
    canvas.translate(x, y);
    canvas.clipRect(0, 0, w, h);
    canvas.scale(0.5f, 0.5f);
    canvas.scale(sx, sy, w, h);
    canvas.drawPicture(mPicture);
    canvas.restore();
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(mPicture.beginRecording(getWidth(), getHeight()));
    mPicture.endRecording();

    int x = getWidth() / 2;
    int y = getHeight() / 2;

    if (false) {
      canvas.drawPicture(mPicture);
    } else {
      drawPict(canvas, 0, 0, x, y, 1, 1);
      drawPict(canvas, x, 0, x, y, -1, 1);
      drawPict(canvas, 0, y, x, y, 1, -1);
      drawPict(canvas, x, y, x, y, -1, -1);
    }
  }

  @Override
  public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
    location[0] = getLeft();
    location[1] = getTop();
    dirty.set(0, 0, getWidth(), getHeight());
    return getParent();
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int count = super.getChildCount();

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        child.layout(childLeft, childTop,
            childLeft + child.getMeasuredWidth(),
            childTop + child.getMeasuredHeight());

      }
    }
  }
}
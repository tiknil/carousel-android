package fr.rolandl.carousel;

import android.content.Context;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author Igor Kushnarev, Ludovic Roland
 * @since 2014.12.19
 */
//Inspired by http://www.codeproject.com/Articles/146145/Android-D-Carousel
public abstract class CarouselItem<T>
        extends FrameLayout
        implements Comparable<CarouselItem<?>> {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields
    private int index;
    private float currentAngle;
    private float itemX;
    private float itemY;
    private float itemZ;
    private boolean drawn;

    private Matrix matrix;
    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle
    public CarouselItem(Context context, int layoutId) {
        super(context);

        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);

        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(layoutId, this, true);

        extractView(view);
    }
    //endregion


    //region Custom accessors
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }

    public float getCurrentAngle() {
        return currentAngle;
    }

    public void setItemX(float x) {
        this.itemX = x;
    }

    public float getItemX() {
        return itemX;
    }

    public void setItemY(float y) {
        this.itemY = y;
    }

    public float getItemY() {
        return itemY;
    }

    public void setItemZ(float z) {
        this.itemZ = z;
    }

    public float getItemZ() {
        return itemZ;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public Matrix getCIMatrix() {
        return matrix;
    }

    void setCIMatrix(Matrix mMatrix) {
        this.matrix = mMatrix;
    }
    //endregion


    //region Public
    public abstract void extractView(View view);

    public abstract void update(T arg0);

    public abstract int getPreferredWidth();

    public abstract int getPreferredHeight();
    //endregion

    //region Protected, without modifier
    //endregion

    //region Private
    //endregion


    //region Override methods and callbacks
    @Override
    public int compareTo(CarouselItem<?> another) {
        return (int) (another.itemZ - this.itemZ);
    }
    //endregion

    //region Inner classes or interfaces
    //endregion
}

package fr.rolandl.carousel;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ludovic ROLAND
 * @since 2014.12.20
 * <p>
 * Adapter class for the Carousel
 */
public abstract class CarouselAdapter<T>
        extends BaseAdapter {


    //region Instance Fields
    private final List<T> modelItems = new ArrayList<>();
    private final List<CarouselItem<T>> items = new ArrayList<>();
    //endregion

    //region Constructors / Lifecycle
    public CarouselAdapter(Context context, List<T> items) {
        modelItems.addAll(items);
        for (int i = 0; i < items.size(); i++) {
            final CarouselItem<T> item = getCarouselItem(context);
            item.setIndex(i);
            item.update(items.get(i));
            this.items.add(item);
        }

        registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                update();
            }

            @Override
            public void onInvalidated() {
                update();
            }
        });
    }
    //endregion

    //region Public
    public abstract CarouselItem<T> getCarouselItem(Context context);
    //endregion

    //region Protected, without modifier
    //endregion

    //region Private
    private void update() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).update(modelItems.get(i));
        }
    }
    //endregion


    //region Override methods and callbacks
    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        }

        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return items.get(position);
    }
    //endregion
}

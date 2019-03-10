package io.keyss.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Key
 * Time: 2019/03/10 18:07
 * Description:
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeViewHolder> {

    private List<IMultiTypeItem> items = new ArrayList<>();

    @NonNull
    @Override
    public MultiTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MultiTypeViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiTypeViewHolder holder, int position) {
        holder.bindTo(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getLayout();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<IMultiTypeItem> getItems() {
        return items;
    }

    public int findPos(IMultiTypeItem item) {
        return items.indexOf(item);
    }

    public void setItem(IMultiTypeItem item) {
        clearItems();
        addItem(item);
    }

    public void setItems(List<IMultiTypeItem> items) {
        clearItems();
        addItems(items);
    }

    public void addItem(IMultiTypeItem item) {
        items.add(item);
    }

    public void addItem(IMultiTypeItem item, int index) {
        items.add(index, item);
    }

    public void addItems(List<IMultiTypeItem> items) {
        this.items.addAll(items);
    }

    public int removeItem(IMultiTypeItem item) {
        int pos = findPos(item);
        items.remove(item);
        return pos;
    }

    public void clearItems() {
        items.clear();
    }
}

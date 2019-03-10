package io.keyss.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Key
 * Time: 2019/03/10 18:19
 * Description:
 */
public class MultiTypeViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    static MultiTypeViewHolder create(ViewGroup parent, int viewType) {
        ViewDataBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        viewType, parent, false);
        return new MultiTypeViewHolder(binding);
    }

    MultiTypeViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bindTo(IMultiTypeItem item) {
        binding.setVariable(item.getVariableId(), item);
        binding.executePendingBindings();
    }
}

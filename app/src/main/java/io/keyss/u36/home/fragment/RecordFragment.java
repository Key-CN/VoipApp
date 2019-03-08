package io.keyss.u36.home.fragment;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.keyss.base.view.BaseFragment;
import io.keyss.u36.R;
import io.keyss.u36.databinding.FragmentRecordBinding;
import io.keyss.u36.databinding.ItemRecordFmtBinding;

/**
 * @author Key
 * Time: 2019/02/20 16:06
 * Description:
 */
public class RecordFragment extends BaseFragment<FragmentRecordBinding> {

    private ArrayList<String> numbers;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initLayout() {
        numbers = new ArrayList<>();
        Cursor query = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, //系统方式获取通讯录存储地址
                new String[]{
                        CallLog.Calls.CACHED_NAME,  //姓名
                        CallLog.Calls.NUMBER,    //号码
                        CallLog.Calls.TYPE,  //呼入/呼出(2)/未接
                        CallLog.Calls.DATE,  //拨打时间
                        CallLog.Calls.DURATION,   //通话时长
                }, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        if (null != query && query.getCount() > 0) {
            int count = query.getCount();
            query.moveToFirst();
            for (int i = 0; i < count && !query.isAfterLast(); query.moveToNext(), i++) {
                String callName = query.getString(0);  //名称
                String callNumber = query.getString(1);  //号码
                numbers.add("[" + callName + "]" + callNumber);
            }
        }

        binding.rvRecordFmt.setAdapter(new ItemAdapter());
        binding.rvRecordFmt.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rvRecordFmt.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemRecordFmtBinding bind = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_record_fmt, viewGroup, false);
            return new ItemViewHolder(bind);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            itemViewHolder.bind.tvNameRecordItem.setText(numbers.get(i));
        }

        @Override
        public int getItemCount() {
            return numbers.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            private final ItemRecordFmtBinding bind;

            public ItemViewHolder(@NonNull ItemRecordFmtBinding bind) {
                super(bind.getRoot());
                this.bind = bind;
            }
        }
    }
}

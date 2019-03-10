package io.keyss.u36.home.fragment;

import android.database.Cursor;
import android.provider.CallLog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import io.keyss.base.adapter.IMultiTypeItem;
import io.keyss.base.adapter.MultiTypeAdapter;
import io.keyss.base.view.BaseFragment;
import io.keyss.u36.R;
import io.keyss.u36.databinding.FragmentRecordBinding;
import io.keyss.u36.home.item.RecordItem;

/**
 * @author Key
 * Time: 2019/02/20 16:06
 * Description:
 */
public class RecordFragment extends BaseFragment<FragmentRecordBinding> {

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initLayout() {
        List<IMultiTypeItem> numbers = new ArrayList<>();
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
                numbers.add(new RecordItem(callName + "[" + callNumber + "]"));
            }
            query.close();
        }

        binding.rvRecordFmt.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rvRecordFmt.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.addItems(numbers);
        binding.rvRecordFmt.setAdapter(adapter);
    }
}

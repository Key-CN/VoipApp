package io.keyss.u36.home.fragment;

import io.keyss.base.view.BaseFragment;
import io.keyss.u36.R;

/**
 * @author Key
 * Time: 2019/02/20 16:06
 * Description:
 */
public class RecordFragment extends BaseFragment {

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initLayout() {
        /*Cursor query = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, //系统方式获取通讯录存储地址
                new String[]{
                        CallLog.Calls.CACHED_NAME,  //姓名
                        CallLog.Calls.NUMBER,    //号码
                        CallLog.Calls.TYPE,  //呼入/呼出(2)/未接
                        CallLog.Calls.DATE,  //拨打时间
                        CallLog.Calls.DURATION,   //通话时长
                }, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        if (null != query) {
            Toast.makeText(mContext, "获取到通话记录" + query.getCount() + "条", Toast.LENGTH_SHORT).show();
        }*/
    }
}

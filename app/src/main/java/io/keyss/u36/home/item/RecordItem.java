package io.keyss.u36.home.item;

import io.keyss.base.adapter.IMultiTypeItem;
import io.keyss.u36.BR;
import io.keyss.u36.R;

/**
 * @author Key
 * Time: 2019/03/10 18:37
 * Description:
 */
public class RecordItem implements IMultiTypeItem {

    /*
    CallLog.Calls.CACHED_NAME,  //姓名
    CallLog.Calls.NUMBER,    //号码
    CallLog.Calls.TYPE,  //呼入/呼出(2)/未接
    CallLog.Calls.DATE,  //拨打时间
    CallLog.Calls.DURATION,   //通话时长
     */
    public String name;
    public String number;
    public String type;
    public String date;
    public String duration;

    public RecordItem(String name, String number, String type, String date, String duration) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.date = date;
        this.duration = duration;
    }

    @Override
    public int getLayout() {
        return R.layout.item_record_fmt;
    }

    @Override
    public int getVariableId() {
        return BR.recordItem;
    }
}

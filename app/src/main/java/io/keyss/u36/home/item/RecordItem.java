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

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RecordItem(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

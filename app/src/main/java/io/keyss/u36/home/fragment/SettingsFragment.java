package io.keyss.u36.home.fragment;

import android.view.View;
import android.widget.Toast;

import io.keyss.base.view.BaseFragment;
import io.keyss.u36.R;
import io.keyss.u36.databinding.FragmentSettingsBinding;

/**
 * @author Key
 * Time: 2019/02/20 16:06
 * Description:
 */
public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initLayout() {
        binding.setButtonTest(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

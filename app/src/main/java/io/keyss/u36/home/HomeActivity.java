package io.keyss.u36.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.keyss.base.activity.BaseActivity;
import io.keyss.u36.R;
import io.keyss.u36.databinding.ActivityHomeBinding;
import io.keyss.u36.home.data.DataHome;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        binding.setHome(new DataHome());
    }

    @Override
    protected void onActivityInitialized() {

    }
}

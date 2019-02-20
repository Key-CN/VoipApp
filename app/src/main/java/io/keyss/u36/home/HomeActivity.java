package io.keyss.u36.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.ArrayList;

import io.keyss.base.view.BaseActivity;
import io.keyss.u36.R;
import io.keyss.u36.databinding.ActivityHomeBinding;
import io.keyss.u36.home.fragment.RecordFragment;
import io.keyss.u36.home.fragment.SettingsFragment;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecordFragment());
        fragments.add(new SettingsFragment());
        binding.vpHomeAct.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        binding.bnvHomeAct.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.id1:
                        binding.vpHomeAct.setCurrentItem(0);
                        break;
                    case R.id.id2:
                        binding.vpHomeAct.setCurrentItem(1);
                        break;
                }
                return false;
            }
        });

        binding.vpHomeAct.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bnvHomeAct.getMenu().getItem(position).setChecked(true);
            }
        });
    }
}

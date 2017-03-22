package com.example.craft.budget2;

import android.support.v4.app.Fragment;

/**
 * Created by craft on 2/21/2017.
 */

public class BudgetListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new BudgetListFragment();
    }
}

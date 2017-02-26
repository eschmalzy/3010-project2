package com.example.craft.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by craft on 2/21/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}

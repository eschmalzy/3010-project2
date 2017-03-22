package com.example.craft.budget2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class BudgetActivity extends SingleFragmentActivity {

    private static final String EXTRA_BUDGET_ID =
            "com.example.craft.budget2.budget_id";

    public static Intent newCreateIntent(Context packageContext){
        Intent intent = new Intent(packageContext, BudgetActivity.class);
        return intent;
    }

    public static Intent newIntent(Context packageContext, UUID BudgetId){
        Intent intent = new Intent(packageContext, BudgetActivity.class);
        intent.putExtra(EXTRA_BUDGET_ID, BudgetId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID BudgetId = (UUID) getIntent().getSerializableExtra(EXTRA_BUDGET_ID);
        return BudgetFragment.newInstance(BudgetId);
    }
}

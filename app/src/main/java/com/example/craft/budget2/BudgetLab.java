package com.example.craft.budget2;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by craft on 2/15/2017.
 */

public class BudgetLab {
    private static BudgetLab sBudgetLab;

    public List<Budget> mBudgets;

    public static BudgetLab get(Context context){
        if (sBudgetLab == null){
            sBudgetLab = new BudgetLab(context);
        }
        return sBudgetLab;
    }

    private BudgetLab(Context context){
        mBudgets = new ArrayList<>();
//        Budget budget = new Budget();
//        budget.setTitle("1");
//        budget.setAmount(1);
//        mBudgets.add(budget);
    }

    public List<Budget> getBudgets(){
        return mBudgets;
    }

    public Budget getBudget(UUID id){
        for (Budget Budget : mBudgets){
            if(Budget.getId().equals(id)){
                return Budget;
            }
        }
        return null;
    }

    public void addBudget(Budget budget){
        mBudgets.add(budget);
    }

}

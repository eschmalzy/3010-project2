package com.example.craft.budget2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by craft on 2/15/2017.
 */

public class BudgetFragment extends Fragment {
    private static final String ARG_BUDGET_ID = "budget_id";

    private Budget mBudget;
    private EditText mTitleField;
    private EditText mAmountField;


    public static BudgetFragment newInstance(UUID BudgetId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_BUDGET_ID, BudgetId);

        BudgetFragment fragment = new BudgetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID budgetId = (UUID) getArguments().getSerializable(ARG_BUDGET_ID);
        if(BudgetLab.get(getActivity()).getBudget(budgetId) != null) {
            mBudget = BudgetLab.get(getActivity()).getBudget(budgetId);
        }else{
            Budget budget = new Budget();
            budget.setTitle("");
            budget.setAmount(-123456789);
            BudgetLab.get(getActivity()).addBudget(budget);
            mBudget = budget;
            Log.d("budget","making new mBudget");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_budget, container, false);

        mTitleField = (EditText) v.findViewById(R.id.budget_title);
        if(mBudget.getTitle() != "") {
            mTitleField.setText(mBudget.getTitle());
        }else{
            mTitleField.setText("");
        }
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mBudget.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAmountField = (EditText) v.findViewById(R.id.budget_amount);
        if(mBudget.getAmount() != -123456789) {
            mAmountField.setText(String.valueOf(mBudget.getAmount()));
        } else{
            mAmountField.setText("");
        }
        mAmountField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mBudget.setAmount(Integer.parseInt(s.toString()));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }



}

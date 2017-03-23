package com.example.craft.budget2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by craft on 2/21/2017.
 */

public class BudgetListFragment extends Fragment {
    private RecyclerView mBudgetRecyclerView;
    private BudgetAdapter mAdapter;
    private Button mCreateButton;
    private List<Budget> mBudgets;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_budget_list, container, false);

        mBudgetRecyclerView = (RecyclerView) view.findViewById(R.id.budget_recycler_view);
        mBudgetRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCreateButton = (Button) view.findViewById(R.id.create_button);
        mCreateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = BudgetActivity.newCreateIntent(getActivity());
                startActivity(intent);
            }
        });

        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        BudgetLab budgetLab = BudgetLab.get(getActivity());
        List<Budget> budgets = budgetLab.getBudgets();

        if(mAdapter == null) {
            mAdapter = new BudgetAdapter(budgets);
            mBudgetRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BudgetAdapter extends RecyclerView.Adapter<BudgetHolder>{


        public BudgetAdapter(List<Budget> budgets){
            mBudgets = budgets;
        }

        @Override
        public BudgetHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_budget, parent, false);
            return new BudgetHolder(view);
        }

        @Override
        public void onBindViewHolder(BudgetHolder holder, int position) {
            Budget budget = mBudgets.get(position);
            for(Iterator<Budget> iter = mBudgets.listIterator(); iter.hasNext();){
                Budget b = iter.next();
                if(b.getAmount() == -123456789){
                    iter.remove();
                }
            }
            holder.bindBudget(budget);
        }

        @Override
        public int getItemCount(){
            return mBudgets.size();
        }

    }

    private class BudgetHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mAmountTextView;
        private Budget mBudget;

        public void bindBudget(Budget budget){
            mBudget = budget;
            mTitleTextView.setText(mBudget.getTitle());
            mAmountTextView.setText("$ " + String.valueOf(mBudget.getAmount()));
        }


        public BudgetHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_budget_title_text_view);
            mAmountTextView = (TextView) itemView.findViewById(R.id.list_item_budget_amount_text_view);
        }

        @Override
        public void onClick(View v){
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//            alertDialogBuilder.setTitle(R.string.alert_title);
//
//            alertDialogBuilder.setMessage(mBudget.getTitle()).setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Intent intent = BudgetActivity.newIntent(getActivity(), mBudget.getId());
//                    startActivity(intent);
//
//                }
//            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.cancel();
//                }
//            });
//            AlertDialog alertDialog = alertDialogBuilder.create();


            LayoutInflater inflater = getActivity().getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.dialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

            TextView budgetTitle = (TextView)  dialogLayout.findViewById(R.id.dialog_budget_title);
            TextView budgetAmount = (TextView)  dialogLayout.findViewById(R.id.dialog_budget_amount);

            budgetTitle.setText(mBudget.getTitle());
            budgetAmount.setText("$ "+mBudget.getAmount());

            alertDialogBuilder.setTitle("Budget");
            alertDialogBuilder.setView(dialogLayout).setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = BudgetActivity.newIntent(getActivity(), mBudget.getId());
                    startActivity(intent);

                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setView(dialogLayout);

            alertDialog.show();
        }
    }



}

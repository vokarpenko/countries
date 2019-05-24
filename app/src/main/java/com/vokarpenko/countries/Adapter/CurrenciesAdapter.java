package com.vokarpenko.countries.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vokarpenko.countries.Model.Entity.CurrencyModel;
import com.vokarpenko.countries.R;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolder> {
    private List<CurrencyModel> currencies = new ArrayList<>();

    public void setCurrencies(List<CurrencyModel> currencies) {
        this.currencies = currencies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_currency, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(currencies.get(i).getCurrencyName());
        viewHolder.code.setText(currencies.get(i).getCode());
        viewHolder.symbol.setText(currencies.get(i).getSymbol());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,code,symbol;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.currency_name);
            code=itemView.findViewById(R.id.currency_code);
            symbol=itemView.findViewById(R.id.currency_symbol);
        }
    }
}

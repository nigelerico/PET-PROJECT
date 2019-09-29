package com.skripsi.nigel.esvira.Adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.skripsi.nigel.esvira.Model.Ongkir;
import com.skripsi.nigel.esvira.R;

import java.util.ArrayList;
import java.util.List;

public class OngkirAdapter extends ArrayAdapter<Ongkir> {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<Ongkir> items;
    private ArrayList<Ongkir> itemsAll;
    private ArrayList<Ongkir> suggestions;
    private int viewResourceId;

    public OngkirAdapter(Context context, int viewResourceId, ArrayList<Ongkir> items) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = (ArrayList<Ongkir>) items.clone();
        this.suggestions = new ArrayList<Ongkir>();
        this.viewResourceId = viewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        Ongkir customer = items.get(position);
        if (customer != null) {
            TextView txt_kota = (TextView) v.findViewById(R.id.kota);
            if (txt_kota != null) {
//              Log.i(MY_DEBUG_TAG, "getView Customer Name:"+customer.getName());
                txt_kota.setText(customer.getKota());
            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Ongkir)(resultValue)).getKota();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (Ongkir ongkir : itemsAll) {
                    if(ongkir.getKota().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(ongkir);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Ongkir> filteredList = (ArrayList<Ongkir>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Ongkir o : filteredList) {
                    add(o);
                }
                notifyDataSetChanged();
            }
        }
    };

}
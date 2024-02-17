package com.example.pregatireassignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AlimentAdapter extends ArrayAdapter<Aliment>{

    private Context context;
    private int resursa;
    private List<Aliment> listaAlimente;
    private LayoutInflater inflater;

    public AlimentAdapter(@NonNull Context context, int resursa, List<Aliment> listaAlimente, LayoutInflater inflater) {
        super(context,resursa,listaAlimente);
        this.context = context;
        this.resursa = resursa;
        this.listaAlimente = listaAlimente;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resursa,parent, false);
        Aliment aliment = listaAlimente.get(position);
        if(aliment!=null){
            TextView tvID = view.findViewById(R.id.tvID);
            TextView tvNume = view.findViewById(R.id.tvNume);
            TextView tvPret = view.findViewById(R.id.tvPret);

            tvID.setText(String.valueOf(aliment.getId()));
            tvNume.setText(aliment.getNume());
            tvPret.setText(String.valueOf(aliment.getPret()));
        }

        return view;
    }
}

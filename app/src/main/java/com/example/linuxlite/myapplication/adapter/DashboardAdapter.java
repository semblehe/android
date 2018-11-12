package com.example.linuxlite.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linuxlite.myapplication.R;
import com.example.linuxlite.myapplication.model.KotaModel;

import java.util.List;

public class DashboardAdapter  extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private List<KotaModel> listKotamodel;
    private Context context;

    public DashboardAdapter(List<KotaModel> listJadawalmodel, Context context) {
        this.listKotamodel = listJadawalmodel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KotaModel model = listKotamodel.get(position);

        try {
            holder.tvKota.setText(model.getKota());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listKotamodel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvKota;
        public ViewHolder(View itemView) {
            super(itemView);
            tvKota = (TextView) itemView.findViewById(R.id.tv_kota);
        }
    }
}

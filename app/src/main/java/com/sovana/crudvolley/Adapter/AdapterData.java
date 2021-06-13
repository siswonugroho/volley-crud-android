package com.sovana.crudvolley.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sovana.crudvolley.InsertData;
import com.sovana.crudvolley.Model.ModelData;
import com.sovana.crudvolley.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
  private List<ModelData> mItems;
  private Context context;

  public AdapterData(Context context, List<ModelData> items) {
    this.mItems = items;
    this.context = context;
  }

  @Override
  public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
    View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
    HolderData holderData = new HolderData(layout);
    return holderData;
  }

  @Override
  public void onBindViewHolder(HolderData holder, int position) {
    ModelData md = mItems.get(position);
    holder.tvusername.setText(md.getUsername());
    holder.tvgrup.setText(md.getGrup());
    holder.tvnama.setText(md.getNama());
    holder.md = md;
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  class HolderData extends RecyclerView.ViewHolder {
    TextView tvusername, tvgrup, tvnama;
    ModelData md;

    public HolderData(View view) {
      super(view);
      tvusername = (TextView) view.findViewById(R.id.username);
      tvgrup = (TextView) view.findViewById(R.id.grup);
      tvnama = (TextView) view.findViewById(R.id.nama);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent update = new Intent(context, InsertData.class);
          update.putExtra("update", 1);
          update.putExtra("username", md.getUsername());
          update.putExtra("grup", md.getGrup());
          update.putExtra("id_grup", md.getIdGrup());
          update.putExtra("nama", md.getNama());
          update.putExtra("password", md.getPassword());
          update.putExtra("id", md.getId());

          context.startActivity(update);
        }

      });

    }

  }

}


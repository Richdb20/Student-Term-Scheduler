package com.example.richardbrown_c196.Activities;

import java.util.List;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import com.example.richardbrown_c196.R;
import androidx.recyclerview.widget.RecyclerView;
import com.example.richardbrown_c196.Entities.TermsEntity;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        private TermViewHolder (View itemView) {
            super (itemView);
            termItemView=itemView.findViewById(R.id.termTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final TermsEntity currentPosition=mTerms.get(position);
                    Intent intent = new Intent(context, TermDetailsActivity.class);
                    intent.putExtra("id", currentPosition.getTermID());
                    intent.putExtra("name", currentPosition.getTitle());
                    intent.putExtra("startDate", currentPosition.getStartDate());
                    intent.putExtra("endDate", currentPosition.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<TermsEntity> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public TermAdapter (Context context) {
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View termItemView=mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(termItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null) {
        TermsEntity current=mTerms.get(position);
        String title=current.getTitle();
        holder.termItemView.setText(title);
        }
        else{
            holder.termItemView.setText("No Term Name");
        }
    }

    public void setTerms(List<TermsEntity> terms) {
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTerms != null) {
            return mTerms.size();
        }
        else return 0;
    }
}

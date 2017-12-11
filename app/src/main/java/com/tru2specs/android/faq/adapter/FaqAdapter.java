package com.tru2specs.android.faq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.lens.adapter.LensesAdapter;
import com.tru2specs.android.lens.view.ILensesView;
import com.tru2specs.android.objects.responses.faq.Faq;
import com.tru2specs.android.objects.responses.lens.Lenses;
import com.tru2specs.android.util.Constants;

import java.util.List;

/**
 * Created by palgour on 12/3/17.
 */

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {

    //    private final OnUpdateAddressListener mListener;
    private Context mContext;
    private List<Faq> mList;
    private static RadioButton lastDefault = null;
    private static int lastDefaultPos = 0;
    ILensesView mView;

    public FaqAdapter(Context context, List<Faq> faqList) {
        this.mContext = context;
        this.mList = faqList;
    }


    @Override
    public FaqAdapter.FaqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_faq_row, parent, false);

        return new FaqAdapter.FaqViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FaqAdapter.FaqViewHolder holder, final int position) {
        final Faq faq = mList.get(position);
        holder.mQues.setText(faq.getQuestion());
        holder.mAnswer.setText(faq.getAnswer());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FaqViewHolder extends RecyclerView.ViewHolder {
        TextView mQues;
        TextView mAnswer;

        public FaqViewHolder(View itemView) {
            super(itemView);
            mQues = (TextView) itemView.findViewById(R.id.txt_faq_ques);
            mAnswer = (TextView) itemView.findViewById(R.id.txt_faq_ans);
        }
    }
}

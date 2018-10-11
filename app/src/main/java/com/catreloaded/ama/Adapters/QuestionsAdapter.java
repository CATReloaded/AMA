package com.catreloaded.ama.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catreloaded.ama.Interfaces.QuestionClickListener;
import com.catreloaded.ama.Objects.Question;
import com.catreloaded.ama.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>{

    private List<Question> mData;
    private QuestionClickListener mQuestionClickListener;

    public QuestionsAdapter(List<Question> data){
        mData = data;
    }

    public QuestionsAdapter(List<Question> data,QuestionClickListener questionClickListener){
        mData = data;
        mQuestionClickListener = questionClickListener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_item,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.tvQuestion.setText(mData.get(position).getQuestion() + " ?");
        if(mData.get(position).getAnswer().equals("NULL")){
            holder.tvAnswer.setVisibility(View.GONE);
        }else{
            holder.tvAnswer.setText(mData.get(position).getAnswer());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_question_rv_item)
        TextView tvQuestion;

        @BindView(R.id.tv_answer_rv_item)
        TextView tvAnswer;

        QuestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!mData.get(getAdapterPosition()).isAnswered()){
                        mQuestionClickListener.onQuestionClicked(mData.get(getAdapterPosition()).getmId(),mData.get(getAdapterPosition()).getQuestion());
                    }
                }
            });
        }
    }
}

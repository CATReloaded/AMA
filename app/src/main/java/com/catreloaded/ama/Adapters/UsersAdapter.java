package com.catreloaded.ama.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catreloaded.ama.Interfaces.UserClickListener;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{

    private List<User> mData;
    private final UserClickListener mUserClickListener;

    public UsersAdapter(List<User> data,UserClickListener userClickListener){
        mData = data;
        mUserClickListener = userClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textView7.setText(mData.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textView7)
        TextView textView7;
        UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mUserClickListener.onUserClickedListener(mData.get(getAdapterPosition()));
                }
            });
        }
    }
}

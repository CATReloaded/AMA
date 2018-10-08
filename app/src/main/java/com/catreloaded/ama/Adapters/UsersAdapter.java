package com.catreloaded.ama.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.catreloaded.ama.Interfaces.UserClickListener;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{

    private List<User> mData;
    private final UserClickListener mUserClickListener;
    private Context mContext;

    public UsersAdapter(List<User> data,UserClickListener userClickListener){
        mData = data;
        mUserClickListener = userClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item,parent,false);
        mContext = view.getContext();
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tvUserName.setText(mData.get(position).getUserName());
        Glide.with(mContext)
                .load(mData.get(position).getAvatarLink()).apply(RequestOptions.circleCropTransform().error(R.drawable.user))
                .into(holder.ivAvatar);
        holder.tvFollowersNumber.setText(mData.get(position).getNFollowers() + "");
        holder.tvFollowingNumber.setText(mData.get(position).getNFollowing() + "");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_username_user_list)
        TextView tvUserName;
        @BindView(R.id.iv_avatar_users_list)
        ImageView ivAvatar;
        @BindView(R.id.tv_followers_number_users_list)
        TextView tvFollowersNumber;
        @BindView(R.id.tv_following_number_users_list)
        TextView tvFollowingNumber;
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

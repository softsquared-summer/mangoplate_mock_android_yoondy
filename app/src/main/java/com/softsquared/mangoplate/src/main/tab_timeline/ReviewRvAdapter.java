package com.softsquared.mangoplate.src.main.tab_timeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.tab_timeline.models.ImageInfo;
import com.softsquared.mangoplate.src.main.tab_timeline.models.ReviewInfo;

import java.util.ArrayList;

class ReviewRvAdapter extends RecyclerView.Adapter<ReviewRvAdapter.ReviewViewHolder> {
    private ArrayList<ReviewInfo> reviewInfoList = new ArrayList<>();

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(position, reviewInfoList.get(position));
    }

    @Override
    public int getItemCount() { return reviewInfoList.size(); }

    public void add(ReviewInfo reviewInfo) { reviewInfoList.add(reviewInfo); }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTrophy;
        ImageView ivSeparatorTop, ivSeparatorBottom, ivSeparatorStart, ivSeparatorEnd;
        TextView tvReviewOfTodayPhrase1, tvReviewOfTodayPhrase2;

        CircularImageView ivProfilePhoto;
        TextView tvNickname;
        TextView tvIcReviewCount, tvIcFollowerCount;
        ImageView ivIcDeliciousMark;
        TextView tvIcDeliciousMark;
        TextView tvRestaurantNameAndLocation;

        TextView tvContent;
        RecyclerView rvPhoto;
        TextView tvLikeCount, tvReplyCount, tvDate;

        ConstraintLayout clWishBtn;
        ImageView ivWishBtn;
        ConstraintLayout clLikeBtn;
        ImageView ivLikeBtn;
        ConstraintLayout clReplyBtn;
        ConstraintLayout clMoreInfoBtn;

        boolean isWish = false;
        boolean isLike = false;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTrophy = itemView.findViewById(R.id.review_iv_trophy_mark);
            ivSeparatorTop = itemView.findViewById(R.id.review_iv_separator_top);
            ivSeparatorBottom = itemView.findViewById(R.id.review_iv_separator_bottom);
            ivSeparatorStart = itemView.findViewById(R.id.review_iv_separator_start);
            ivSeparatorEnd = itemView.findViewById(R.id.review_iv_separator_end);
            tvReviewOfTodayPhrase1 = itemView.findViewById(R.id.review_tv_review_of_today_phrase1);
            tvReviewOfTodayPhrase2 = itemView.findViewById(R.id.review_tv_review_of_today_phrase2);

            ivProfilePhoto = itemView.findViewById(R.id.review_iv_profile_photo);
            tvNickname = itemView.findViewById(R.id.review_tv_nickname);
            tvIcReviewCount = itemView.findViewById(R.id.review_tv_review_count);
            tvIcFollowerCount = itemView.findViewById(R.id.review_tv_follower_count);
            ivIcDeliciousMark = itemView.findViewById(R.id.review_iv_ic_delicious_mark);
            tvIcDeliciousMark = itemView.findViewById(R.id.review_tv_delicious_mark);
            tvRestaurantNameAndLocation = itemView.findViewById(R.id.review_tv_restaurant_name_and_location);

            tvContent = itemView.findViewById(R.id.review_tv_content);
            rvPhoto = itemView.findViewById(R.id.review_rv_photo);
            tvLikeCount = itemView.findViewById(R.id.review_tv_like_count);
            tvReplyCount = itemView.findViewById(R.id.review_tv_reply_count);
            tvDate = itemView.findViewById(R.id.review_tv_date);

            clWishBtn = itemView.findViewById(R.id.review_const_layout_wish_btn);
            ivWishBtn = itemView.findViewById(R.id.review_iv_wish_btn);
            clLikeBtn = itemView.findViewById(R.id.review_const_layout_like_btn);
            ivLikeBtn = itemView.findViewById(R.id.review_iv_like_btn);
            clReplyBtn = itemView.findViewById(R.id.review_const_layout_reply_btn);
            clMoreInfoBtn = itemView.findViewById(R.id.review_iv_more_info_btn);
        }

        public void bind(int position, ReviewInfo reviewInfo) {
            if(position > 0) {
                ivTrophy.setVisibility(View.GONE);
                ivSeparatorTop.setVisibility(View.INVISIBLE);
                ivSeparatorBottom.setVisibility(View.INVISIBLE);
                ivSeparatorStart.setVisibility(View.INVISIBLE);
                ivSeparatorEnd.setVisibility(View.INVISIBLE);
                tvReviewOfTodayPhrase1.setVisibility(View.GONE);
                tvReviewOfTodayPhrase2.setVisibility(View.GONE);
            }

            Glide.with(itemView.getContext()).load(reviewInfo.getProfileUrl()).into(ivProfilePhoto);
            ivProfilePhoto.setCircleColor(0);

            tvNickname.setText(reviewInfo.getName());
            tvIcReviewCount.setText(reviewInfo.getReviewNum());
            tvIcFollowerCount.setText(reviewInfo.getFollowerNum());
            String reviewType = reviewInfo.getReview();
            switch (reviewType) {
                case "맛있다!": {
                    ivIcDeliciousMark.setImageResource(R.drawable.ic_yum_orange);
                    break;
                }
                case "괜찮다": {
                    ivIcDeliciousMark.setImageResource(R.drawable.ic_slightly_smiling_face_orange);
                    break;
                }
                case "별로": {
                    ivIcDeliciousMark.setImageResource(R.drawable.ic_slightly_frowning_face_orange);
                    break;
                }
            }
            tvIcDeliciousMark.setText(reviewType);
            String restaurantNameAndLocation =  "@@ " + reviewInfo.getRestaurantName() + " - " + reviewInfo.getRestaurantArea();
            tvRestaurantNameAndLocation.setText(restaurantNameAndLocation);

            tvContent.setText(reviewInfo.getContent());

            PhotoRvAdapter photoRvAdapter = new PhotoRvAdapter();
            rvPhoto.setHasFixedSize(true);
            rvPhoto.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
            rvPhoto.setAdapter(photoRvAdapter);

            for(ImageInfo imageInfo : reviewInfo.getImages())
                photoRvAdapter.add(imageInfo.getImageUrl());

            // TODO: API is not ready yet
            tvLikeCount.setText("33");
            tvReplyCount.setText("2");

            tvDate.setText(reviewInfo.getCreatedAt());

            isWish = reviewInfo.getUserStar().equals("YES");

            clWishBtn.setOnClickListener(v -> {
                ivWishBtn.setImageResource(isWish ?
                        R.drawable.ic_star_unfilled_gray : R.drawable.ic_star_filled_orange);

                // TODO: use API 11-1

                isWish = !isWish;
            });
            clLikeBtn.setOnClickListener(v -> {
                ivLikeBtn.setImageResource(isLike ?
                        R.drawable.ic_heart_unfilled_gray : R.drawable.ic_heart_filled_orange);

                isLike = !isLike;
            });
            clReplyBtn.setOnClickListener(v -> Toast.makeText(itemView.getContext(),
                    itemView.getResources().getString(R.string.notify_not_prepared),
                    Toast.LENGTH_LONG).show());
            clMoreInfoBtn.setOnClickListener(v -> Toast.makeText(itemView.getContext(),
                    itemView.getResources().getString(R.string.notify_not_prepared),
                    Toast.LENGTH_LONG).show());
        }
    }
}

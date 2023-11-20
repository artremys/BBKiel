package com.example.bbkiel.ui.project;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.bbkiel.R;
import com.example.bbkiel.model.Comment;
import java.util.ArrayList;
import java.util.List;


//Die Kommentare werden in einem RecyclerView dargestellt. Ein Recyclerview ist eine Liste von view_items. In der Klasse CommentAdapter wird der Adapter
//für diesen RecyclerView implementiert.
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private List<Comment> commentList = new ArrayList<>();
    private final CommentAdapter.ListItemClickListener onClickListener;
    private int likeCounter;
    private boolean liked;
    public CommentAdapter(CommentAdapter.ListItemClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    //
    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view_item,parent,false);
        return new  CommentHolder(itemView);
    }


    //
    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position){

        Comment currentComment = commentList.get(position);
        likeCounter = currentComment.getCachedVotesUp();
        liked = false;
        holder.textViewUserName.setText(currentComment.getUserName());
        holder.textViewBody.setText(currentComment.getBody());
        holder.textViewCommentLikeCounter.setText(String.valueOf(currentComment.getCachedVotesUp()));
        holder.comment_like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(liked==false) {
                    currentComment.setCachedVotesUp(likeCounter++);
                    holder.textViewCommentLikeCounter.setText(String.valueOf(currentComment.getCachedVotesUp()));
                    liked = true;
                }
            }
        });
    }
    @Override
    public int getItemCount(){return this.commentList.size();}

    public void setComments(List<Comment> commentList){
        this.commentList = commentList;
        notifyDataSetChanged();
    }

// CommentHolder beschreibt ein comment_view_item. Jede CommentHolder Instanz enthält Verweise auf die TextView elemente und den Like und Dislike button.
public class CommentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewUserName;
        private TextView textViewBody;
        private TextView textViewCommentLikeCounter;
        private ImageButton comment_like_button;
   //     private Button buttonLikes;

        public CommentHolder(@NonNull View itemView){
            super(itemView);
            this.textViewUserName = itemView.findViewById(R.id.author_comment);
            this.textViewBody = itemView.findViewById(R.id.comment_body);
            this.textViewCommentLikeCounter = itemView.findViewById(R.id.comment_like_counter);
            this.comment_like_button = itemView.findViewById(R.id.like_comment);
      //      this.buttonLikes = itemView.findViewById(R.id.like_comment);
            itemView.setOnClickListener(this);


        }


    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        onClickListener.onListItemClick(position);
    }
}




    public interface ListItemClickListener {
        void onListItemClick(int postiion);
    }
}

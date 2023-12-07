package com.demo.roomdatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserNotes;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private final Context context;
    private final ArrayList<UserNotes> list;

    public RecyclerAdapter(Context context, ArrayList<UserNotes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.textViewName.setText(list.get(position).getUserName());
        holder.textViewUserNotes.setText(list.get(position).getUserNotes());
        holder.deleteBtn.setVisibility(View.VISIBLE);

        if (list.get(position).getBookmarkItem()) {
            holder.bookmarkBtn.setVisibility(View.GONE);
            holder.bookmarkBtnGreen.setVisibility(View.VISIBLE);

        } else {
            holder.bookmarkBtn.setVisibility(View.VISIBLE);
            holder.bookmarkBtnGreen.setVisibility(View.GONE);
        }

        holder.deleteBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete").setMessage("Are you sure").setPositiveButton("Yes", (dialog, which) -> {
                UserDatabase.getDatabase(context).userNotesDao().deleteUser(list.get(position));
                Toast.makeText(context, "User Deleted successfully", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("No", (dialog, which) -> {

            });
            builder.show();

        });

        holder.bookmarkBtn.setOnClickListener(v -> {
            UserDatabase.getDatabase(context).userNotesDao().updateBookmark(true, list.get(position).getId());
            Toast.makeText(context, "Bookmark Added successfully", Toast.LENGTH_SHORT).show();
        });

        holder.bookmarkBtnGreen.setOnClickListener(v -> {
            UserDatabase.getDatabase(context).userNotesDao().updateBookmark(false, list.get(position).getId());
            Toast.makeText(context, "Bookmark Remove successfully", Toast.LENGTH_SHORT).show();
        });


        holder.ViewItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserInputActivity.class);
            intent.putExtra("userId", list.get(position).getId());
            intent.putExtra("userPosition", position);
            intent.putExtra("bookmark",list.get(position).getBookmarkItem());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewUserNotes;
        ImageView deleteBtn, bookmarkBtn, bookmarkBtnGreen;

        LinearLayout ViewItem;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewUserName);
            textViewUserNotes = itemView.findViewById(R.id.textViewUserNotes);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            bookmarkBtn = itemView.findViewById(R.id.bookmark_btn);
            bookmarkBtnGreen = itemView.findViewById(R.id.bookmark_btn_green);
            ViewItem = itemView.findViewById(R.id.clickOnItem);
        }
    }
}

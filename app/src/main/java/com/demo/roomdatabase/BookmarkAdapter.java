package com.demo.roomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserNotes;
import java.util.ArrayList;

public class BookmarkAdapter  extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private final Context context;
    private final ArrayList<UserNotes> list;

    public BookmarkAdapter(Context context, ArrayList<UserNotes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        holder.textViewName.setText(list.get(position).getUserName());
        holder.textViewUserNotes.setText(list.get(position).getUserNotes());

        holder.deleteBtn.setOnClickListener(v -> {

            UserDatabase.getDatabase(context).userNotesDao().deleteUser(list.get(position));
            Toast.makeText(context, "User Deleted successfully", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder{


        TextView textViewName, textViewUserNotes;
        ImageView deleteBtn;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewUserName);
            textViewUserNotes = itemView.findViewById(R.id.textViewUserNotes);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }
    }
}

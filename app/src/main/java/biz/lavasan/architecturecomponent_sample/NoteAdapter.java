package biz.lavasan.architecturecomponent_sample;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldNote, @NonNull Note newNote) {
            return oldNote.getId() == newNote.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldNote, @NonNull Note newNote) {
            return oldNote.getTitle().equals(newNote.getTitle()) &&
                    oldNote.getDescription().equals(newNote.getDescription()) &&
                    oldNote.getPriority() == newNote.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note currentNote = getItem (i);
        noteHolder.tvTitle.setText(currentNote.getTitle());
        noteHolder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
        noteHolder.tvDescription.setText(currentNote.getDescription());

    }

//    public void setNotes(List<Note> notes) {
//
//        this.notes = notes;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvPriority;
        private TextView tvDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.text_view_title);
            tvPriority = itemView.findViewById(R.id.text_view_priority);
            tvDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

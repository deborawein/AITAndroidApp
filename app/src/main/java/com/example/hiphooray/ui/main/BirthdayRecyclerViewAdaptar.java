package com.example.hiphooray.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiphooray.databinding.FragmentRecyclerItemViewBinding;
import com.example.hiphooray.ui.database.BirthdayContact;
import com.example.hiphooray.ui.database.BirthdayContactDao;


public class BirthdayRecyclerViewAdaptar extends ListAdapter<BirthdayContact, BirthdayItemViewHolder>{

    private FragmentRecyclerItemViewBinding binding;
    private OnItemClickListener onItemClickListener;

    private BirthdayContact birthdayContact;


    protected BirthdayRecyclerViewAdaptar(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    private static final DiffUtil.ItemCallback<BirthdayContact> DIFF_CALLBACK = new DiffUtil.ItemCallback<BirthdayContact>() {
        @Override
        public boolean areItemsTheSame(@NonNull BirthdayContact oldItem, @NonNull BirthdayContact newItem) {
            return oldItem.getId() == newItem.getId();

        }

        @Override
        public boolean areContentsTheSame(@NonNull BirthdayContact oldItem, @NonNull BirthdayContact newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getBirthdate().equals(newItem.getBirthdate()) &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getPhone().equals(newItem.getPhone());
        }
    };



    @NonNull
    @Override
    public BirthdayItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = FragmentRecyclerItemViewBinding.inflate(inflater, parent, false);





       return new BirthdayItemViewHolder(binding);



    }



    @Override
    public void onBindViewHolder(@NonNull BirthdayItemViewHolder holder, int position) {
        BirthdayContact birthdayContact = getBirthdayContactAt(position);

        holder.loadItemBirthday(birthdayContact);
        holder.bind(birthdayContact, onItemClickListener);

    }

    public BirthdayContact getBirthdayContactAt(int position){
        return getItem(position);
    }



}

package com.example.hiphooray.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiphooray.R;
import com.example.hiphooray.databinding.FragmentRecyclerItemViewBinding;
import com.example.hiphooray.ui.database.BirthdayContact;


public class BirthdayItemViewHolder extends RecyclerView.ViewHolder{

    private FragmentRecyclerItemViewBinding binding;

    public BirthdayItemViewHolder(@NonNull FragmentRecyclerItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }



    public void loadItemBirthday(BirthdayContact birthdayContact){
    this.binding.nameBdayContactTextView.setText(birthdayContact.getName());
    this.binding.dateBdayTextView.setText(birthdayContact.displayBirthdate(birthdayContact.getBirthdate()));
    this.binding.actionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("CONTACT", birthdayContact);

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_birthdayFragment_to_messageBirthdayContactFragment, bundle);
        }
    });
    }

    public void bind(BirthdayContact birthdayContact, OnItemClickListener onItemClickListener){
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(birthdayContact, view);
            }
        });

    }


}

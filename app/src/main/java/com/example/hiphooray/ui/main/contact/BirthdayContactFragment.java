package com.example.hiphooray.ui.main.contact;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiphooray.R;
import com.example.hiphooray.databinding.FragmentBirthdayContactBinding;
import com.example.hiphooray.ui.database.BirthdayContact;


public class BirthdayContactFragment extends Fragment {

    private FragmentBirthdayContactBinding binding;
    private BirthdayContact birthdayContact;



    public BirthdayContactFragment() {
        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBirthdayContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("CONTACT")){
            birthdayContact = (BirthdayContact) getArguments().getSerializable("CONTACT");
            binding.nameContactTextView.setText(birthdayContact.getName());
            binding.birthdateContactTextView.setText(birthdayContact.displayBirthdate(birthdayContact.getBirthdate()));
            binding.emailContactTextView.setText(birthdayContact.getEmail());
            binding.mobileContactTextView.setText(birthdayContact.getPhone());
        }

        binding.editContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_birthdayContactFragment_to_updateBirthdayContactFragment, bundle);
            }
        });

        binding.messageContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_birthdayContactFragment_to_messageBirthdayContactFragment, bundle);
            }
        });
    }
}
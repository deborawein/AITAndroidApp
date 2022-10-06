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
import com.example.hiphooray.databinding.FragmentAddBirthdayContactBinding;
import com.example.hiphooray.ui.database.BirthdayContact;
import com.google.android.material.snackbar.Snackbar;


public class AddBirthdayContactFragment extends Fragment {

    private FragmentAddBirthdayContactBinding binding;

    private BirthdayContact birthdayContact;



    public AddBirthdayContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAddBirthdayContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nameNewContactEditText.getText().toString();
                if(name.trim().isEmpty()) {
                    Snackbar.make(view, "Name is required", Snackbar.LENGTH_SHORT).show();
                    binding.nameNewContactEditText.getText().clear();
                    binding.nameNewContactEditText.requestFocus();
                    return;
                }

                String birthday = binding.bdayNewContactEditText.getText().toString();
                if(birthday.trim().isEmpty()){
                    Snackbar.make(view, "Birthday is required", Snackbar.LENGTH_SHORT).show();
                    binding.bdayNewContactEditText.getText().clear();
                    binding.bdayNewContactEditText.requestFocus();
                    return;
                }

                String email = binding.emailNewContactEditText.getText().toString();
                String phone = binding.mobileNewContactEditText.getText().toString();

                birthdayContact = new BirthdayContact();
                birthdayContact.setName(name);
                birthdayContact.setBirthdate(birthday);
                birthdayContact.setEmail(email);
                birthdayContact.setPhone(phone);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ADD_CONTACT", birthdayContact);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_addBirthdayContactFragment_to_birthdayFragment, bundle);
                Snackbar.make(view, birthdayContact.getName()+" added", Snackbar.LENGTH_SHORT).show();

            }
        });
    }
}
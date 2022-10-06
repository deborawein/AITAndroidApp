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
import com.example.hiphooray.databinding.FragmentUpdateBirthdayContactBinding;
import com.example.hiphooray.ui.database.BirthdayContact;
import com.google.android.material.snackbar.Snackbar;


public class UpdateBirthdayContactFragment extends Fragment {

    public FragmentUpdateBirthdayContactBinding binding;

    private BirthdayContact birthdayContact;

    public UpdateBirthdayContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBirthdayContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("CONTACT")) {
            birthdayContact = (BirthdayContact) getArguments().getSerializable("CONTACT");
            binding.nameUpdateContactTextView.setText(birthdayContact.getName());
            binding.birthdateUpdateContactTextView.setText(birthdayContact.displayCompletedBirthdate(birthdayContact.getBirthdate()));
            binding.emailUpdateContactTextView.setText(birthdayContact.getEmail());
            binding.mobileUpdateContactTextView.setText(birthdayContact.getPhone());
        }

        binding.updateContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.nameUpdateContactTextView.getText().toString();
                String birthdate = binding.birthdateUpdateContactTextView.getText().toString();
                String email = binding.emailUpdateContactTextView.getText().toString();
                String mobile = binding.mobileUpdateContactTextView.getText().toString();

                Bundle modifiedBundle = new Bundle();
                birthdayContact.setName(name);
                birthdayContact.setBirthdate(birthdate);
                birthdayContact.setEmail(email);
                birthdayContact.setPhone(mobile);
                modifiedBundle.putSerializable("CONTACT", birthdayContact);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_updateBirthdayContactFragment_to_birthdayFragment, modifiedBundle);

            }
        });

        binding.deleteContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("DELETE_CONTACT", birthdayContact);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_updateBirthdayContactFragment_to_birthdayFragment, bundle);
                Snackbar.make(view, birthdayContact.getName()+" deleted.", Snackbar.LENGTH_SHORT).show();

            }
        });
    }
}

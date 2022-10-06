package com.example.hiphooray.ui.main.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiphooray.databinding.FragmentMessageBirthdayContactBinding;
import com.example.hiphooray.ui.database.BirthdayContact;


public class MessageBirthdayContactFragment extends Fragment {

    public FragmentMessageBirthdayContactBinding binding;

    private BirthdayContact birthdayContact;


    public MessageBirthdayContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessageBirthdayContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("CONTACT")) {
            birthdayContact = (BirthdayContact) getArguments().getSerializable("CONTACT");
            binding.nameMessageTextView.setText(birthdayContact.getName());
            binding.birthdateMessageTextView.setText(birthdayContact.displayBirthdate(birthdayContact.getBirthdate()));
            String phone = birthdayContact.getPhone();

            binding.sendMessageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String message = binding.messageTextView.getText().toString();

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null));
                    intent.putExtra("sms_body", message);
                    startActivity(intent);


//                        try {
//                            SmsManager smsManager=SmsManager.getDefault();
//                            smsManager.sendTextMessage(phone, null, message, null, null);
//                            Snackbar.make(view,"Message sent to " + birthdayContact.getName() + ".", Snackbar.LENGTH_LONG).show();
//                            NavController navController = Navigation.findNavController(view);
//                            navController.navigate(R.id.action_messageBirthdayContactFragment_to_birthdayFragment);
//                        }catch (Exception e)
//                        {
//                            Snackbar.make(view,"Message failed to send.",Snackbar.LENGTH_LONG).show();
//                        }


                }
            });
        }
    }
}
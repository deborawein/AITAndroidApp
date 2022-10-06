package com.example.hiphooray.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiphooray.R;
import com.example.hiphooray.databinding.FragmentBirthdayBinding;
import com.example.hiphooray.ui.database.BirthdayContact;

import java.util.List;

public class BirthdayFragment extends Fragment implements OnItemClickListener{

    public static BirthdayFragment newInstance() {
        return new BirthdayFragment();
    }

    private FragmentBirthdayBinding binding;
    private BirthdayViewModel birthdayViewModel;

    private BirthdayItemViewHolder birthdayViewHolder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBirthdayBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        birthdayViewModel = new ViewModelProvider(this).get(BirthdayViewModel.class);

//BUNDLE TO PASS VALUES
        NavController navController = Navigation.findNavController(view);
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("ADD_CONTACT")){
            BirthdayContact birthdayContact = (BirthdayContact) getArguments().getSerializable("ADD_CONTACT");
            birthdayViewModel.insert(birthdayContact);
        }
        if(bundle != null && bundle.containsKey("CONTACT")){
            BirthdayContact birthdayContact = (BirthdayContact) getArguments().getSerializable("CONTACT");
            birthdayViewModel.update(birthdayContact);
        }
        if(bundle != null && bundle.containsKey("DELETE_CONTACT")){
            BirthdayContact birthdayContact = (BirthdayContact) getArguments().getSerializable("DELETE_CONTACT");
            birthdayViewModel.delete(birthdayContact);
        }

//SET RECYCLER VIEW ADAPTER
        //set layout
        binding.birthdaysRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.birthdaysRecycleView.setHasFixedSize(true);

        //set adapter
        BirthdayRecyclerViewAdaptar adapter = new BirthdayRecyclerViewAdaptar(this);
        binding.birthdaysRecycleView.setAdapter(adapter);

        //main observer
        final Observer<List<BirthdayContact>> allBirthdaysObserver = new Observer<List<BirthdayContact>>() {
            @Override
            public void onChanged(List<BirthdayContact> birthdays) {
                adapter.submitList(birthdays);

                //getCount by .size()
                //binding.allContactsQtdTextView.setText("" + birthdays.size());
            }
        };
        birthdayViewModel.getAllBirthdays().observe(getViewLifecycleOwner(), allBirthdaysObserver);

//FLOATING BUTTON

        //onClick
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_birthdayFragment_to_addBirthdayContactFragment);
            }
        });

//ALLCONTACTS CARDVIEW

        //getCount
        birthdayViewModel.getCountAll().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                binding.allContactsQtdTextView.setText(String.valueOf(integer));


            }
        });

        //onClick
        binding.allContactsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        final Observer<List<BirthdayContact>> allBirthdaysObserver = new Observer<List<BirthdayContact>>() {
            @Override
            public void onChanged(List<BirthdayContact> birthdays) {
                adapter.submitList(birthdays);
            }
        };
        birthdayViewModel.getAllBirthdays().observe(getViewLifecycleOwner(), allBirthdaysObserver);
            }
        });


//UPCOMING CARDVIEW

        // getCount
        birthdayViewModel.getCountUpcoming().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.upcomingQtdTextView.setText(String.valueOf(integer));
            }
        });

        //onClick
        binding.upcomingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Observer<List<BirthdayContact>> upcomingBirthdaysObserver = new Observer<List<BirthdayContact>>() {
                    @Override
                    public void onChanged(List<BirthdayContact> birthdays) {
                        adapter.submitList(birthdays);
                    }
                };
                birthdayViewModel.getUpcomingBirthdays().observe(getViewLifecycleOwner(), upcomingBirthdaysObserver);
            }
        });

//MISSED CARDVIEW

        //getCount
        birthdayViewModel.getCountMissed().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.missedQtdTextView.setText(String.valueOf(integer));
            }
        });

        //onClick
        binding.missedCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Observer<List<BirthdayContact>> missedBirthdaysObserver = new Observer<List<BirthdayContact>>() {
                    @Override
                    public void onChanged(List<BirthdayContact> birthdays) {
                        adapter.submitList(birthdays);
                    }
                };
                birthdayViewModel.getMissedBirthdays().observe(getViewLifecycleOwner(), missedBirthdaysObserver);
            }
        });

// TODAY CARD VIEW

        //getCount
        birthdayViewModel.getCountToday().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.todayQtdTextView.setText(String.valueOf(integer));
            }
        });

        //onClick
        binding.todayCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Observer<List<BirthdayContact>> todayBirthdaysObserver = new Observer<List<BirthdayContact>>() {
                    @Override
                    public void onChanged(List<BirthdayContact> birthdays) {
                        adapter.submitList(birthdays);
                    }
                };
                birthdayViewModel.getTodayBirthdays().observe(getViewLifecycleOwner(), todayBirthdaysObserver);
            }
        });
    }

    @Override
    //Items RecyclerView onClick
    public void onClick(BirthdayContact birthdayContact, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("CONTACT", birthdayContact);
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_birthdayFragment_to_birthdayContactFragment, bundle);
    }
}
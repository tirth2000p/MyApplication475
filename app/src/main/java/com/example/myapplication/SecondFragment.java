package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private String spin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fragment locked in portrait screen orientation
        }



        @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        Button button = view.findViewById(R.id.button);//


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);

        spin = spinner.getSelectedItem().toString();

        spin = spin.substring(0,1);
        System.out.println(spin);



        // directs to gameactivity (blank fragsment is empty
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int var = Integer.parseInt(spin);
                System.out.println(var);

                //String spin = spinner.getSelectedItem().toString();

                //System.out.println(spin);

                // good from here
                //var = 2;
                Intent i = new Intent(getActivity(), GameActivity.class);
                i.putExtra("var", var);
                startActivity(i);

            }
        });


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_firstFragment);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
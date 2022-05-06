package com.example.miprimerabd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.miprimerabd.data.Persona;
import com.example.miprimerabd.data.PersonaDBHelper;
import com.example.miprimerabd.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private PersonaDBHelper mPersonaDBHelper;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mPersonaDBHelper = new PersonaDBHelper(getActivity());
        binding = FragmentFirstBinding.inflate( inflater, container, false );
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        EditText id = (EditText) view.findViewById( R.id.editTextNumber );
        EditText nombre = (EditText) view.findViewById( R.id.editTextTextPersonName2 );
        binding.buttonFirst.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona miPrimerPersona = new Persona(id.getText().toString(),nombre.getText().toString());
                mPersonaDBHelper.savePersona( miPrimerPersona );
                NavHostFragment.findNavController( FirstFragment.this )
                        .navigate( R.id.action_FirstFragment_to_SecondFragment );
            }
        } );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
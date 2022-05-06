package com.example.miprimerabd;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.miprimerabd.data.Persona;
import com.example.miprimerabd.data.PersonaDBHelper;
import com.example.miprimerabd.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private PersonaDBHelper mPersonaDBHelper;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mPersonaDBHelper = new PersonaDBHelper(getActivity());
        binding = FragmentSecondBinding.inflate( inflater, container, false );
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        TextView textoSalida = (TextView) view.findViewById( R.id.textview_second );
        Cursor cursor = mPersonaDBHelper.getAllPersons();
        if (cursor != null && cursor.moveToLast()) {
            Persona miPrimeraPersona = new Persona( cursor );
            textoSalida.setText( miPrimeraPersona.getName() );
        }
        binding.buttonSecond.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController( SecondFragment.this )
                        .navigate( R.id.action_SecondFragment_to_FirstFragment );
            }
        } );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
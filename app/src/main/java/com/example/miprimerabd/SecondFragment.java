package com.example.miprimerabd;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miprimerabd.data.Persona;
import com.example.miprimerabd.data.PersonaDBHelper;
import com.example.miprimerabd.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements PersonaAdapter.OnItemClickListener{

    private FragmentSecondBinding binding;
    private PersonaDBHelper mPersonaDBHelper;
    private RecyclerView listaPersonas;
    private LinearLayoutManager linearLayoutManager;
    private PersonaAdapter adaptador;

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
        listaPersonas = (RecyclerView) view.findViewById( R.id.listaPersonas );
        listaPersonas.setHasFixedSize( true );
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        listaPersonas.setLayoutManager( linearLayoutManager );
        adaptador = new PersonaAdapter( this.getContext(), this );
        listaPersonas.setAdapter( adaptador );
        // Iniciar loader
        cargarPersonas();

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

    public void cargarPersonas(){
        new PersonasLoadTask().execute();
    }

    @Override
    public void onClick(PersonaAdapter.ViewHolder holder, String idPersona) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.getContext(), idPersona, duration);
        toast.show();
    }

    private class PersonasLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mPersonaDBHelper.getAllPersons();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                adaptador.swapCursor(cursor);
            }else {
                // Mostrar empty state
            }
        }
    }

}
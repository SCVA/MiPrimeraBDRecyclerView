package com.example.miprimerabd;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miprimerabd.data.Persona;


public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder>{

    private final Context contexto;
    private OnItemClickListener escucha;
    private Cursor items;

    interface OnItemClickListener {
        public void onClick(ViewHolder holder, String idPersona);
    }

    public PersonaAdapter(Context contexto,OnItemClickListener escucha){
        this.contexto = contexto;
        this.escucha = escucha;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.persona_item_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items.moveToPosition( position );
        Persona miPersona = new Persona( items );
        String s;

        // Asignación UI
        s = miPersona.getName();
        holder.nombre.setText( s );

        s = miPersona.getId();
        holder.identificacion.setText( s );
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.getCount();
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            items = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    public Cursor getItems() {
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView nombre;
        public TextView identificacion;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById( R.id.name );
            identificacion = (TextView) v.findViewById( R.id.identificacion );
            v.setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            escucha.onClick( this, obtenerIdPersona(getAdapterPosition()) );
        }
    }

    private String obtenerIdPersona(int posicion) {
        if (items != null) {
            if (items.moveToPosition(posicion)) {
                Persona miPersona = new Persona( items );
                return miPersona.getId();
            }else{
                return "-1";
            }
        }else{
            return "-1";
        }
    }

}

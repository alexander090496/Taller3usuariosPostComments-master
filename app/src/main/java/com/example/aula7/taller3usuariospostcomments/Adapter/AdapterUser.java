package com.example.aula7.taller3usuariospostcomments.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aula7.taller3usuariospostcomments.MainActivity2;
import com.example.aula7.taller3usuariospostcomments.Model.Usermodel;
import com.example.aula7.taller3usuariospostcomments.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/10/2017.
 */

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {


    List<Usermodel> usermodelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public AdapterUser(List<Usermodel> usermodelList, Context context) {
        this.usermodelList = usermodelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes


        holder.textViewuserid.setText(Integer.toString(usermodelList.get(position).getId()));
        holder.textViewuname.setText(usermodelList.get(position).getName());
        holder.textViewusername.setText(usermodelList.get(position).getUsername());
        holder.textViewuseraddress.setText(usermodelList.get(position).getAddress());
        holder.textViewusercompany.setText(usermodelList.get(position).getCompany());

    }

    @Override
    public int getItemCount() {
        return usermodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewuserid;
        TextView textViewusername;
        TextView textViewuname;
        TextView textViewuseraddress;
        TextView textViewusercompany;
        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);
            textViewuserid = (TextView) item.findViewById(R.id.id_item_id);
            textViewuname = (TextView) item.findViewById(R.id.id_item_name);
            textViewusername = (TextView) item.findViewById(R.id.id_item_usermane);
            textViewuseraddress = (TextView) item.findViewById(R.id.id_item_address);
            textViewusercompany = (TextView) item.findViewById(R.id.id_item_company);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("idUser", usermodelList.get(getLayoutPosition()).getId());

            contextItem.startActivity(intent);
            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }


}

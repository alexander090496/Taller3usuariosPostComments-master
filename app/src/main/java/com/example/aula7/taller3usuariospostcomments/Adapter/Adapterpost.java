package com.example.aula7.taller3usuariospostcomments.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aula7.taller3usuariospostcomments.MainActivity3;
import com.example.aula7.taller3usuariospostcomments.Model.Postmodel;
import com.example.aula7.taller3usuariospostcomments.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 14/10/2017.
 */

public class Adapterpost extends RecyclerView.Adapter<Adapterpost.ViewHolder>{
    List<Postmodel> postmodelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public Adapterpost(List<Postmodel> postmodelList, Context context) {
        this.postmodelList = postmodelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.textViewpostuserId.setText(Integer.toString(postmodelList.get(position).getUserId()));
        holder.textViewpostid.setText(Integer.toString(postmodelList.get(position).getId()));
        holder.textViewposttitle.setText(postmodelList.get(position).getTitle());
        holder.textViewpostbody.setText(postmodelList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return postmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewpostid;
        TextView textViewpostuserId;
        TextView textViewposttitle;
        TextView textViewpostbody;

        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);
            textViewpostid = (TextView) item.findViewById(R.id.id_tv_post_id);
            textViewpostuserId = (TextView) item.findViewById(R.id.id_tv_user_id);
            textViewposttitle = (TextView) item.findViewById(R.id.id_tv_post_title);
            textViewpostbody = (TextView) item.findViewById(R.id.id_tv_post_body);

        }
        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, MainActivity3.class);
            intent.putExtra("idPost", postmodelList.get(getLayoutPosition()).getId());

            contextItem.startActivity(intent);
            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }


}

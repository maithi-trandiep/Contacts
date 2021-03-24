package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList contact_id, contact_nom, contact_prenom, contact_telephone;

    CustomAdapter(Activity activity, Context context, ArrayList contact_id, ArrayList contact_nom, ArrayList contact_prenom,
                  ArrayList contact_telephone){
        this.activity = activity;
        this.context = context;
        this.contact_id = contact_id;
        this.contact_nom = contact_nom;
        this.contact_prenom = contact_prenom;
        this.contact_telephone = contact_telephone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.contact_id_txt.setText(String.valueOf(contact_id.get(position)));
        holder.contact_nom_txt.setText(String.valueOf(contact_nom.get(position)));
        holder.contact_prenom_txt.setText(String.valueOf(contact_prenom.get(position)));
        holder.contact_telephone_txt.setText(String.valueOf(contact_telephone.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("nom", String.valueOf(contact_nom.get(position)));
                intent.putExtra("prenom", String.valueOf(contact_prenom.get(position)));
                intent.putExtra("telephone", String.valueOf(contact_telephone.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contact_id_txt, contact_nom_txt, contact_prenom_txt, contact_telephone_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_id_txt = itemView.findViewById(R.id.contact_id_txt);
            contact_nom_txt = itemView.findViewById(R.id.contact_nom_txt);
            contact_prenom_txt = itemView.findViewById(R.id.contact_prenom_txt);
            contact_telephone_txt = itemView.findViewById(R.id.contact_telephone_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

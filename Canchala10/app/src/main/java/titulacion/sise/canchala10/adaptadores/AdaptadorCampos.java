package titulacion.sise.canchala10.adaptadores;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.entidades.Horario;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.Utils.CircleTransform;
import titulacion.sise.canchala10.entidades.Campo;

/**
 * Created by yzeballos on 16/02/2018.
 */

public class AdaptadorCampos extends
        RecyclerView.Adapter<AdaptadorCampos.CampoViewHolder> implements View.OnClickListener{

    List<Campo> campos;
    private View.OnClickListener listener;
    Context context;
    int  pos   ;
    AdaptadorHorarios adaptadorHorarios;
    private GridLayoutManager glm;
    public AdaptadorCampos( List<Campo> campos){
        this.campos = campos;
    }

    @Override
    public CampoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.campo_item, null, false);
        view.setOnClickListener(this);
        context = view.getContext();
        return new CampoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampoViewHolder holder, int position) {
        pos = position;
        holder.tvDescripcion.setText(campos.get(position).getDescripcion());
        Picasso.with(context).load(ApiUtils.imgUrl + campos.get(position).getImagen()).transform(new CircleTransform(100,10)).into(holder.ivImagen);

        glm = new GridLayoutManager(context, 2);
        holder.recyclerHorarios.setLayoutManager(glm);

        holder.recyclerHorarios.setLayoutManager(new LinearLayoutManager(context));
        adaptadorHorarios = new AdaptadorHorarios(CargarHorarios());
        glm = new GridLayoutManager(context, 2);
        holder.recyclerHorarios.setLayoutManager(glm);
        holder.recyclerHorarios.setAdapter(adaptadorHorarios);

    }

    public List<Horario> CargarHorarios(){
        List<Horario> horarios = new ArrayList<Horario>();
        Horario horario;

        String[] horas = {
                "08:00 - 09:00",
                "09:00 - 10:00",
                "10:00 - 11:00",
                "11:00 - 12:00",
                "12:00 - 13:00",
                "13:00 - 14:00",
                "14:00 - 15:00",
                "15:00 - 16:00",
                "16:00 - 17:00",
                "17:00 - 18:00",
                "18:00 - 19:00",
                "19:00 - 20:00",
                "20:00 - 21:00",
                "21:00 - 22:00",
                "22:00 - 23:00",
                "23:00 - 24:00"
        };

        for(int i = 0; i  < horas.length; i ++ ){
            horario = new Horario();
            horario.setId(i + 1);
            horario.setdDescripcion(horas[i]);
            horarios.add(horario);
        }
        return  horarios;
    }

    @Override
    public int getItemCount() {
        return campos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class CampoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvDescripcion;
        ImageView ivImagen;
        RecyclerView recyclerHorarios;
        Button btnReservar;

        public CampoViewHolder(View itemView) {
            super(itemView);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            ivImagen = (ImageView)itemView.findViewById(R.id.tvImagen);
            recyclerHorarios = (RecyclerView) itemView.findViewById(R.id.recyclerHorarios);
            btnReservar = (Button)itemView.findViewById(R.id.btnReserva);
            btnReservar.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (view.getId() == btnReservar.getId()){
                int count = recyclerHorarios.getAdapter().getItemCount();
                List<Integer> data = new ArrayList<Integer>() ;
                String sss = "";
                for (int i = 0; i < count; i++){
                    ViewGroup row = (ViewGroup) recyclerHorarios.getChildAt(i);
                    CheckBox tvTest = (CheckBox) row.findViewById(R.id.chkHorario);
                    boolean v = tvTest.isChecked();

                    if(v){
                        data.add(i);
                    }

                }

                for (int i = 0; i < data.size(); i++){
                    sss = sss + adaptadorHorarios.horarios.get(data.get(i)).getDescripcion() + " - ";
                }

                Toast.makeText(view.getContext(), sss + campos.get(getAdapterPosition())
                        .getDescripcion(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}

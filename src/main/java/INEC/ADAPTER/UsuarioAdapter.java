package INEC.ADAPTER;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.josuevu.myapplication.R;

import java.util.List;

import INEC.MODEL.Usuario;

/**
 * Created by ankit on 27/10/17.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    private Context context;
    private List<Usuario> list;

    public UsuarioAdapter(Context context, List<Usuario> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.opcion_mostrar, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Usuario movie = list.get(position);

        holder.textTitle.setText(movie.getCodigo_Usuario());
        holder.textRating.setText(String.valueOf(movie.getApellido1_Usuario()));
        holder.textYear.setText(String.valueOf(movie.getApellido2_Usuario()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textRating, textYear;

        public ViewHolder(View itemView) {
            super(itemView);

            /*textTitle = itemView.findViewById(R.id.main_title);
            textRating = itemView.findViewById(R.id.main_rating);
            textYear = itemView.findViewById(R.id.main_year);*/
        }
    }

}
package INEC.ADAPTER;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josuevu.myapplication.R;

import java.util.ArrayList;

public class GridViewImageUsuario extends BaseAdapter {
    private Context mContext;
    private final ArrayList<String> names;
    private final ArrayList<Bitmap> bitmaps;
    //private final String[] ids;
    public GridViewImageUsuario(Context c, ArrayList<String> names, ArrayList<Bitmap> bitmaps) {
        mContext = c;
        this.bitmaps = bitmaps;
        this.names = names;
        //this.ids = Ids;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return names.size();
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return bitmaps.get(position);
    }

    @Override
    public long  getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.grid_item_layout, null);
        } else {
            grid = (View) convertView;
        }
        //TextView textView_id = (TextView) grid.findViewById(R.id.grid_id);
        TextView textView = (TextView) grid.findViewById(R.id.grid_text);
        ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
        //textView_id.setText(ids[position]);
        textView.setText(names.get(position));
        imageView.setImageBitmap(bitmaps.get(position));
        return grid;
    }
}

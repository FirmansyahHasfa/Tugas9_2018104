package com.example.tugas4;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    String data1[], data2[], data3[];
    int images[];
    Context context;
    public HomeAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }
    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pil_barang, parent, false);
        return new HomeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull
                                         HomeAdapter.HomeViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);
    }
    @Override
    public int getItemCount() {
        return images.length;
    }
    public class HomeViewHolder extends
            RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView myImage;
        ConstraintLayout mainLayout;
        public HomeViewHolder(View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myImage = itemView.findViewById(R.id.myImages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

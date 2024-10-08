package fr.iutlan.xmen;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.iutlan.xmen.databinding.XMenBinding;
import java.util.List;

public class XMenAdapter extends RecyclerView.Adapter<XMenViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;
    private final List<XMen> liste;

    public XMenAdapter(List<XMen> liste) {
        this.liste = liste;
    }

    @NonNull
    @Override
    public XMenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        XMenBinding ui = XMenBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new XMenViewHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull XMenViewHolder holder, int position) {
        holder.setXMen(liste.get(position));
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}


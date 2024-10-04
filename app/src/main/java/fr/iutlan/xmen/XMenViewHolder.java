package fr.iutlan.xmen;


import android.view.ContextMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.iutlan.xmen.databinding.XMenBinding;

public class XMenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final XMenBinding ui;

    public static final int MENU_EDIT = 1;
    public static final int MENU_DELETE = 2;

    private XMenAdapter.OnItemClickListener onItemClickListener;

    public XMenViewHolder(@NonNull XMenBinding ui) {
        super(ui.getRoot());
        this.ui = ui;
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this::onCreateContextMenu);

    }



    public void setOnItemClickListener(XMenAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setXMen(XMen xmen) {
        ui.nom.setText(xmen.getNom());
        ui.alias.setText(xmen.getAlias());
        ui.image.setImageResource(xmen.getIdImage());
        ui.description.setText(xmen.getDescription());
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(getAdapterPosition());
    }



    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int position = getAdapterPosition();
        menu.setHeaderTitle(ui.nom.getText());
        menu.add(0, MENU_EDIT, position, "Edit");
        menu.add(0, MENU_DELETE, position, "Delete");
    }

}

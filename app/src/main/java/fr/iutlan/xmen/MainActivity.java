package fr.iutlan.xmen;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.iutlan.xmen.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> editLauncher;
    private ActivityMainBinding ui;
    private List<XMen> liste;
    private XMenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("im here heelllooooooosfsdfsdf");

        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        // Find the Toolbar in the layout
        Toolbar toolbar = ui.toolbar;
        // Set the Toolbar as the ActionBar
        setSupportActionBar(toolbar);



        Log.d("MainAcitvity", "123456798 called");



        XMenApplication application = (XMenApplication) getApplication();
        liste = application.getListe();

        adapter = new XMenAdapter(liste);
        adapter.setOnItemClickListener(this::onItemClick);

        ui.recycler.setAdapter(adapter);
        ui.recycler.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        ui.recycler.setLayoutManager(lm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ui.recycler.addItemDecoration(dividerItemDecoration);
        editLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onEditActivityFinished
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        Log.d("MainAcitvity", "onCreateOptionsMenu called");
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void onEdit(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        // Add extra "position" to the intent
        intent.putExtra(EditActivity.EXTRA_POSITION, position);
        // Start the activity with result
        editLauncher.launch(intent);
    }

    private void onReallyDelete(int position) {
        liste.remove(position);

        // Notify the adapter that the item at this position has been removed

        adapter.notifyItemRemoved(position);
    }

    private void onDelete(int position)
    {
        XMen xmen = liste.get(position);
        new AlertDialog.Builder(this)
                .setTitle(xmen.getNom())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Vous confirmez la suppression ?")
                .setPositiveButton(android.R.string.ok,
                        (dialog, idbtn) -> onReallyDelete(position))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onEditActivityFinished(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            // Notify the adapter that the list has changed
            adapter.notifyDataSetChanged();
        }
    }




    private void onItemClick(int position) {
        XMen xmen = liste.get(position);
        xmen.setIdImage(R.drawable.undef);
        adapter.notifyItemChanged(position);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        XMenApplication application = (XMenApplication) getApplication();
        int iditem = item.getItemId();
        if (iditem == R.id.reinit) {
            application.initListe();
            adapter.notifyDataSetChanged();
            return true;
        }
        if (iditem == R.id.create) {
            // Call onEdit with position -1 to create a new XMen
            onEdit(-1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = item.getOrder();

        switch (item.getItemId()) {
            case XMenViewHolder.MENU_EDIT:
                onEdit(position);
                return true;
            case XMenViewHolder.MENU_DELETE:
                onDelete(position);
                return true;
        }

        return super.onContextItemSelected(item);
    }





}

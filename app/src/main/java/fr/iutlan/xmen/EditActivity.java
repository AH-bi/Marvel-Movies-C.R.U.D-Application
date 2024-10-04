package fr.iutlan.xmen;


import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fr.iutlan.xmen.databinding.ActivityEditBinding;
import fr.iutlan.xmen.databinding.ActivityMainBinding;

import java.util.List;
import lombok.Data;


// EditActivity.java
public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding ui;
    private List<XMen> liste;
    private int position;
    public static final String EXTRA_POSITION = "position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ui = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());


        Toolbar toolbar = ui.toolbar;
        setSupportActionBar(toolbar);

        // obtenir la liste
        XMenApplication application = (XMenApplication) getApplication();
        liste = application.getListe();

        // Retrieve the position from the intent
        Intent intent = getIntent();
        position = intent.getIntExtra(EXTRA_POSITION, -1);
        // Call setXMen if the position is not negative
        if (position >= 0) {
            setXMen(liste.get(position));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        Log.d("EditActivity", "onCreateOptionsMenu called");
        inflater.inflate(R.menu.edit_menu, menu); // Replace with your menu resource ID
        return true;
    }
    private void setXMen(XMen xmen) {
        // Set XMen information in views
        ui.nom.setText(xmen.getNom());
        ui.alias.setText(xmen.getAlias());
        ui.description.setText(xmen.getDescription());
        ui.image.setImageResource(xmen.getIdImage());
    }



    public void onAccept(MenuItem item) {
        XMen xmen = new XMen();
        xmen.setNom(ui.nom.getText().toString());
        xmen.setAlias(ui.alias.getText().toString());
        xmen.setDescription(ui.description.getText().toString());



        if (position < 0) {
            // Create a new X-Men
            liste.add(xmen); // Add the new X-Men to the list
        } else {
            XMen existingXMen = liste.get(position);
            // Set the existing image ID to the new X-Men
            xmen.setIdImage(existingXMen.getIdImage());
            // Update the existing X-Men
            liste.set(position, xmen); // Update the X-Men in the list at the given position
        }

        setResult(RESULT_OK);
        finish();
    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("EditActivity", "ahmed:binous : onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
    /*
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    Log.d("EditActivity", "ahmed:binous : onCreateOptionsMenu");
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.edit_menu, menu);
    Log.d("EditActivity", "ahmed:binous : onCreateOptionsMenu");

    return true;
}
*/

}


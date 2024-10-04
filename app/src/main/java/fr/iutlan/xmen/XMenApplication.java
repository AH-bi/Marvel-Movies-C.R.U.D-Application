package fr.iutlan.xmen;


import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class XMenApplication extends Application {

    // Global variable for the application: the list of XMen
    private final List<XMen> liste = new ArrayList<>();

    // Initialization of the context

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the list from resources
        initXMenList();
    }




    // Getter for the list variable
    public List<XMen> getListe() {
        return liste;
    }


    public void initListe() {
        // Clear the list and reinitialize
        liste.clear();
        initXMenList();
    }


    // Method to initialize the list from resources
    private void initXMenList() {
        // Access resources
        Resources res = getResources();

        // Retrieve arrays from resources
        final String[] noms = res.getStringArray(R.array.noms);
        final String[] aliases = res.getStringArray(R.array.alias);
        final String[] descriptions = res.getStringArray(R.array.descriptions);
        final String[] pouvoirs = res.getStringArray(R.array.pouvoirs);

        TypedArray images = res.obtainTypedArray(R.array.idimages);

        // Copy data from resources to the list
        for (int i = 0; i < noms.length; ++i) {
            // Constructor with all parameters
            XMen xm = new XMen(
                    noms[i],
                    aliases[i],  // Add alias
                    descriptions[i],  // Add description
                    pouvoirs[i],  // Add pouvoirs
                    images.getResourceId(i, R.drawable.undef)
            );

            // Add XMen to the list
            liste.add(xm);
        }

        // Release resources explicitly
        images.recycle();
    }



}

package com.kalpana.user.assignment71;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;

import com.kalpana.user.assignment71.database.DatabaseHandler;
import com.kalpana.user.assignment71.MyObject;
public class MainActivity extends Activity {

    /*
     * Change to type CustomAutoCompleteView instead of AutoCompleteTextView
     * since we are extending to customize the view and disable filter
     * The same with the XML view, type will be CustomAutoCompleteView
     */
    CustomAutoCompleteView myAutoComplete;

    // adapter for auto-complete
    ArrayAdapter<String> myAdapter;

    // for database operations
    DatabaseHandler databaseH;

    // just to add some initial value
    String[] item = new String[] {"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            // instantiate database handler
            databaseH = new DatabaseHandler(MainActivity.this);

            // put sample data to database
            insertSampleData();

            // autocompletetextview is in activity_main.xml
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myautocomplete);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /*Inserting Sample Data */
    public void insertSampleData(){

        // CREATE
        databaseH.create( new MyObject("HP Deskjet IA 3635 Aio Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 3775 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 48775 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 777") );
        databaseH.create( new MyObject("HP Spacejet All in one Printer") );
        databaseH.create( new MyObject("HP lazerjet") );
        databaseH.create( new MyObject("HP Deskjet GT") );
        databaseH.create( new MyObject("HP Deskjet  Advantage ultra All in one Printer") );
        databaseH.create( new MyObject("HP LaserJet Pro Printer") );
        databaseH.create( new MyObject("HP Deskjet 2132") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 12345 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 6789 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 1111 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 2222 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 333 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 444 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 555 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 666 All in one Printer ") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 777 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 888 All in one Printer") );
        databaseH.create( new MyObject("HP Deskjet Ink Advantage 999 All in one Printer") );

    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<MyObject> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }

}
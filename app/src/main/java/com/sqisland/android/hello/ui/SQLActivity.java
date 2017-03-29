package com.sqisland.android.hello.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sqisland.android.hello.R;
import com.sqisland.android.hello.db.MyDBHandler;
import com.sqisland.android.hello.model.Products;

public class SQLActivity extends Activity {

    EditText buckysInput;
    TextView buckysText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        buckysInput = (EditText) findViewById(R.id.buckyInput);
        buckysText = (TextView) findViewById(R.id.buckysText);
        dbHandler = new MyDBHandler(this,null,null,1);
        printDatabase();


        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClicked();
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClicked();
            }
        });

        Button resetDB = (Button) findViewById(R.id.reset);
        resetDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.resetDB();
                printDatabase();
            }
        });

    }


    //Add a product to the DB
    public void addButtonClicked(){
        Products products = new Products(buckysInput.getText().toString());
        dbHandler.addProduct(products);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(){
        String inputText = buckysInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    private void printDatabase() {
        String dbString = dbHandler.databaseToString();
        buckysText.setText(dbString);
        buckysInput.setText("");

    }
}

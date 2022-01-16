package com.example.actividadconjuntamyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Helper helper;
    SQLiteDatabase db;

    EditText nombre, peso;
    Spinner tipo;
    CheckBox rotten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new Helper(this);

        nombre=findViewById(R.id.nombrefruta);
        peso=findViewById(R.id.peso);
        tipo=findViewById(R.id.tipo);
        rotten=findViewById(R.id.rotten);

    }


    public void insertar(View view) {
        if (nombre.getText().toString().isEmpty() || peso.getText().toString().isEmpty() || tipo.getSelectedItem().toString().isEmpty() || rotten.getText().toString().isEmpty()/*poner si los que faltan estan vacios (spinner y checkbox)*/){
            Toast.makeText(this,"Faltan huecos por rellenar",Toast.LENGTH_SHORT).show();
            return;
        } else{
            db=helper.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put("nombre", nombre.getText().toString());
            values.put("peso", peso.getText().toString());
            //falta spinner y checkbox
            db.insert("fruitis", null, values);

            nombre.setText("");
            peso.setText("");
            //faltan los 2 de siempre jajxd

            db.close();

            Toast.makeText(this,"Se han insertado los datos",Toast.LENGTH_SHORT).show();


        }
    }


        public class ListarOperas extends AppCompatActivity {
        TextView txtTexto1, txtTexto2;
         SQLiteDatabase db;
         Helper helper;
         ListView lv;
         @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_operas);
        txtTexto1 = findViewById(R.id.textView);
        txtTexto2 = findViewById(R.id.textView2);
        lv = findViewById(R.id.listViewl);
         //realizamos la consulta
        consultaOperas();
    }

        private void consultaOperas() {
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(EstructuraBBDD.EstructuraOperas.TABLE_NAME_OPERAS, null, null, null, null, null, null);
        //adaptamos el cursor a nuestro ListView
        String[] from = {EstructuraBBDD.EstructuraOperas.COLUMN_NAME_TITULO, EstructuraBBDD.EstructuraOperas.COLUMN_NAME_COMPOSITOR};
        int[] to = {R.id.textView3, R.id.textView4};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.lista, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adaptador);
        db.close();
    }
}






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc1:
                pag1();
            case R.id.opc2:
                pag2();

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void pag1() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void pag2() {
        Intent i = new Intent(this,LayoutMostrar.class);
        startActivity(i);
    }







}
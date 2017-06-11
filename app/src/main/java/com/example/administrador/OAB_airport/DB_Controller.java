package com.example.administrador.OAB_airport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by rafael on 05/06/17.
 */

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "OAB_Airport.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf VARCHAR, telefone VARCHAR, email VARCHAR, senha VARCHAR, dataHora DATETIME DEFAULT CURRENT_TIMESTAMP);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Usuario;");
    }

    public void insert_Usuario(String Nome, String CPF, String Telefone, String Email, String Senha){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", Nome);
        contentValues.put("cpf", CPF);
        contentValues.put("telefone", Telefone);
        contentValues.put("email", Email);
        contentValues.put("senha", Senha);
        this.getWritableDatabase().insertOrThrow("Usuario", "", contentValues);
    }

    public void delete_Usuario(){
        this.getWritableDatabase().delete("Usuario", null, null);
    }

    public void update_Usuario(String id, String Nome, String CPF, String Telefone, String Email, String Senha){
        this.getWritableDatabase().execSQL("Update Usuario set nome='"+Nome+"', cpf='"+CPF+"', telefone='"+Telefone+"', email='"+Email+"', , senha='"+Senha+"' where  id='"+id+"'");
    }

    public void lista_Usuario(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Usuario", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+cursor.getString(1));
        }
    }

    public boolean validar_Usuario(String Email, String Senha) {
        String countQuery = "SELECT * FROM Usuario where email='"+Email+"' and senha='"+Senha+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        if(cnt > 0)
            return true;
        else
            return false;
    }
}

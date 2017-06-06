package com.example.administrador.OAB_airport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void delete_Usuario(String id){
        this.getWritableDatabase().delete("Usuario", "id='"+id+"'", null);
    }

    public void update_Usuario(String id, String Nome, String CPF, String Telefone, String Email, String Senha){
        this.getWritableDatabase().execSQL("Update Usuario set nome='"+Nome+"', cpf='"+CPF+"', telefone='"+Telefone+"', email='"+Email+"', , senha='"+Senha+"' where  id='"+id+"'");
    }


}

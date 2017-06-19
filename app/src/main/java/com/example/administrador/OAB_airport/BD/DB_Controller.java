package com.example.administrador.OAB_airport.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by rafael on 05/06/17.
 */

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "OAB_Airport.db", factory, version);
    }

    public static final String TableUsuario= "CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf VARCHAR, telefone VARCHAR, email VARCHAR, senha VARCHAR, dataHora DATETIME DEFAULT CURRENT_TIMESTAMP);";
    public static final String TableReclamacao = "CREATE TABLE Reclamacao (id INTEGER PRIMARY KEY AUTOINCREMENT, 'idUser' VARCHAR, 'aeroporto' VARCHAR, 'descricao' TEXT, 'status' VARCHAR, dataHora VARCHAR);";
    public static final String TableStatus = "CREATE TABLE Status (id INTEGER, descricao VARCHAR);";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableUsuario);
        sqLiteDatabase.execSQL(TableReclamacao);
        sqLiteDatabase.execSQL(TableStatus);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Usuario;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Reclamacao;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Status;");

        onCreate(sqLiteDatabase);
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

    public String Id_Usuario(String Email, String Senha){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Usuario where email='"+Email+"' and senha='"+Senha+"'", null);
        String val ="";
        while (cursor.moveToNext()){
            val = cursor.getString(0);
        }
        return val;
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
    public boolean validar_cpf(String CPF) {
        String countQuery = "SELECT * FROM Usuario where cpf='"+CPF+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        if(cnt > 0)
            return true;
        else
            return false;
    }

    public void insert_Reclamacao(String IdUser, String Aeroporto, String Descricao, String DataHora){
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUser", IdUser);
        contentValues.put("aeroporto", Aeroporto);
        contentValues.put("descricao", Descricao);
        contentValues.put("status", "1");
        contentValues.put("dataHora", DataHora);
        this.getWritableDatabase().insertOrThrow("Reclamacao", "", contentValues);
    }

    public void delete_Reclamacao(String ID){
        this.getWritableDatabase().delete("Reclamacao", "id='"+ID+"'", null);
    }

    public void update_Usuario(String Status, String ID) {
        this.getWritableDatabase().execSQL("Update Reclamacao set status='" + Status + "' where  id='" + ID + "'");
    }

    public void lista_Reclamacao(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT a.aeroporto, b.descricao FROM Reclamacao a inner join Status b on a.status = b.id", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+cursor.getString(1));
        }
    }

    public void lista_Usuario(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Usuario", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+cursor.getString(1));
        }
    }

    public void insert_Status(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", "1");
        contentValues.put("descricao", "Enviado");
        this.getWritableDatabase().insertOrThrow("Status", "", contentValues);

        contentValues.put("id", "2");
        contentValues.put("descricao", "Em Avaliação");
        this.getWritableDatabase().insertOrThrow("Status", "", contentValues);

        contentValues.put("id", "2");
        contentValues.put("descricao", "Aprovado");
        this.getWritableDatabase().insertOrThrow("Status", "", contentValues);

        contentValues.put("id", "2");
        contentValues.put("descricao", "Reprovado");
        this.getWritableDatabase().insertOrThrow("Status", "", contentValues);
    }

    public boolean qtd_status() {
        String countQuery = "SELECT * FROM Status";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        if(cnt > 0)
            return true;
        else
            return false;
    }

    public void lista_Status(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Status", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+cursor.getString(1));
        }
    }
}

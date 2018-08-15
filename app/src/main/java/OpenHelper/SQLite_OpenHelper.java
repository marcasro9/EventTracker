package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Armando on 26/08/2017.
 */

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table usuarios(_ID integer primary Key autoincrement, " +
                "Usuario text, Correo text, Password text);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //Metodo que permite abrir la base de datos
    public void abrir() {
        this.getWritableDatabase();
    }

    //Metodo que permite cerrar la base de datos
    public void cerrar() {
        this.close();
    }

    //Metodo que permite insertar registros en la tabla usuarios
    public void insertarRegistro(String usu, String cor, String pass){
        ContentValues valores= new ContentValues();
        valores.put("Usuario",usu);
        valores.put("Correo",cor);
        valores.put("Password",pass);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    //Metodo que permite validar el usuario

    public Cursor ConsultarUsuPas(String cor, String pas) throws SQLiteAbortException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("usuarios",new String[]{"_ID",
                "Usuario","Correo","Password"},"Correo like '"+cor+"' " +
                "and Password like '"+pas+"'",null,null,null,null);
        return mcursor;
    }
}
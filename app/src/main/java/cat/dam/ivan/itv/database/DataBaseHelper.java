package cat.dam.ivan.itv.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cat.dam.ivan.itv.Cotxe;


public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Version
    private	static final int DB_VERSION =	7;
    // Database Name
    private	static final String DB_NAME = "Itv";
    // Table Name
    private	static final String TABLE_NAME = "Cotxes";

    // Table Columns
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MATRICULA = "matricula";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_ANYITV = "anyItv";

    //Constructor de la classe
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Mètode que s'executa quan es crea la base de dades
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MATRICULA + " TEXT," + COLUMN_MODEL + " Text," + COLUMN_COLOR + " Text," + COLUMN_ANYITV + " Text" + " )";
        db.execSQL(query);
    }

    //Metode que s'executa quan es fa algun canvi a la base de dades i es torna a crear la taula
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Metode que afegeix un cotxe a la base de dades
    public void addCar(Cotxe item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, item.getMatricula());
        values.put(COLUMN_MODEL, item.getModel());
        values.put(COLUMN_COLOR, item.getColor());
        values.put(COLUMN_ANYITV, item.getAnyItv());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    //Metode que eliminarà un cotxe de la base de dades a partir de l'id
    public void deleteCotxe(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "	= ?", new String[] { String.valueOf(id)});
    }

    //Metode que actualitzarà un cotxe de la base de dades a partir de l'id
    public void updateCotxe(Cotxe item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, item.getMatricula());
        values.put(COLUMN_MODEL, item.getModel());
        values.put(COLUMN_COLOR, item.getColor());
        values.put(COLUMN_ANYITV, item.getAnyItv());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[] { String.valueOf(item.getId()) });
    }

    //Metode que retornarà els cotxes de la base de dades
    public ArrayList<Cotxe> getCars(){
        ArrayList<Cotxe> cars = new ArrayList<>();
        //objecte que permet llegir la base de dades
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String matricula = cursor.getString(1);
                String model = cursor.getString(2);
                String color = cursor.getString(3);
                String anyItv = cursor.getString(4);
                Cotxe car = new Cotxe(id, matricula, model, color, anyItv);
                cars.add(car);
            }while(cursor.moveToNext());
        }
        return cars;
    }

    //Metode que retornarà els cotxes de la base de dades que tinguin l'ITV caducada
    public ArrayList<Cotxe> getCarsItv(){
        ArrayList<Cotxe> cars = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ANYITV + " < '2023-01-01' ";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String matricula = cursor.getString(1);
                String model = cursor.getString(2);
                String color = cursor.getString(3);
                String anyItv = cursor.getString(4);
                Cotxe car = new Cotxe(id, matricula, model, color, anyItv);
                cars.add(car);
            }while(cursor.moveToNext());

        }
        cursor.close();
        return cars;
    }




}
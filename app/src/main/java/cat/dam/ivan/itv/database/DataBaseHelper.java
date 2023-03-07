package cat.dam.ivan.itv.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cat.dam.ivan.itv.Cotxe;


public class DataBaseHelper extends SQLiteOpenHelper {

    private	static final int DB_VERSION =	7;
    private	static final String DB_NAME = "Itv";
    private	static final String TABLE_NAME = "Cotxes";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MATRICULA = "matricula";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_ANYITV = "anyItv";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MATRICULA + " TEXT," + COLUMN_MODEL + " Text," + COLUMN_COLOR + " Text," + COLUMN_ANYITV + " Text" + " )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCar(Cotxe item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, item.getMatricula());
        values.put(COLUMN_MODEL, item.getModel());
        values.put(COLUMN_COLOR, item.getColor());
        values.put(COLUMN_ANYITV, item.getAnyItv());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public void deleteCotxe(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "	= ?", new String[] { String.valueOf(id)});
    }

    public void updateCotxe(Cotxe item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, item.getMatricula());
        values.put(COLUMN_MODEL, item.getModel());
        values.put(COLUMN_COLOR, item.getColor());
        values.put(COLUMN_ANYITV, item.getAnyItv());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COLUMN_MATRICULA + " = ?", new String[] { String.valueOf(item.getMatricula()) });
    }

    public ArrayList<Cotxe> getCars(){
        ArrayList<Cotxe> cars = new ArrayList<>();
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
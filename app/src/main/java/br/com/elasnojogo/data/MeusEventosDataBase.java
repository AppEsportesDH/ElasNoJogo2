package br.com.elasnojogo.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MeusEventosFireBase.class}, version = 1, exportSchema = false)
public abstract class MeusEventosDataBase extends RoomDatabase {
    public static volatile MeusEventosDataBase INSTANCE;
    public abstract MeusEventosDAO meusEventosDAO;

    public static MeusEventosDataBase getDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (MeusEventosDataBase.class) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context, MeusEventosDataBase.class, "meus_eventos").
                        fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;

    }
}
package br.com.elasnojogo.repository.data;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.elasnojogo.model.Evento;

@Database(entities = {Evento.class}, version = 2, exportSchema = false)
public abstract class EventosDataBase extends RoomDatabase {

    public static volatile EventosDataBase INSTANCE;
    public abstract EventosDAO eventosDAO() ;

    public static EventosDataBase getDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (EventosDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, EventosDataBase.class, "criar_eventos")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
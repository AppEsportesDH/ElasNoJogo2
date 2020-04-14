package br.com.elasnojogo.Repository.data;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.Model.SegurancaMulher;

@Database(entities = {Evento.class, SegurancaMulher.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
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
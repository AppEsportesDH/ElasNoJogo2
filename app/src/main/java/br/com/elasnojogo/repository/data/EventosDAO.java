package br.com.elasnojogo.repository.data;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import br.com.elasnojogo.model.Evento;
import io.reactivex.Flowable;

@Dao
public interface EventosDAO {
    @Insert
    public void inserirEventos(Evento evento);

    @Delete
    void deletarEventos(Evento evento);

    @Update
    void updateEventos(Evento evento);

    @Query("SELECT * FROM evento ORDER BY id DESC ")
    Flowable<List<Evento>> retornaEventos();

}

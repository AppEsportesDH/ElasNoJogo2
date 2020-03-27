package br.com.elasnojogo.data;


import android.database.Observable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;


@Dao
public interface MeusEventosDAO {
   // esperando o firebase de CadastroEventos para receber os parametros
//    @Insert
//    public void inserirEventos(Eventos eventos);
//
//    @Delete
//    void DeletarEventos (Eventos eventos);
//
//    @Update
//    void updateEventos(Eventos eventos);
//
//    @Query("SELECT * FROM eventos ORDER BY id DESC ")
//    Observable<List<Eventos>>retornaEventos;
}

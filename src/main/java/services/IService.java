package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public  void ajouter(T t );
    public  void modifier(T t ) throws SQLException;
    public  void supprimer(int id );
    public List<T> afficher();





}

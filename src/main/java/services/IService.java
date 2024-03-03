package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{


    void ajouter(T t ) throws SQLException;
    void modifier(int id, T t) throws SQLException;
    T authentifier(String email, String password) throws SQLException;

    void supprimer(int id) throws SQLException;
    List<T> afficher() throws SQLException;



}

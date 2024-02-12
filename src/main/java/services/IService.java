package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public  void ajouter(T t ) throws SQLException;
    void modifier(int id, T t) throws SQLException; // Change the method signature
    T authentifier(String email, String password) throws SQLException;

    void supprimer(int id) throws SQLException; // Change the method signature
    public List<T> afficher() throws SQLException;



}

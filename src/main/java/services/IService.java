package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{

    void ajouter(T t ) throws SQLException;
    void modifier(int id, T t) throws SQLException;
    T authentifier(String email, String password) throws SQLException;

    void supprimer(int id) throws SQLException;
    List<T> afficher() throws SQLException;
/*
    public  void ajouter(T t ) throws SQLException;
    public  void modifier(T t ) throws SQLException;
    public  void supprimer(T t ) throws SQLException;
    public List<T> afficher() throws SQLException;
*/
void ajouter_v2(T t) throws SQLException;
    void modifier_v2(T t) throws SQLException;
    void supprimer_v2(T t) throws SQLException;
    List<T> afficher_v2() throws SQLException;



}

package org.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.connection.ConnectionDB.connect;
public class BookService {
    private Connection conn = connect();

    public void registerBook(int code, String title, String authorName, String date){
        String SQL = "INSERT INTO book (code, title, authorname, date) VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,code);
            preparedStatement.setString(2,title);
            preparedStatement.setString(3,authorName);
            preparedStatement.setString(4,date);

            preparedStatement.executeUpdate();
            System.out.println("Livro registrado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void changeBook(int code, String title){
        String SQL = "UPDATE book SET title =? WHERE code=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1,title);
            preparedStatement.setInt(2,code);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Livro alterado com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o código fornecido.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBook(int code){
        String SQL = "DELETE FROM book WHERE code=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,code);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o código fornecido.");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showAllBooks(){
        String SQL = "SELECT * FROM book";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println("CÓD: " + resultSet.getInt("code"));
                System.out.println("NOME DO LIVRO: " + resultSet.getString("title"));
                System.out.println("NOME DO AUTOR: " + resultSet.getString("authorname"));
                System.out.println("DATA DE LANÇAMENTO: " + resultSet.getString("date"));
                System.out.println();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showBook(int code){
        String SQL = "SELECT * FROM book WHERE code=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("CÓD: " + resultSet.getInt("code"));
                System.out.println("NOME DO LIVRO: " + resultSet.getString("title"));
                System.out.println("NOME DO AUTOR: " + resultSet.getString("authorname"));
                System.out.println("DATA DE LANÇAMENTO: " + resultSet.getString("date"));
                System.out.println();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

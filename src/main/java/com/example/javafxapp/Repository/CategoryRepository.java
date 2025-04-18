package com.example.javafxapp.Repository;

import com.example.javafxapp.Config.DatabaseConnection;
import com.example.javafxapp.Model.Category;
import com.example.javafxapp.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements JDBCRepository<Category>{
    // add category .
    public void add(Category category) {
        String sql = "INSERT INTO Category(category_name) Value (?) " ;
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1,category.getCategory_name());
            pstmt.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update product .
    public void update(Category category) {
        String sql = "UPDATE Category set category_name = ?  where category_id = ?" ;
        try(Connection connection = DatabaseConnection.getConnection() ;
            PreparedStatement pstmt = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1,category.getCategory_name());
            pstmt.setInt(2,category.getCategory_id());
            pstmt.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace() ;
        }
    }

    // delete product
    public void delete(int categoryId) {
        String sql = "UPDATE Category set deleted = ? where category_id = ?" ;
        try(Connection connection = DatabaseConnection.getConnection() ;
            PreparedStatement pstmt = connection.prepareStatement(sql) ;
        ) {
            pstmt.setBoolean(1,true);
            pstmt.setInt(2 , categoryId);
            pstmt.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get all product .
    public List<Category> getAll(){
        String sql = "SELECT * FROM Category where deleted = ?" ;
        List<Category> categories = new ArrayList<>() ;
        try (Connection connection = DatabaseConnection.getConnection() ;
             PreparedStatement pstmt = connection.prepareStatement(sql) ;
        ) {
            pstmt.setBoolean(1,false);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                categories.add(new Category(
                        rs.getInt("category_id") ,
                        rs.getString("category_name")
                )) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories ;
    }

    // find category by category_id .
    public Category findByID(int category_id) {
        String sql = "SELECT * from category where category_id = ? AND deleted = ?" ;
        try(Connection connection = DatabaseConnection.getConnection() ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setInt(1,category_id);
            preparedStatement.setBoolean(2,false);
            ResultSet rs = preparedStatement.executeQuery() ;
            if (rs.next()) {
                return new Category(
                        rs.getInt("category_id") ,
                        rs.getString("category_name")
                ) ;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    // find category by category_name.
    public Category findByName(String category_name) {
        String sql = "SELECT * from category where category_name = ? AND deleted = ?" ;
        try(Connection connection = DatabaseConnection.getConnection() ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1,category_name);
            preparedStatement.setBoolean(2,false);
            ResultSet rs = preparedStatement.executeQuery() ;
            if (rs.next()) {
                return new Category(
                        rs.getInt("category_id") ,
                        rs.getString("category_name"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    // find all category by keyword .
    public List<Category> findAllByKeyword(String keyword) {
        String sql = "SELECT * FROM category WHERE category_name LIKE ? AND deleted = ?";
        List<Category> categories = new ArrayList<>() ;
        try (Connection connection = DatabaseConnection.getConnection() ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        ){
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setBoolean(2, false);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                categories.add(new Category(
                        rs.getInt("category_id") ,
                        rs.getString("category_name")
                )) ;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return categories ;
    }


}

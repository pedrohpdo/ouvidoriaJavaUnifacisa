package databaseMySQL;
import java.util.ArrayList;
import feedback.Category;

public interface interfaceDB {
    
    void addData(Category category);

    ArrayList<Category> getData(String type);

    void updateData(String feedback, int key);

    void deleteData(int key);

    void deleteAllData();

}
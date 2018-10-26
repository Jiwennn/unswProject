package au.espressolearning.javaapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import au.espressolearning.javaapp.model.User;

import java.util.List;

/*In the DAO (data access object), you specify SQL queries and associate them with method calls.
The compiler checks the SQL and generates queries from convenience annotations for common queries, such as @Insert.
The DAO must be an interface or abstract class.
By default, all queries must be executed on a separate thread.
Room uses the DAO to create a clean API for your code.*/

//Annotate the class with @Dao to identify it as a DAO class for Room.
//don't have to provide any SQL!
@Dao
public interface UserDAO {

    //If the table has more than one column, use @Insert(onConflict = OnConflictStrategy.REPLACE) to replace a row.
    //Declare a method to insert one user or more individual data objects or a simple array
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserList(List<User> userList);


    //Create a method (getAllUsers()) to get all the user and return a List of User
    /* LiveData - When data changes you usually want to take some action, such as displaying the updated data in the UI.
    This means you have to observe the data so that when it changes, you can react.
    Depending on how the data is stored, this can be tricky.
    Observing changes to data across multiple components of your app can create explicit, rigid dependency paths between the components.
    This makes testing and debugging difficult, among other things.
    */
    @Query("SELECT * FROM user_table WHERE email = :email")
    User findbyEmail(String email);


    @Query("SELECT * from user_table ORDER BY created_at desc")
    List<User> getAllUsers();
    //LiveData<List<User>> getAllUsers();


    //Declare a method to delete all the user using @Query
    //There is no convenience annotation for deleting multiple entities, so annotate the method with the generic @Query.
    @Query("DELETE FROM user_table")
    void deleteAll();

    //@Delete and @Update annotations for deleting and updating a row
    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

}


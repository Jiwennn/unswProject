package au.espressolearning.javaapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import au.espressolearning.javaapp.database.UserRoomDatabase;
import au.espressolearning.javaapp.model.User;

import java.util.List;

//The ViewModel's role is to provide data to the UI and survive configuration changes.
//A ViewModel acts as a communication center between the Repository and the UI.
//You can also use a ViewModel to share data between fragments. The ViewModel is part of the lifecycle library.
public class UserViewModel extends AndroidViewModel {

    //Add a private member variable to hold a reference to the repository.
    private UserRepository mRepository;

    //Add a private LiveData member variable to cache the list of users.
    //private LiveData<List<User>> mAllUsers;
    List<User> mAllUsers;

    private LiveData<User> mUser;

    //Add a constructor that gets a reference to the repository and gets the list of users from the repository.
    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    //Add a "getter" method for all the users. This completely hides the implementation from the UI.
    /*
    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }*/

    List<User> getAllUsers() {
        return mAllUsers;
    }



    //Create a wrapper insert() method that calls the Repository's insert() method.
    //In this way, the implementation of insert() is completely hidden from the UI.
    public void insert(User user) {
        mRepository.insert(user);
    }
}
package au.espressolearning.javaapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import au.espressolearning.javaapp.database.UserDAO;
import au.espressolearning.javaapp.database.UserRoomDatabase;
import au.espressolearning.javaapp.model.User;

import java.util.List;


//A Repository is a class that abstracts access to multiple data sources
//The Repository is NOT part of the Architecture Components libraries, but is a suggested best practice for code separation and architecture.
//A Repository class handles data operations. It provides a clean API to the rest of the app for app data.
public class UserRepository {
    //Add member variables for the DAO and the list of User
    private UserDAO mUserDao;
    //private LiveData<List<User>> mAllUsers;
    List<User> mAllUsers;
    private LiveData<User> mUser;

    private UserRoomDatabase db;


    //Add a constructor that gets a handle to the database and initializes the member variables
    public UserRepository(Application application) {
        db = UserRoomDatabase.getInstance(application);
        mUserDao = db.UserDao();
        mAllUsers = mUserDao.getAllUsers();
    }


    //Add a wrapper for getAllUsers(). Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    /*LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }*/
    List<User> getAllUsers() {
        return mAllUsers;
    }

    //Add a wrapper for the insert() method.
    //You must call this on a non-UI thread or your app will crash.
    //Room ensures that you don't do any long-running operations on the main thread, blocking the UI
    public void insert (User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO mAsyncTaskDao;

        insertAsyncTask(UserDAO dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insertUser(params[0]);
            return null;
        }
    }
}
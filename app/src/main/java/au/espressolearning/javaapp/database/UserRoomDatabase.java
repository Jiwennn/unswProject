package au.espressolearning.javaapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import au.espressolearning.javaapp.model.User;

/*Room is a database layer on top of an SQLite database.
Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.

//Room uses the DAO to issue queries to its database.
By default, to avoid poor UI performance, Room doesn't allow you to issue database queries on the main thread.
LiveData applies this rule by automatically running the query asynchronously on a background thread, when needed.
Room provides compile-time checks of SQLite statements.
Room class must be abstract and extend RoomDatabase.
Usually, you only need one instance of the Room database for the whole app.*/


//Annotate the class to be a Room database, declare the entities that belong in the database and set the version number.
//Listing the entities will create tables in the database.
@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    //Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao.
    public abstract UserDAO UserDao();

    //Make the UserRoomDatabase a singleton to prevent having multiple instances of the database opened at the same time.
    private static volatile UserRoomDatabase INSTANCE;

    //set UserRoomDatabase as a public method, so that this method can be used in other class
    public static UserRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {

                    //Add the code to get a database.
                    //This code uses Room's database builder to create a RoomDatabase object in the application context from the UserRoomDatabase class and names it "user_database"
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "User_database")
                            //.addCallback(sRoomDatabaseCallback) // add the callback to the database build sequence right before calling .build().
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //to dereference the database object within the singleton and clean up the reference and avoid leaks
    public static void destroyInstance() {
        INSTANCE = null;
    }

    //To delete all content and repopulate the database whenever the app is started, you create a RoomDatabase.Callback and override onOpen().
    //Because you cannot do Room database operations on the UI thread, onOpen() creates and executes an AsyncTask to add content to the database.
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    //Here is the code for the AsyncTask that deletes the contents of the database, then populates it with the users detail.
    //Feel free to add more users!
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDAO mDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mDao = db.UserDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            User user = new User("Lina","llzeom@gmail.com","012345");
            mDao.insertUser(user);
            user = new User("Jiwen","jiwen@gmail.com","012345");
            mDao.insertUser(user);
            return null;
        }
    }
}

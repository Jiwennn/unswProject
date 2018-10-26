package au.espressolearning.javaapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import au.espressolearning.javaapp.utils.TimestampConverter;

import java.util.Date;

//Each @Entity class represents an entity in a table.
//Annotate your class declaration to indicate that it's an entity.
//Specify the name of the table if you want it to be different from the name of the class.
@Entity(tableName = "user_table")
public class User  {

    //Every entity needs a primary key. To keep things simple, each id acts as its own primary key.
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "email")
    private String mEmail;

    //Specify the name of the column in the table if you want it to be different from the name of the member variable.
    @ColumnInfo(name = "username")
    private String mUsername;

    private boolean encrypt;
    @ColumnInfo(name = "password")
    private String mPwd;

    @ColumnInfo (name ="created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    @ColumnInfo (name ="modified_at")
    @TypeConverters({TimestampConverter.class})
    private Date modifiedAt;

    @Ignore
    public User(){
    }

    /*
    public User(String mEmail, String mUsername, String mPwd, Date createdAt, Date modifiedAt) {
        this.mEmail = mEmail;
        this.mUsername = mUsername;
        this.mPwd = mPwd;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    */
    public User(String mEmail, String mUsername, String mPwd) {
        this.mEmail = mEmail;
        this.mUsername = mUsername;
        this.mPwd = mPwd;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public String getPwd() {
        return mPwd;
    }

    public void setPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
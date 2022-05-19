package com.dengemo.TekWulf.CANBusGuardian.room;

import android.app.Application;
import android.os.AsyncTask;

import com.dengemo.TekWulf.CANBusGuardian.room.dao.UserDao;
import com.dengemo.TekWulf.CANBusGuardian.room.database.UserDatabase;
import com.dengemo.TekWulf.CANBusGuardian.room.entity.User;

import java.util.concurrent.ExecutionException;

public class UserRepository {
    private final UserDao mUserDao;

    public UserDao getDao(){
        return mUserDao;
    }

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }
    public void insert(User user) {
        new InsertTask(mUserDao).execute(user);
    }

    public User queryUser(String username, String password) {
        try {
            return new QueryUserTask(mUserDao).execute(username, password).get();
        } catch (Exception e){
            return null;
        }
    }

    private static class QueryUserTask extends AsyncTask<String, Void, User> {
        private final UserDao mAsyncTaskDao;

        public QueryUserTask(UserDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected User doInBackground(String... strings) {

            User user = mAsyncTaskDao.getUser(strings[0], strings[1]);

            return user;
        }
    }

    private static class InsertTask extends AsyncTask<User, Void, Void> {
        private final UserDao mAsyncTaskDao;

        public InsertTask(UserDao userDao) {
            mAsyncTaskDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.insert(users[0]);
            return null;
        }
    }

}

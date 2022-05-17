package com.dengemo.TekWulf.CANBusGuardian.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {
    private AppDatabase mAppDatabase;
    private final LiveData<List<User>> mAllUsers;
    private final UserDao mUserDao;

    public AppRepository(Context context) {
        super();
        mAppDatabase = AppDatabase.getInstance(context);
        mUserDao = mAppDatabase.userDao();
        mAllUsers = mUserDao.doFindAll();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    void insertUser(User... users) {
        new InsertUserAsyncTask(mUserDao).execute(users);
    }

    void deleteUser(User... users) {
        new DeleteUserAsyncTask(mUserDao).execute(users);
    }

    void updateUser(User... users) {
        new UpdateUserAsyncTask(mUserDao).execute(users);
    }

    void deleteAllUser() {
        new DelAllUserAsyncTask(mUserDao).execute();
    }

    void findUser(User user) {
        new FindUserAsyncTask(mUserDao).execute(user);
    }

    void findAllUser() {
        new FindAllUserAsyncTask(mUserDao).execute();
    }

    public void closeDataBase() {
        if (mAppDatabase != null) {
            if (mAppDatabase.isOpen()) {
                mAppDatabase.close();
                mAppDatabase = null;
            }
        }
    }
    //一个异步线程  三个参数  对象，进度，结果\

    /**
     * 插入数据
     */
    static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private final UserDao userDao;

        InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users);
            return null;
        }
    }

    /**
     * 删除数据
     */
    static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private final UserDao userDao;

        DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users);
            return null;
        }
    }

    /**
     * 更新数据
     */
    static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private final UserDao userDao;

        UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users);
            return null;
        }
    }

    /**
     * 删除所有数据
     */
    public static class DelAllUserAsyncTask extends AsyncTask<User, Void, Void> {
        private final UserDao userDao;

        DelAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delAllUser();
            return null;
        }
    }


    /**
     * 查找数据
     */
    static class FindUserAsyncTask extends AsyncTask<User, Void, User> {
        private final UserDao userDao;

        FindUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(User... users) {
            return userDao.doFindByUsername(users[0].username);
        }
    }

    /**
     * 查找数据
     */
    static class FindAllUserAsyncTask extends AsyncTask<User, Void, LiveData<List<User>>> {
        private final UserDao userDao;

        FindAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected LiveData<List<User>> doInBackground(User... users) {
            return userDao.doFindAll();
        }
    }
}


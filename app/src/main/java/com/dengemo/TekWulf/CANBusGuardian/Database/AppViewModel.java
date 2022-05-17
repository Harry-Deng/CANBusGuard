package com.dengemo.TekWulf.CANBusGuardian.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private final AppRepository mRepository;

    public AppViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<List<User>> getAllUser() {
        return mRepository.getAllUsers();
    }

    public void insertUser(User... users) {
        mRepository.insertUser(users);
    }

    public void delUser(User... users) {
        mRepository.deleteUser(users);
    }

    public void updateUser(User... users) {
        mRepository.updateUser(users);
    }

    public void delAll() {
        mRepository.deleteAllUser();
    }

    public void findUser(User user) {
        mRepository.findUser(user);
    }

    public void findAllUser(){
        mRepository.findAllUser();
    }

    public void closeDataBase() {
        mRepository.closeDataBase();
    }

}


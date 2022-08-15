package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public int findNotExistingSearchKey(String uuid) {
        int index = findIndex(uuid);
        if (findIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    public int findExistingSearchKey(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}

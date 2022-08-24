package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    Resume doGet(Object searchKey) {
        String uuid = (String) searchKey;
        int index = findIndex(uuid);
        return storage[index];
    }

    @Override
    void doDelete(Object searchKey) {
        String uuid = (String) searchKey;
        int index = findIndex(uuid);
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    void doSave(Object searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflow", resume.getUuid());
        }
        int index = (int) searchKey;
        insertResume(resume, index);
        size++;
    }

    @Override
    void doUpdate(Object searchKey, Resume resume) {
        int index = (int)searchKey;
        storage[index] = resume;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return findIndex(uuid);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    protected abstract int findIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}




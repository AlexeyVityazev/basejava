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
    public final Resume get(String uuid) {
        int index = findNotExistingSearchKey(uuid);
        return storage[index];
    }

    @Override
    public final void save(Resume resume) {
        int index = findExistingSearchKey(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflow", resume.getUuid());
        }
        insertResume(resume, index);
        size++;
    }

    @Override
    public final void update(Resume resume) {
        int index = findNotExistingSearchKey(resume.getUuid());
        storage[index] = resume;
    }

    @Override
    public final void delete(String uuid) {
        int index = findNotExistingSearchKey(uuid);
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
}




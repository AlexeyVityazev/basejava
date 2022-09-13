package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public static Comparator<Resume> comparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public Object findNotExistingSearchKey(Object key) {
        String k = (String) key;
        Object searchKey = findSearchKey(k);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(k);
        } else {
            return searchKey;
        }
    }

    public Object findExistingSearchKey(Object key) {
        String k = (String) key;
        Object searchKey = findSearchKey(k);
        if (isExist(searchKey)) {
            throw new ExistStorageException(k);
        } else {
            return searchKey;
        }
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = findNotExistingSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = findExistingSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object searchkey = findNotExistingSearchKey(uuid);
        return doGet(searchkey);
    }

    @Override
    public void delete(String uuid) {
        Object searchkey = findNotExistingSearchKey(uuid);
        doDelete(searchkey);
    }


    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list, comparator);
        return list;
    }

    abstract List<Resume> doCopyAll();

    abstract boolean isExist(Object searchKey);

    abstract Object findSearchKey(String uuid);

    abstract Resume doGet(Object searchKey);

    abstract void doDelete(Object searchKey);

    abstract void doSave(Object searchKey, Resume resume);

    abstract void doUpdate(Object searchKey, Resume resume);
}

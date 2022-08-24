package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> list = new ArrayList<>();

    @Override
    protected Object findSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[list.size()];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = list.get(i);
        }
        return resumes;
    }

    @Override
    Resume doGet(Object searchKey) {
        String uuid = (String) searchKey;
        int index = (int) findSearchKey(uuid);
        return list.get(index);
    }

    @Override
    void doDelete(Object searchKey) {
        String uuid = (String) searchKey;
        int index = (int) findSearchKey(uuid);
        list.remove(index);
    }

    @Override
    void doSave(Object searchKey, Resume resume) {
        list.add(resume);
    }

    @Override
    void doUpdate(Object searchKey, Resume resume) {
        int index = (int) searchKey;
        list.add(index, resume);
    }
}

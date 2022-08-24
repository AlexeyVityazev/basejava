package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> map = new HashMap();
    @Override
    protected Object findSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return 5;
            }
        }
        return -1;
    }

    @Override
    Resume doGet(Object searchKey) {
        String uuid = (String) searchKey;
        return map.get(uuid);
    }

    @Override
    void doDelete(Object searchKey) {
        String uuid = (String) searchKey;
        map.remove(uuid);
    }

    @Override
    void doSave(Object searchKey, Resume resume) {
        String key = resume.getUuid();
        map.put(key, resume);

    }

    @Override
    void doUpdate(Object searchKey, Resume resume) {
        String uuid = resume.getUuid();
        map.put(uuid, resume);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[map.size()];
        int count = 0;
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            resumes[count] = entry.getValue();
            count++;
        }
        return resumes;
    }

    @Override
    public int size() {
        return map.size();
    }
}

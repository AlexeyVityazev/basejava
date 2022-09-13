package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object findSearchKey(String uuid) {
        if (map.get(uuid) != null) {
            return uuid;
        }
        return null;
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
    boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        map.clear();
    }


    @Override
    List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<Resume>(map.values());
        return list;
    }


    @Override
    public int size() {
        return map.size();
    }
}

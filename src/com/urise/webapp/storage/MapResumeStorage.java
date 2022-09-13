package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object findSearchKey(String uuid) {
        return map.get(uuid);
    }


    @Override
    Resume doGet(Object searchKey) {
        // String uuid = (String) searchKey;
        return (Resume) searchKey;
    }

    @Override
    void doDelete(Object searchKey) {
        Resume resume = (Resume) searchKey;
        map.remove(resume.getUuid());
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

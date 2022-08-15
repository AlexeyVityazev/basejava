package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume resume) {
        int index = findNotExistingSearchKey(resume.getUuid());
        list.add(index, resume);
    }

    @Override
    public void save(Resume resume) {
        findExistingSearchKey(resume.getUuid());
        list.add(resume);
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = list.get(findNotExistingSearchKey(uuid));
        return resume;
    }

    @Override
    public void delete(String uuid) {
        list.remove(findNotExistingSearchKey(uuid));
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
    public int size() {
        return list.size();
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume, int index) {

    }

    @Override
    protected void deleteResume(int index) {
    }
}

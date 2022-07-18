package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int i = findindex(uuid);
        if (i == -1) {
            System.out.println("Резюме не найдено " + uuid);
            return null;
        }
        return storage[i];
    }

    @Override
    public final void save(Resume resume) {
        int index = findindex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Хранилище переполнено");
        } else {
            if (index >= 0) {
                System.out.println("Такое резюме уже существует " + resume.getUuid());
            } else {
                insertResume(resume, index);
                size++;
            }
        }
    }

    public final void update(Resume resume) {
        int index = findindex(resume.getUuid());
        if (index >= 0) {
            insertResume(resume, index);
        } else {
            System.out.println("Такого резюме нет " + resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = findindex(uuid);
        if (index >= 0) {
            deleteResume(index);
            size--;
        } else {
            System.out.println("Такого резюме нет " + uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int findindex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}




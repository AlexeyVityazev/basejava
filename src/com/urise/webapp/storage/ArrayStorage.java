package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static int storagesize = 10_000;
    Resume[] storage = new Resume[storagesize];
    int size = 0;

    public int searchindex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume resume) {
        if (searchindex(resume.getUuid()) != -1) {
            storage[searchindex(resume.getUuid())] = resume;
        } else {
            System.out.println("Такого резюме нет " + resume.getUuid());
        }

    }

    public void clear() {

        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storagesize) {
            System.out.println("Хранилище переполнено");
        } else {
            if (searchindex(r.getUuid()) == -1) {

                storage[size] = r;
                size++;
            } else {
                System.out.println("Такое резюмен уже существует" + r.getUuid());
            }
        }

    }

    public Resume get(String uuid) {
        if (searchindex(uuid) != -1) {
            return storage[searchindex(uuid)];
        }

        return null;
    }

    public void delete(String uuid) {
        if (searchindex(uuid) != -1) {
            storage[searchindex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Такого резюме нет " + uuid);
        }


    }

    public Resume[] getAll() {


        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

}

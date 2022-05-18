package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

   public void update(Resume resume) {
       boolean check = false;
       for (int i = 0; i < size ; i++) {
           if(storage[i].getUuid() == resume.getUuid()) {
               storage[i] = resume;
               check = true;
           }
       }
       if (check != true) {
           System.out.println("Такого резюме нет " + resume.getUuid());
       }

    }

   public void clear() {
        if (size == 0) {
            return;
        }
       Arrays.fill(storage,0,size,null);

    }

   public void save(Resume r) {
       if (size == storage.length) {
           System.out.println("Хранилище переполнено");
           return;
       }
       for (int i = 0; i < size; i++) {
           if (storage[i] == r) {
               System.out.println("Такое резюме уже существует " + r.getUuid());
               return;
           }
       }

        storage[size] = r;
        size++;
    }

   public Resume get(String uuid) {
       boolean check = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                check = true;
                return storage[i];

            }
        }
       if (check != true) {
           System.out.println("Такого резюме нет " + uuid );
       }
        return null;
    }

    public void delete(String uuid) {
       boolean check = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                check = true;
            }
        }
        if (check != true) {
            System.out.println("Такого резюме нет " + uuid);
        }

    }

   public Resume[] getAll() {
       Resume[] resume = Arrays.copyOf(storage, size);

        return resume;
    }

   public int size() {
        return size;
    }

}

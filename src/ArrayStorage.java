/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
        size++;
    }

    Resume get(String uuid) {
        if (size == 0) {
            return null;
        }
        Resume r = null;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid) {
                r = storage[i];
                break;
            } else {
                return null;
            }
        }

        return r;
    }

    void delete(String uuid) {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;
                break;
            }
        }
        for (int j = 0; j < storage.length; j++) {
            if (j == storage.length - 1) {
                break;
            }
            if (storage[j] == null && storage[j + 1] != null) {
                storage[j] = storage[j + 1];
                storage[j + 1] = null;
            }


        }
        size--;
    }


    Resume[] getAll() {
        int w = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                w++;
            }
        }
        Resume[] resume = new Resume[w];
        for (int j = 0; j < storage.length; j++) {
            if (storage[j] != null) {
                resume[j] = storage[j];
            }
        }


        return resume;
    }

    int size() {
        return size;
    }

}

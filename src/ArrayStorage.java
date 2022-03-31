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
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size == 0) {
            storage[0] = r;
        } else {
            storage[size] = r;
        }
        size++;
    }

    Resume get(String uuid) {
        if (size == 0) {
            return null;
        }
        Resume r = null;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                r = storage[i];
                break;
            }
        }

        return r;
    }

    void delete(String uuid) {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;

                for (int j = 0; j < size; j++) {
                    if (j == size - 1) {
                        break;
                    } else {
                        if (storage[j] == null && storage[j + 1] != null) {
                            storage[j] = storage[j + 1];
                            storage[j + 1] = null;
                        }
                    }
                }

                size--;
            }
        }
    }


    Resume[] getAll() {
        Resume[] resume;
        if (size == 0) {
            resume = new Resume[0];
        } else {
            resume = new Resume[size];
            for (int j = 0; j < size; j++) {
                resume[j] = storage[j];
            }
        }
        return resume;
    }

    int size() {
        return size;
    }

}

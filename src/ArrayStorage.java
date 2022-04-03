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

        storage[size] = r;

        size++;
    }

    Resume get(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }

        return null;
    }

    void delete(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;

                for (int j = i; j < size - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
                size--;
                break;
            }
        }


    }


    Resume[] getAll() {
        Resume[] resume;

        resume = new Resume[size];
        for (int j = 0; j < size; j++) {
            resume[j] = storage[j];
        }

        return resume;
    }

    int size() {
        return size;
    }

}

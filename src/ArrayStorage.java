/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        try {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].uuid == uuid) {
                    r = storage[i];
                    break;
                }
            }
        } catch (NullPointerException e) {
        }
        return r;
    }

    void delete(String uuid) {
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
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
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
        int w = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                w++;
            }
        }
        return w;
    }
}

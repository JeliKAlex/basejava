import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && uuid.equals(resume.toString())) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int len = storage.length;
        for (int i = 0; i < len; i++) {
            if (storage[i] != null && uuid.equals(storage[i].toString())) {
                System.arraycopy(storage, i + 1, storage, i, len - 1 - i);
                storage[len - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                size++;
            }
        }
        return size;
    }
}

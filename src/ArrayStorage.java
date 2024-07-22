import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size;

    void clear() {
        Arrays.fill(storage, 0, size(), null);
        size = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        if (r.toString() != null) {
            size++;
        }
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
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                if (i != size - 1) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                } else {
                    storage[i] = null;
                }
                size--;
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
        return size;
    }
}

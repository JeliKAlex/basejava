package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено!");
        } else {
            System.out.println("ОШИБКА: " + resume.getUuid() + " не найден!");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("ОШИБКА: хранилище полностью заполнено, невозможно сохранить " + resume.getUuid() + "!");
        } else if (isExist(getIndex(resume.getUuid()))) {
            System.out.println("ОШИБКА: " + resume.getUuid() + " уже существует в системе!");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Резюме " + resume.getUuid() + " добавлено!");

        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме " + uuid + " удалено!");
            return;
        }
        System.out.println("ОШИБКА: " + uuid + " не найден!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExist(int index) {
        return index >= 0 && storage[index] != null;
    }
}

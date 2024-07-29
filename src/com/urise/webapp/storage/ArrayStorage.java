package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено!");
        } else {
            System.out.println("ОШИБКА: " + resume.getUuid() + " не найден!");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("ОШИБКА: хранилище полностью заполнено, невозможно сохранить " + resume.getUuid() + "!");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("ОШИБКА: " + resume.getUuid() + " уже существует в системе!");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Резюме " + resume.getUuid() + " добавлено!");
            
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("ОШИБКА: " + uuid + " не найден!");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
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

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (isExist(i) && uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExist(int index) {
        return storage[index] != null;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];

    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if(complianceCheck(resume)) {
            System.out.println("Резюме " + resume.getUuid() + " обновлено!");
        } else {
            notifyNotFound(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            if (complianceCheck(resume)) {
                System.out.println("ОШИБКА: " + resume.getUuid() + " уже существует в системе!");
            } else {
                if (resume.toString() != null) {
                    storage[size] = resume;
                    size++;
                    System.out.println("Резюме " + resume.getUuid() + " добавлено!");
                }
            }
        } else {
            System.out.println("ОШИБКА: хранилище полностью заполнено, невозможно сохранить " + resume.getUuid() + "!");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return storage[i];
            }
        }
        System.out.println("ОШИБКА: " + uuid + " не найден!");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                System.out.println("Резюме " + uuid + " удалено!");
                return;
            }
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

    private boolean complianceCheck(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && resume.getUuid().equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }

    private void notifyNotFound(String uuid) {
        System.out.println("ОШИБКА: " + uuid + " не найден!");
    }

}

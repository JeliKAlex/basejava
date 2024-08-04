package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == storage.length) {
            System.out.println("ОШИБКА: хранилище полностью заполнено, невозможно сохранить " + resume.getUuid() + "!");
        } else if (isExist(index)) {
            System.out.println("ОШИБКА: " + resume.getUuid() + " уже существует в системе!");
        } else {
            InsertElement(resume, index);
            size++;
            System.out.println("Резюме " + resume.getUuid() + " добавлено!");

        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            FillDeleteElement(index);
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме " + uuid + " удалено!");
            return;
        }
        System.out.println("ОШИБКА: " + uuid + " не найден!");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected boolean isExist(int index) {
        return index >= 0 && storage[index] != null;
    }

    protected abstract void InsertElement(Resume resume, int index);

    protected abstract void FillDeleteElement(int index);
}


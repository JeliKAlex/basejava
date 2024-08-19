package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (isExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
            System.out.println("Resume " + resume.getUuid() + " added!");

        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            fillDeleteElement(index);
            storage[size - 1] = null;
            size--;
            System.out.println("Resume " + uuid + " removed!");
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isExist(int index) {
        return index >= 0 && storage[index] != null;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeleteElement(int index);
}


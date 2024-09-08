package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeleteElement(int index);

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void doUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    public final void doSave(Resume resume, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, (int) searchKey);
            size++;
        }
    }

    public final Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    public final void doDelete(Object searchKey) {
        fillDeleteElement((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    public final List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public final List<Resume> getAllSorted() {
        return List.of(Arrays.copyOf(storage, size));
    }

    public final int size() {
        return size;
    }

    public final boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }
}


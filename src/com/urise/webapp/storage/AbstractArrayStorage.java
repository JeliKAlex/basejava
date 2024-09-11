package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    public final void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    public final void doSave(Resume resume, Integer searchKey) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, searchKey);
            size++;
        }
    }

    public final Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    public final void doDelete(Integer searchKey) {
        fillDeleteElement(searchKey);
        storage[size - 1] = null;
        size--;
    }

    public final List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public final int size() {
        return size;
    }

    public final boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}


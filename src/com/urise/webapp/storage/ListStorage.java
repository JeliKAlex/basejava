package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    public List<Resume> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public final void update(Resume resume) {
        int index = getIndex(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            list.add(index, resume);
        }
    }

    public final Resume get(Resume resume) {
        if (list.contains(resume)) {
            return resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void save(Resume resume) {
        if (list.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        }
        list.add(resume);
    }

    public final void delete(Resume resume) {
        if (list.contains(resume)) {
            list.remove(resume);
            System.out.println("Resume " + resume.getUuid() + " removed!");
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    public int size() {
        return list.size();
    }

    private int getIndex(Resume resume) {
        return list.indexOf(resume);
    }

}




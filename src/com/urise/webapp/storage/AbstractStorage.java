package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> doCopyAll();

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistedKey(resume.getUuid());
        doUpdate(resume, searchKey);
        System.out.println("Resume " + resume.getUuid() + " updated!");
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistedKey(resume.getUuid());
        doSave(resume, searchKey);
        System.out.println("Resume " + resume.getUuid() + " added!");
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedKey(uuid);
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedKey(uuid);
        doDelete(searchKey);
        System.out.println("Resume " + uuid + " removed!");
    }

    private SK getExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume resume, String uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void doSave(Resume resume, String uuid) {
        map.putIfAbsent((String) uuid, resume);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get((String) uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey((String) uuid);
    }
}

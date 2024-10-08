package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.urise.webapp.model.ResumeTestData.fillData;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final String PATH_DIRECTORY = "D:\\Java\\javaOps\\basejava\\storage";

    protected static final File STORAGE_DIRECTORY = new File("D:\\Java\\javaOps\\basejava\\storage");
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULL_NAME_1 = "Name 1";
    private static final String FULL_NAME_2 = "Name 2";
    private static final String FULL_NAME_3 = "Name 3";
    private static final String FULL_NAME_4 = "Name 4";

    private static Resume RESUME_1;
    private static Resume RESUME_2;
    private static Resume RESUME_3;
    private static Resume RESUME_4;


    private final String dummy = "dummy";

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        RESUME_1 = fillData(UUID_1, FULL_NAME_1);
        RESUME_2 = fillData(UUID_2, FULL_NAME_2);
        RESUME_3 = fillData(UUID_3, FULL_NAME_3);
        RESUME_4 = fillData(UUID_4, FULL_NAME_4);
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "Name 1");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(dummy);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertGet(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(dummy);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3)));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
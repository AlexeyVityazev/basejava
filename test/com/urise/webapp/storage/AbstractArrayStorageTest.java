package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    Storage storage;
    Resume resume = new Resume();

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Resume resume = storage.get("uuid2");
        Assert.assertEquals("uuid2", resume.getUuid());

    }

    @Test
    public void getNotExist() {

        Assert.assertEquals(null, storage.get("dummy"));
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        Resume resume = storage.get("uuid4");
        Assert.assertEquals("uuid4", resume.getUuid());
    }

    @Test
    public void overFlow() {
        storage.clear();
        String id = "uuid";
        try {
            for (int i = 0; i < 10_000; i++) {
                String index = String.valueOf(i);
                String uuid = id + index;
                Resume resume = new Resume(uuid);
                storage.save(resume);
            }

        } catch (Exception e) {
            Assert.fail("Тест не прошел");
        }
        try {
            Resume theLast = new Resume("last");
            storage.save(theLast);
        } catch (Exception e) {
            System.out.println("Переполнение");
            e.printStackTrace();

        }

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
        storage.delete("uuid2");
        Assert.assertEquals(null, storage.get("uuid2"));
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        Assert.assertEquals(array.length, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Resume[] resumes = storage.getAll();
        for (int i = 0; i < resumes.length; i++) {
            if (resumes[i] != null) {
                Assert.fail("Тест не прошел");
            }

        }
    }

}

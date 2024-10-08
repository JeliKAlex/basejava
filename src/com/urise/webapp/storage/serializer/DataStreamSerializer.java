package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataos = new DataOutputStream(outputStream)) {
            dataos.writeUTF(resume.getUuid());
            dataos.writeUTF(resume.getFullName());
            writeCollection(dataos, resume.getContacts().entrySet(), entry -> {
                dataos.writeUTF(entry.getKey().name());
                dataos.writeUTF(entry.getValue());
            });
            writeCollection(dataos, resume.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dataos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dataos.writeUTF(((StringSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dataos, ((ListSection) section).getItems(),
                                dataos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dataos, ((OrganizationSection) section).getOrganizations(),
                                organization -> {
                                    dataos.writeUTF(organization.getHomePage().getTitle());
                                    dataos.writeUTF(organization.getHomePage().getUrl());
                                    writeCollection(dataos, organization.getPeriods(), periods -> {
                                        writeLocalDate(dataos, periods.getStartDate());
                                        writeLocalDate(dataos, periods.getEndDate());
                                        dataos.writeUTF(periods.getTitle());
                                        dataos.writeUTF(periods.getContent());
                                    });
                                });
                        break;
                }
            });
        }
    }

    private <T> void writeCollection(DataOutputStream dataos, Collection<T> collection, ElementWriter<T> writer)
            throws IOException {
        dataos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void writeLocalDate(DataOutputStream dataos, LocalDate date) throws IOException {
        dataos.writeInt(date.getYear());
        dataos.writeInt(date.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream datais = new DataInputStream(inputStream)) {
            Resume resume = new Resume(datais.readUTF(), datais.readUTF());
            readItems(datais, () -> {
                resume.setContacts(ContactType.valueOf(datais.readUTF()), datais.readUTF());
            });
            readItems(datais, () -> {
                SectionType sectionType = SectionType.valueOf(datais.readUTF());
                resume.setSections(sectionType, readSection(datais, sectionType));
            });
            return resume;
        }
    }

    private void readItems(DataInputStream datais, ElementProcessor processor) throws IOException {
        int size = datais.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private Section readSection(DataInputStream datais, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new StringSection(datais.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(datais, datais::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(
                        readList(datais, () -> new Organization(
                                new Link(datais.readUTF(), datais.readUTF()),
                                readList(datais, () -> new Period(
                                        readLocalDate(datais), readLocalDate(datais), datais.readUTF(), datais.readUTF()
                                ))
                        )));
            default:
                throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream datais, ElementReader<T> reader) throws IOException {
        int size = datais.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private LocalDate readLocalDate(DataInputStream datais) throws IOException {
        return LocalDate.of(datais.readInt(), datais.readInt(), 1);
    }

}

package com.stquiz.dao;

import com.stquiz.domain.QuizElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.MissingResourceException;

import com.sun.tools.javac.Main;
import org.springframework.lang.NonNull;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.Trim;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class QuizElementDaoImpl implements QuizElementDao {
    private final String dataResourceKey;

    public QuizElementDaoImpl(String dataResourceKey) {
        this.dataResourceKey = dataResourceKey;
    }

    @NonNull
    @Override
    public Collection<QuizElement> getQuizElements() {
        QuizEntriesContainer entriesContainer = new QuizEntriesContainer();

        InputStream resourceStream = QuizElementDaoImpl.class.getClassLoader().getResourceAsStream(dataResourceKey);
        if (resourceStream == null) {
            throw new MissingResourceException("Missing resource: " + dataResourceKey, Main.class.getName(), dataResourceKey);
        }

        // the header elements are used to map the values to CsvEntry (names must match)
        String[] header = {"Id", "Type", "Text"};

        CellProcessor[] cellProcessors = new CellProcessor[] {
            new ParseInt(),
            new ParseEnum(QuizEntryType.class),
            new Trim()
        };

        try (ICsvBeanReader beanReader =
             new CsvBeanReader(new InputStreamReader(resourceStream), CsvPreference.STANDARD_PREFERENCE))
        {
            QuizEntry entry;
            while( (entry = beanReader.read(QuizEntry.class, header, cellProcessors)) != null ) {
                entriesContainer.addEntry(entry);
            }
        } catch (IOException e) {
            throw new QuizElementDaoException(e);
        }

        return entriesContainer.getQuizElements();
    }
}

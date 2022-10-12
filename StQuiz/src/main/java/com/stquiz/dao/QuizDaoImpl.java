package com.stquiz.dao;

import com.stquiz.domain.QuizElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.MissingResourceException;

import com.sun.tools.javac.Main;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.Trim;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class QuizDaoImpl implements QuizDao {
    private final String dataResourceKey;

    public QuizDaoImpl(String dataResourceKey) {
        this.dataResourceKey = dataResourceKey;
    }

    @Override
    public Collection<QuizElement> getQuizElements() throws IOException {
        QuizEntriesContainer entriesContainer = new QuizEntriesContainer();

        ICsvBeanReader beanReader = null;
        try {
            InputStream resourceStream = Main.class.getClassLoader().getResourceAsStream(dataResourceKey);
            if (resourceStream == null) {
                throw new MissingResourceException("Missing resource", Main.class.getName(), dataResourceKey);
            }

            beanReader = new CsvBeanReader(new InputStreamReader(resourceStream), CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the values to CsvEntry (names must match)
            String[] header = {"Id", "Type", "Text"};

            CellProcessor[] cellProcessors = new CellProcessor[] {
                new ParseInt(),
                new ParseEnum(QuizEntryType.class),
                new Trim()
            };

            QuizEntry entry;
            while( (entry = beanReader.read(QuizEntry.class, header, cellProcessors)) != null ) {
                entriesContainer.addEntry(entry);
            }

        } finally {
            if( beanReader != null ) {
                beanReader.close();
            }
        }

        return entriesContainer.getQuizElements();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samfch.lucene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Sam FCH
 */
public class Indexer {

    private final File sourceDirectory;
    private final File indexDirectory;
    private static String fieldContent;
    private static String fieldName;
    public List<String> names;

    public Indexer() {
        this.sourceDirectory = new File(Configuration.SOURCE_DIRECTORY_TO_INDEX);
        this.indexDirectory = new File(Configuration.INDEX_DIRECTORY);
        fieldContent = Configuration.FIELD_CONTENT;
        this.names = new ArrayList<>();
    }

    public void index() throws IOException {
        Directory dir = FSDirectory.open(indexDirectory.toPath());
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        if (indexDirectory.exists()) {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } else {
            // Add new documents to an existing index:
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }

        try ( IndexWriter writer = new IndexWriter(dir, iwc)) {
            int k = 0;

            for (File f : sourceDirectory.listFiles()) {
                Document doc = new Document();

                FieldType fieldType = new FieldType();
                fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
                fieldType.setStored(true);
                fieldType.setStoreTermVectors(true);
                fieldType.setTokenized(true);
                Field contentField = new Field(fieldContent, getAllTextInFiles(f), fieldType);
                doc.add(contentField);

                writer.addDocument(doc);
                names.add(f.getName());
                k++;

            }

        }

    }

    public String getAllTextInFiles(File f) throws FileNotFoundException, IOException {
        String textFileContent = "";

        for (String line : Files.readAllLines(Paths.get(f.getAbsolutePath()))) {
            textFileContent += line;
        }
        return textFileContent;
    }
}

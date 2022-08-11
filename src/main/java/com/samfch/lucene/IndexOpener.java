/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samfch.lucene;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Sam FCH
 */
public class IndexOpener {
    
    public static IndexReader GetIndexReader() throws IOException {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(Configuration.INDEX_DIRECTORY).toPath()));
        return indexReader;
    }

    /**
     * Returns the total number of documents in the index
     * @return
     * @throws IOException 
     */
    public static Integer TotalDocumentInIndex() throws IOException
    {
        Integer maxDoc = GetIndexReader().maxDoc();
        GetIndexReader().close();
        return maxDoc;
    }
}

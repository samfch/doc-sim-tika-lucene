/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samfch.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;

/**
 *
 * @author Sam FCH
 */
public class AllTerms {

    private Map<String, Integer> allTerms;
    Integer totalNoOfDocumentInIndex;
    IndexReader indexReader;

    public AllTerms() throws IOException {
        allTerms = new HashMap<>();
        indexReader = IndexOpener.GetIndexReader();
        totalNoOfDocumentInIndex = IndexOpener.TotalDocumentInIndex();
    }

    public void initAllTerms() throws IOException {
        int pos = 0;
        for (int docId = 0; docId < totalNoOfDocumentInIndex; docId++) {
            Terms vector = indexReader.getTermVector(docId, Configuration.FIELD_CONTENT);
            TermsEnum termsEnum = null;
            termsEnum = vector.iterator();
            BytesRef text = null;
            while ((text = termsEnum.next()) != null) {
                String term = text.utf8ToString();
                allTerms.put(term, pos++);
            }
        }

        //Update postition
        pos = 0;
        for (Map.Entry<String, Integer> s : allTerms.entrySet()) {
//            System.out.println(s.getKey());
            s.setValue(pos++);
        }
    }

    public Map<String, Integer> getAllTerms() {
        return allTerms;
    }
}

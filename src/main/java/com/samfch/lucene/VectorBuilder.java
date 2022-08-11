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
public class VectorBuilder {

    DocVector[] docVector;
    private Map allterms;
    Integer totalNoOfDocumentInIndex;
    IndexReader indexReader;

    public VectorBuilder() throws IOException {
        allterms = new HashMap<>();
        indexReader = IndexOpener.GetIndexReader();
        totalNoOfDocumentInIndex = IndexOpener.TotalDocumentInIndex();
        docVector = new DocVector[totalNoOfDocumentInIndex];
    }

    public void getAllTerms() throws IOException {
        AllTerms allTerms = new AllTerms();
        allTerms.initAllTerms();
        allterms = allTerms.getAllTerms();
    }

    public DocVector[] getDocumentVectors() throws IOException {
        for (int docId = 0; docId < totalNoOfDocumentInIndex; docId++) {
            Terms vector = indexReader.getTermVector(docId, Configuration.FIELD_CONTENT);
            TermsEnum termsEnum = null;
            termsEnum = vector.iterator();
            BytesRef text = null;
            docVector[docId] = new DocVector(allterms);
            while ((text = termsEnum.next()) != null) {
                String term = text.utf8ToString();
                int freq = (int) termsEnum.totalTermFreq();
                docVector[docId].setEntry(term, freq);
            }
            docVector[docId].normalize();
        }
        indexReader.close();
        return docVector;
    }
}

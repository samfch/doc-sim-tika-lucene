/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.samfch.sample.doc.sim;

import com.samfch.lucene.DocVector;
import com.samfch.lucene.Indexer;
import com.samfch.lucene.VectorBuilder;
import com.samfch.lucene.similarity.CosineSimilarity;
import com.samfch.tika.FilesParser;
import java.io.IOException;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author Sam FCH
 */
public class MultiTypeDocumentSimilarity {
    
    public static void main(String[] args) throws IOException, LockObtainFailedException, TikaException, SAXException {
        getCosineSimilarity();
    }
    
    public static void getCosineSimilarity() throws LockObtainFailedException, IOException, TikaException, SAXException {

//        String dir = System.getProperty("user.dir");
//        System.out.println(dir);
        FilesParser fp = new FilesParser();
        fp.parseFiles();
        Indexer index = new Indexer();
        index.index();
//        System.out.println("LISTS DOCUMENTS 0 " + index.names.get(0));

        VectorBuilder vectorGenerator = new VectorBuilder();
        vectorGenerator.getAllTerms();
        DocVector[] docVector = vectorGenerator.getDocumentVectors(); // getting document vectors

        for (int i = 0; i < docVector.length; i++) {
//            System.out.println("Vector" + i + " = " + docVector[i].toString());
            double cosineSimilarity = CosineSimilarity.distance(docVector[4], docVector[i]);
            System.out.println("Cosine Similarity Score between document : "+ index.names.get(4) + " <-> " + index.names.get(i) + "  = " + cosineSimilarity);
        }
    }
}

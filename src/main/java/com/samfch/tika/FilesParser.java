/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samfch.tika;

import com.samfch.lucene.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author Sam FCH
 */
public class FilesParser {

    private final File sourceDirectoryRawFiles;
    private final FilesWriter filesWriter;

    public FilesParser() {
        sourceDirectoryRawFiles = new File(Configuration.SOURCE_DIRECTORY_RAW_FILES);
        filesWriter = new FilesWriter();
    }

    public void parseFiles() throws IOException, TikaException, SAXException {
        for (File f : sourceDirectoryRawFiles.listFiles()) {
            System.out.println("Parsing files : " + f.getName());
            String content = extractTextFromFIle(f.getName());
            String newFileName = FilenameUtils.removeExtension(f.getName()) + ".txt";
            System.out.println("Writing files : " + newFileName);
            filesWriter.write(newFileName, content);
        }
    }

    public String extractTextFromFIle(String fileName)
            throws IOException, TikaException, SAXException {

        Parser parser = new AutoDetectParser();
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        try ( InputStream stream = new FileInputStream(Configuration.SOURCE_DIRECTORY_RAW_FILES + "/" + fileName)) {
            parser.parse(stream, handler, metadata, context);
            return cleanSpaces(handler.toString());
        }

    }

    private static String cleanSpaces(String result) {
        return result.replaceAll("[\\n\t ]", " ").replaceAll("[ ]+", " ");
    }
}

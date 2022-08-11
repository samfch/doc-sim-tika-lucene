/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samfch.tika;

import com.samfch.lucene.Configuration;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Sam FCH
 */
public class FilesWriter {

    public void write(String fileName, String content)
            throws IOException {
        try ( BufferedWriter writer = new BufferedWriter(
                new FileWriter(Configuration.SOURCE_DIRECTORY_TO_INDEX + "/" + fileName))) {
            writer.write(content);
        }
    }
}

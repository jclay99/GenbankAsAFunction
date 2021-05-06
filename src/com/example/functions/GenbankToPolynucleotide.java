package com.example.functions;

import java.io.File;
import java.util.LinkedHashMap;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.GenbankReaderHelper;

/** This class reads Genbank files and converts them to Polynucleotide
 *  objects, or models describing linear, circular, double stranded, and
 *  single stranded DNA sequences. The Model class is imported from the
 *  biojava library, an open source library for DNA sequencing. */

public class GenbankToPolynucleotide {

    public static void main(String[] args) {
        convert("/example_files/pAPAPABCbc.gb");
    }

    /** Reads the file given by the input filePath. Inspiration for reading
     *  file sourced from repository biojava-tutorial.
     *
     *  NOTE: this method should never have to be called. It is called for you
     *  in convert(). */
    public static void readFile(String filePath) {
        LinkedHashMap<String, DNASequence> dnaSequences = GenbankReaderHelper.readGenbankDNASequence(new File(filePath));
        for (DNASequence sequence : dnaSequences.values()) {
            System.out.println(sequence.getSequenceAsString());
        }
    }

    /** Takes the input genbankFile (assigned in readFile()) and converts
     *  it to a Polynucleotide (Model) file. */
    public static void convert(String inputFilePath) {
        readFile(inputFilePath);
    }

}

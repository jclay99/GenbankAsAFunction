package com.example.GenbankAsAFunction;

/** This class reads Polynucleotide objects, or models describing linear,
 *  circular, double stranded, and single stranded DNA sequences, and converts
 *  them to annotated Genbank files. The Model class is imported from the
 *  biojava library, an open source library for DNA sequencing. */

import java.io.File;
import org.biojava.nbio.structure.Model;

public class PolynucleotideToGenbank {

    /** Instantiates the PolynucleotideFile variable; this variable is assigned
     *  in readFile(). */
    private static Model polynucleotideFile;

    public static void main(String[] args) {

    }

    /** Assigns the input file path (as a String) to the polynucleotideFile
     *  variable. This variable can be converted to Genbank in convert(). */
    public static void readFile(String filePath) {

        File dnaFile = new File("src/test/resources/NM_000266.gb");
        File protFile = new File("src/test/resources/BondFeature.gb");

        LinkedHashMap<String, DNASequence> dnaSequences =
                GenbankReaderHelper.readGenbankDNASequence( dnaFile );
        for (DNASequence sequence : dnaSequences.values()) {
            System.out.println( sequence.getSequenceAsString() );
        }

        LinkedHashMap<String, ProteinSequence> protSequences =
                GenbankReaderHelper.readGenbankProteinSequence(protFile);
        for (ProteinSequence sequence : protSequences.values()) {
            System.out.println( sequence.getSequenceAsString() );

    }

    /** Takes the input polynucleotideFile (assigned in readFile()) and converts
     *  it to a Genbank file. */
    public static void convert() {

    }

}

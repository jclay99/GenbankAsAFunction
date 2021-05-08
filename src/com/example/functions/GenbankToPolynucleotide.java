package com.example.functions;

import java.awt.*;
import java.io.File;
import java.util.LinkedHashMap;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.GenbankReaderHelper;
import org.biojava.nbio.structure.Model;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.Group;
import org.biojava.nbio.structure.Atom;

/** This class reads Genbank files and converts them to Polynucleotide
 *  objects, or models describing linear, circular, double stranded, and
 *  single stranded DNA sequences. The Model class is imported from the
 *  biojava library, an open source library for DNA sequencing.
 *
 *  CREDITS: the online library biojava was used heavily in the implementation of
 *  this project. The GitHub tutorial repository biojava-tutorial (found at
 *  https://github.com/biojava/biojava-tutorial) was used to understand how to read
 *  and write Genbank files with biojava, as well as how to use the various classes. */

public class GenbankToPolynucleotide {

    /** Users can call this class with the argument following being the
     *  file path to a Genbank file. */
    public static void main(String[] args) throws Exception {
        convert(args[0]);
    }

    /** Reads the file given by the input filePath. Inspiration for reading
     *  file sourced from repository biojava-tutorial.
     *
     *  NOTE: this method should never have to be called. It is called for you
     *  in convert(). */
    public static LinkedHashMap<String, DNASequence> readFile(String filePath) throws Exception {
        LinkedHashMap<String, DNASequence> genbankSequences = GenbankReaderHelper.readGenbankDNASequence(new File(filePath));
        return genbankSequences;
    }

    /** Takes in a series of nucleotide sequences and converts them into
     *  amino acid sequences to be used in the Polynucleotide Model object.
     *
     *  NOTE: this method should never have to be called. It is called for you
     *  in convert(). */
    public static Model writeFile(LinkedHashMap<String, DNASequence> inputSequences) {
        Model returnModel = new Model();
        for (DNASequence dna : inputSequences.values()) {
            String dnaString = dna.getSequenceAsString();
            List<Atom> atomList = new List<Atom>();

            /** Iterate over the string to create atoms. */
            for (int i = 0; i < dnaString.length(); i++) {
                Atom newAtom = new Atom();
                newAtom.setName(String.valueOf(dnaString.charAt(i)));
                atomList.add(newAtom);
            }

            /** Create atom groups and add them to a chain to be added to the model. */
            Group nucleotideGroup = new Group();
            nucleotideGroup.setAtoms(atomList);
            Chain nextChain = new Chain();
            nextChain.addGroup(nucleotideGroup);
            returnModel.addChain(nextChain);
        }
        return returnModel;
    }

    /** Takes the input genbankFile (assigned in readFile()) and converts
     *  it to a Polynucleotide (Model) file. */
    public static Model convert(String inputFilePath) throws Exception {
        LinkedHashMap<String, DNASequence> genbankDNASequences = readFile(inputFilePath);
        return writeFile(genbankDNASequences);
    }

}

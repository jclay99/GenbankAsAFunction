package com.example.functions;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.*;

import org.biojava.nbio.structure.Model;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.GenbankWriterHelper;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.Group;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;

/** This class reads Polynucleotide objects, or models describing linear,
 *  circular, double stranded, and single stranded DNA sequences, and converts
 *  them to annotated Genbank files. The Model class is imported from the
 *  biojava library, an open source library for DNA sequencing.
 *
 *  CREDITS: the online library biojava was used heavily in the implementation of
 *  this project. The GitHub tutorial repository biojava-tutorial (found at
 *  https://github.com/biojava/biojava-tutorial) was used to understand how to read
 *  and write Genbank files with biojava, as well as how to use the various classes. */

public class PolynucleotideToGenbank {

    public static void main(String[] args) {

    }

    /** Reads the file assigned to polynucleotideFile.
     *
     *  NOTE: this method should never have to be called. It is called for you
     *  in convert(). */
    public static List<DNASequence> readFile(Model polynucleotideFile) throws CompoundNotFoundException {
        List<Chain> chainList = polynucleotideFile.getChains();
        List<String> stringList = new List<String>();
        for (Chain chain : chainList) {
            List<Group> groupList = chain.getAtomGroups();
            for (Group group : groupList) {
                List<Atom> atomList = group.getAtoms();
                String atomString = "";
                for (Atom atom : atomList) {
                    String atomName = atom.getName();
                    atomString = atomString + atomName;
                }
                stringList.add(atomString);
            }
        }
        List<DNASequence> dnaSequences = new List<DNASequence>();
        for (String string : stringList) {
            DNASequence insertSequence = new DNASequence(string);
            dnaSequences.add(insertSequence);
        }
        return dnaSequences;
    }

    /** Writes the given DNA sequences to a Genbank text string.
     *
     *  NOTE: this method should never have to be called. It is called for you
     *  in convert(). */
    public static String writeFile(List<DNASequence> inputSequences) throws Exception {
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        ArrayList<DNASequence> dnaSequences = new ArrayList<DNASequence>();
        for(DNASequence seq : inputSequences) {
            dnaSequences.add(seq);
        }

        GenbankWriterHelper.writeNucleotideSequence(writer, dnaSequences,
                GenbankWriterHelper.LINEAR_DNA);

        return writer.toString();
    }

    /** Takes the input polynucleotideFile (assigned in readFile()) and converts
     *  it to a Genbank text string. */
    public static String convert(Model inputPolynucleotide) throws Exception {
        List<DNASequence> dnaSequences = readFile(inputPolynucleotide);
        return writeFile(dnaSequences);
    }

}

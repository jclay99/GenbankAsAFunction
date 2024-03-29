/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 */
package org.biojava.nbio.structure.io.mmtf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.biojava.nbio.structure.AminoAcidImpl;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.AtomImpl;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.ChainImpl;
import org.biojava.nbio.structure.Element;
import org.biojava.nbio.structure.EntityInfo;
import org.biojava.nbio.structure.Group;
import org.biojava.nbio.structure.PDBHeader;
import org.biojava.nbio.structure.ResidueNumber;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureImpl;
import org.biojava.nbio.structure.chem.ChemComp;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.TemporaryFolder;

/**
 * Test the Biojava MMTF writer.
 *
 * @author Anthony Bradley
 * @author Aleix Lafita
 *
 */
public class TestMmtfStructureWriter {

	/**
	 * A test folder for testing writing files.
	 */
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();


	/**
	 * Test that Biojava can read a file from the file system.
	 * @throws IOException
	 */
	@Test
	public void testRead() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		Structure structure = MmtfActions.readFromFile((Paths.get(classLoader.getResource("org/biojava/nbio/structure/io/mmtf/4CUP.mmtf").getPath())));
		assertEquals(structure.getPDBCode(),"4CUP");
		assertEquals(structure.getChains().size(),6);
	}

	/**
	 * Test the writing of Structure objects to a file.
	 * @throws IOException
	 */
	@Test
	public void testWrite() throws IOException {

		// Create a structure
		Structure structure = new StructureImpl();

		// Add some header information
		PDBHeader pdbHeader = new PDBHeader();
		pdbHeader.setExperimentalTechnique("X-RAY DIFFRACTION");
		structure.setPDBHeader(pdbHeader);

		// Create one chain
		structure.setEntityInfos(new ArrayList<EntityInfo>());
		Chain chain = new ChainImpl();
		chain.setId("A");
		chain.setName("A");
		Group group = new AminoAcidImpl();
		group.setPDBName("FKF");
		ChemComp chemComp = new ChemComp();
		chemComp.setType("TYPfdl");
		chemComp.setOneLetterCode("A");
		group.setChemComp(chemComp);

		// Create one Atom
		Atom atom = new AtomImpl();
		atom.setName("A");
		atom.setElement(Element.Ag);
		atom.setCoords(new double[] { 1.0, 2.0, 3.0 });

		// Link together the objects
		chain.addGroup(group);
		group.addAtom(atom);

		ResidueNumber residueNumber = new ResidueNumber();
		residueNumber.setInsCode('A');
		residueNumber.setSeqNum(100);
		group.setResidueNumber(residueNumber);

		structure.addChain(chain);

		File tempFile = testFolder.newFile("tmpfile");
		MmtfActions.writeToFile(structure, tempFile.toPath());
	}
}

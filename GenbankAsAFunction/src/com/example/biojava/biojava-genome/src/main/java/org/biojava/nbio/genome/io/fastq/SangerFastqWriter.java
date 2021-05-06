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
package org.biojava.nbio.genome.io.fastq;

/**
 * Writer for {@link FastqVariant#FASTQ_SANGER} formatted sequences.
 *
 * @since 3.0.3
 */
public final class SangerFastqWriter
	extends AbstractFastqWriter
{

	@Override
	protected Fastq convert(final Fastq fastq)
	{
		return fastq.convertTo(FastqVariant.FASTQ_SANGER);
	}
}

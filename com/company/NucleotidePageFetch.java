// CREDIT TO DR. PHILIP HELLER FOR PROVIDING US WITH THIS CODE
// lines getNucleotideGPPage() were commented out due to error with throwing a new ConversionException

package com.company;

import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;


public class NucleotidePageFetch
{	
	private String					surl;
	private URLConnection			urlConn;
	private InputStreamReader		isr;
	
	
	public NucleotidePageFetch(String surl)		{ this.surl = surl; }

	
	public static NucleotidePageFetch forInitiateGPLookup(String accession)
	{
		String surl = buildInitiateGPLookupSurl(accession);
		return new NucleotidePageFetch(surl);
	}
		
		
	private static String buildInitiateGPLookupSurl(String accession)	
	{
		return "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?" +
			"&rettype=gp&usehistory=n&db=nucleotide&term=" + accession;
	}
	
	
	public static NucleotidePageFetch forRetrieveGPFromEntrez(String accession)
	{
		String surl = buildRetrieveGPFromEntrez(accession);
		return new NucleotidePageFetch(surl);
	}

		
	private static String buildRetrieveGPFromEntrez(String euID)	
	{
		return "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?" +
			"&rettype=gp&db=nuccore&id=" + euID;
	}
	
	
	public static String getNucleotideGPPage(String accessionOrGI) throws IOException, ConversionException
	{		
		// Use eUtils to retrieve .gp page. 1st response page is XML. Retrieve 1st ID in <ID> tag.
		NucleotidePageFetch client = forInitiateGPLookup(accessionOrGI);
		String eutilsInitialResponse = client.getResponsePageAsString();
		//if (eutilsInitialResponse == null)
		//	throw new ConversionException(ConversionFailure.PROTEIN_GP_PAGE_NO_INITIAL_RESPONSE);
		int index = eutilsInitialResponse.indexOf("<Id>");
		//if (index < 0)
		//	throw new ConversionException(ConversionFailure.PROTEIN_GP_PAGE_NO_ID_TAG_IN_INITIAL_RESPONSE);
		index += "<Id>".length();
		eutilsInitialResponse = eutilsInitialResponse.substring(index);
		String euID = "";
		int n = 0;
		while (Character.isJavaIdentifierPart(eutilsInitialResponse.charAt(n)))
			euID += eutilsInitialResponse.charAt(n++);
		
		// Retrieve the .gp  page from Entrez. It contains a "coded_by" tag that contains 
		// the nucleotide accession #, range, and strand that we need.
		client = forRetrieveGPFromEntrez(euID);
		String gpPage = client.getResponsePageAsString();
		//if (gpPage == null)
		//	throw new ConversionException(ConversionFailure.PROTEIN_GP_PAGE_NO_GP_PAGE);
		return gpPage;
	}
		
	
	public LineNumberReader getLineNumberReaderForResponse() throws MalformedURLException, IOException
	{
		URL url = new URL(surl);		// throws MalformedURLException
		urlConn = url.openConnection();
		isr = new InputStreamReader(urlConn.getInputStream());
		return new LineNumberReader(isr);
	}
	
	
	// Converts IOException to ConversionException.
	public String getResponsePageAsString() throws ConversionException
	{		
		return getResponsePageAsString(-1);
	}

	
	// Converts IOException to ConversionException. Honors maxLines if >0. 
	public String getResponsePageAsString(int maxLines) throws ConversionException
	{
		try
		(
			LineNumberReader lnr = getLineNumberReaderForResponse();
		)
		{
			StringBuilder sb = new StringBuilder();
			String line = null;
			int nLines = 0;
			while ((line = lnr.readLine()) != null)
			{
				sb.append(line);
				if (!line.endsWith("\n"))
					sb.append("\n");
				if (maxLines > 0  &&  ++nLines >= maxLines)
					break;
			}
			return sb.toString();
		}
		catch (IOException x)
		{
			sop("Stress: " + x.getMessage());
			throw new ConversionException(ConversionFailure.NUCLEOTIDE_PAGE_NOT_RECEIVED);
		}
	
	
}
	enum ConversionFailure 
	{
		PROTEIN_GP_PAGE_NO_INITIAL_RESPONSE,
		PROTEIN_GP_PAGE_NO_ID_TAG_IN_INITIAL_RESPONSE,
		PROTEIN_GP_PAGE_NO_GP_PAGE,
		PROTEIN_GP_PAGE_NO_CODED_BY_TAG,
		PROTEIN_GP_PAGE_BAD_NUMBER_FORMAT,
		NUCLEOTIDE_PAGE_NOT_RECEIVED,
		NUCLEOTIDE_PAGE_NOT_CONVERTED_TO_EMBL,
		EMBL_FILE_MISSING
	}
	
	
	class ConversionException extends Exception
	{
		private ConversionFailure		failure;
		
		
		public ConversionException(ConversionFailure failure)
		{
			this.failure = failure;
		}
		
		
		public ConversionException(ConversionFailure failure, String message)
		{
			super(message);
			this.failure = failure;
		}
		
		
		public String toString()
		{
			return "Conversion failure: " + failure;
		}
		
		
		public ConversionFailure getFailureMode()
		{
			return failure;
		}
	}
	
	
	public String getURLString()				{ return surl; }
	static void sop(Object x)					{ System.out.println(x); }
	
	
	public static void main(String[] args) throws Exception
	{
		String acc = "AB002225.1";
		
		try
		{
			String page = getNucleotideGPPage(acc);
			sop(page);
		}
		catch (Exception x)
		{
			sop("STRESS: " + x.getMessage());
			x.printStackTrace();
		}
	}
}

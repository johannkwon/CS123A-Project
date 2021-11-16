# RedwoodProject

Credit to Dr. Philip Heller for providing us with NucleotidePageFetch.java* for grabbing nucleotide pages from Entrez databases.
Due to some complications with GitHub, Johann Kwon mainly pushed files so changes made by Rajiv Iyengar are not tracked.
Rajiv worked on BLASTDatabase.java, BLASTSubject.java, DatabaseOutput.java, and the main.

Before running the project, insert .csv paths in Main.java. Output is a .csv containing all non-duplicate and non-chloroplast subjects.

*ConversionException catching lines getNucleotideGPPage() were commented out due to error with throwing a new ConversionException
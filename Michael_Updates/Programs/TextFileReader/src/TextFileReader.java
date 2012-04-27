import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.File;

/**
 * TextFileReader class
 * 
 * @author Michael Aranda
 * @param textfile to read, textfile to print output to
 * @data last modified: February 01, 2012
 */

public class TextFileReader{
	
	/**
	 * main method takes 2 paramaters: file to be read and file to print to
	 */
	public static void main(String[] args){
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			
			FileWriter outFile = new FileWriter(args[1]);
			PrintWriter out = new PrintWriter(outFile);
			ArrayList<String> labelList = new ArrayList<String>();
			fillArrayList(labelList, 50);
					
			//Code below added specifically for file nif-function-obo.obo on March 18, 2012
			out.print("id,name,namespace,comment,is_a,definition,externalSourceURI,synonym,is_metadata_tag," +
			          "format-version,date,saved-by,auto-generated-by,id_space,editorialNote,title," +
			          "changeNote,subject,contributor,defaultLanguage,creator,UmlsCui," +
			          "hasCurationStatus,hasBirnlexCurator,prefLabel,GOid,createdDate,modifiedDate," +
			          "hasDefinitionSource,birnlexDefinition,example,MeshUid,externallySourcedDefinition," +
			          "relationship,nifID,bonfireID,usageNote,scopeNote,definingCitation,hasExternalSource," +
					  "tempDefinition,gene_Ontology_ID,definingCitationURI,");
			out.println();		
			
			/* Line has been read in, 3 cases
			 * 	1. Line -> [Term] or [Typedef]
			 *  2. Line -> label: ...
			 *  3. Line -> Anything else
			 *  
			 *  Case 1: 
			 *    i. Print out contents of labelList as new line in CSV output file.
			 *    ii. Clear contents of labelList.
			 *  Case 2: 
			 *    i. Add everything after "label:" to index in labelList corresponding
			 *       to label (e.g. "id" -> 0, "namespace" -> 1, etc.)
			 *  Case 3:
			 *    i. Skip.
			 */
			String line;
			
			while((line = in.readLine()) != null){
				
				int indexOfColon = line.indexOf(':');
				
				//Case 1
				if(line.equals("[Term]") || line.equals("[Typedef]")){
					printTermValues(labelList, out);
				}
				
				//Case 2
				else if(indexOfColon > 0){			
					
					String label = line.substring(0,indexOfColon);
					String entry = line.substring(indexOfColon+1);
					int indexToAdd = 0;
					
					if(label.equals("id")){
						indexToAdd = 0;
					}
					else if(label.equals("name")){
						indexToAdd = 1;
					}
					else if(label.equals("namespace")){
						indexToAdd = 2;
					}
					else if(label.equals("comment")){
						indexToAdd = 3;
					}
					else if(label.equals("is_a")){
						indexToAdd = 4;
					}
					else if(label.equals("definition")){
						indexToAdd = 5;
					}
					else if(label.equals("externalSourceURI")){
						indexToAdd = 6;
					}
					else if(label.equals("synonym")){
						indexToAdd = 7;
					}
					else if(label.equals("is_metadata_tag")){
						indexToAdd = 8;
					}
					else if(label.equals("format-version")){
						indexToAdd = 9;
					}
					else if(label.equals("date")){
						indexToAdd = 10;
					}
					else if(label.equals("saved-by")){
						indexToAdd = 11;
					}
					else if(label.equals("auto-generated-by")){
						indexToAdd = 12;
					}
					else if(label.equals("id_space")){
						indexToAdd = 13;
					}
					else if(label.equals("editorialNote")){
						indexToAdd = 14;
					}
					else if(label.equals("title")){
						indexToAdd = 15;
					}
					else if(label.equals("changeNote")){
						indexToAdd = 16;
					}
					else if(label.equals("subject")){
						indexToAdd = 17;
					}
					else if(label.equals("contributor")){
						indexToAdd = 18;
					}
					else if(label.equals("defaultLanguage")){
						indexToAdd = 19;
					}
					else if(label.equals("creator")){
						indexToAdd = 20;
					}
					else if(label.equals("UmlsCui")){
						indexToAdd = 21;
					}
					else if(label.equals("hasCurationStatus")){
						indexToAdd = 22;
					}
					else if(label.equals("hasBirnlexCurator")){
						indexToAdd = 23;
					}
					else if(label.equals("prefLabel")){
						indexToAdd = 24;
					}
					else if(label.equals("GOid")){
						indexToAdd = 25;
					}
					else if(label.equals("createdDate")){
						indexToAdd = 26;
					}
					else if(label.equals("modifiedDate")){
						indexToAdd = 27;
					}
					else if(label.equals("hasDefinitionSource")){
						indexToAdd = 28;
					}
					else if(label.equals("birnlexDefinition")){
						indexToAdd = 29;
					}
					else if(label.equals("example")){
						indexToAdd = 30;
					}
					else if(label.equals("MeshUid")){
						indexToAdd = 31;
					}
					else if(label.equals("externallySourcedDefinition")){
						indexToAdd = 32;
					}
					else if(label.equals("relationship")){
						indexToAdd = 33;
					}
					else if(label.equals("nifID")){
						indexToAdd = 34;
					}
					else if(label.equals("bonfireID")){
						indexToAdd = 35;
					}
					else if(label.equals("usageNote")){
						indexToAdd = 36;
					}
					else if(label.equals("scopeNote")){
						indexToAdd = 37;
					}
					else if(label.equals("definingCitation")){
						indexToAdd = 38;
					}
					else if(label.equals("hasExternalSource")){
						indexToAdd = 39;
					}
					else if(label.equals("tempDefinition")){
						indexToAdd = 40;
					}
					else if(label.equals("gene_Ontology_ID")){
						indexToAdd = 41;
					}


					if(label.equals("definingCitationURI")){
						indexToAdd = 42;
						//Commented code below specifically for CNO_0.2.3.obo
						
						/*String[] splitArray = entry.trim().split(" ");
						indexToAdd = findIndexOfRelationship(splitArray[0]);
						entry = splitArray[1];*/
					}

					//check if this is the first entry into this index of the labelList ArrayList
					if(labelList.get(indexToAdd) != null){
						labelList.set(indexToAdd, labelList.get(indexToAdd) + ";");
						labelList.set(indexToAdd, labelList.get(indexToAdd) + entry);
					}
					else
						labelList.set(indexToAdd, entry);
				}
	
			}
		
			printTermValues(labelList, out);
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void fillArrayList(ArrayList<String> list, int size){
		for(int i = 0; i < size; i++){
			list.add(null);
		}
	}
	
	public static void printTermValues(ArrayList<String> list, PrintWriter fout){
		for(String s : list){
			if(s != null){
				fout.print('\"' + s + '\"');
				fout.print(",");
			}
			else if(s == null){
				fout.print(",");
			}
		}
		list.clear();
		fillArrayList(list, 50);
		fout.println();
	}
	
	/* For translating relationship labels of the form:
	 * 
	 *		relationship: cno_0000005 cno_0000002
	 *
	 * As seen in the file CNO_0.2.obo
	 */
	public static int findIndexOfRelationship(String s){
		if(s.equals("cno_0000005")){
			return 9;
		}
		if(s.equals("cno_0000006")){
			return 10;
		}
		if(s.equals("cno_0000063")){
			return 10;
		}
		if(s.equals("cno_0000064")){
			return 11;
		}
		if(s.equals("cno_0000180")){
			return 12;
		}
		if(s.equals("cno_0000181")){
			return 13;
		}
		if(s.equals("cno_0000202")){
			return 14;
		}
		if(s.equals("cno_0000203")){
			return 15;
		}
		if(s.equals("cno_0000204")){
			return 16;
		}
		if(s.equals("cno_0000205")){
			return 17;
		}

		return 0;
	}

}
package org.subtitle;

import java.io.File;
import java.util.ArrayList;

public class SubtitleRenamer {

	public static void main(String[] args) {
		/** Give the parent folder of the TV series*/
		String folderPath = "C:/Users/PrabhuM/Documents/TV Series/Family Guy";
		renameSubtitleFilesInFolder(folderPath);
	}

	public static void renameSubtitleFilesInFolder(String folderPath) {
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<File> videoFiles = new ArrayList<File>();
		ArrayList<File> subtitleFiles = new ArrayList<File>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().contains(".srt")) {
					subtitleFiles.add(listOfFiles[i]);
				} else {
					videoFiles.add(listOfFiles[i]);
				}
			} else if (listOfFiles[i].isDirectory()) {
				//Call recursively
				renameSubtitleFilesInFolder(listOfFiles[i].getAbsolutePath());
			}
		}
		for(File f : videoFiles){
			String episodeNo = f.getName().substring(1,3);
			String[] fileName = f.getName().split(".avi");
			String newSubtitleName = fileName[0] + ".srt";
			for(File s : subtitleFiles){
				if(s.getName().contains(episodeNo)){
					System.out.println("Rename "+s.getName()+" to "+newSubtitleName);
					//Uncomment this for real renaming
					//File newfile = new File(s.getParent()+"/"+newSubtitleName);
					//s.renameTo(newfile);
				}
			}
		}
	}

}

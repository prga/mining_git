import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;



public class Directory {
	
	private String name;
	
	private List<String> filesNames;
	
	private List<Directory> subDirectories;
	
	public Directory(String name){
		
		this.name = name;
		
		this.filesNames = new ArrayList<String>();
		
		this.subDirectories = new ArrayList<Directory>();
		
		
		
	}
	
	//this method gets all files names inside a directory and its sub directories
	public void listFilesAndFilesSubDirectories(String directoryName){
		
		File directory = new File (directoryName);
		
		File[] fList = directory.listFiles();
		
		for(File file : fList){
			
			if(file.isFile()){
				
				this.filesNames.add(file.getName());
				
			} else if (file.isDirectory()){
				
				String name = file.getName();
				Directory sub = new Directory(name);
				sub.listFilesAndFilesSubDirectories(name);
				this.subDirectories.add(sub);

			}
			
		}
		
		
	}

	public ChangeSet compareDirectories(Directory baseDir){
		ChangeSet result = null;
	
		
		return result;
	}
	
	
}
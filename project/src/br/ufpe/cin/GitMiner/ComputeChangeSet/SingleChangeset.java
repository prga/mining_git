package br.ufpe.cin.GitMiner.ComputeChangeSet;



public class SingleChangeset {
	
	private ChangeSet singleChangeSet;
	
	public SingleChangeset(){
		
		this.singleChangeSet = new ChangeSet();
	}

	public ChangeSet getSingleChangeSet() {
		
		return singleChangeSet;
	}

	public void setSingleChangeSet(ChangeSet singleChangeSet) {
		this.singleChangeSet = singleChangeSet;
	}
	
	public void loadSingleChangeSet(ChangeSet cs){
		
		this.auxiliarReturnSingleChangeset(this.singleChangeSet, cs);
		
		for(ChangeSet subChangeSet : cs.getSubChangeSets()){
			
			
			this.loadSingleChangeSet(subChangeSet);
		}
		
	}
	
	private void auxiliarReturnSingleChangeset(ChangeSet a, ChangeSet b){
		
		a.getModifiedFiles().addAll(b.getModifiedFiles());
		a.getAddedFiles().addAll(b.getAddedFiles());
		a.removedFiles.putAll(b.removedFiles);
		a.getAddedDirectories().addAll(b.getAddedDirectories());
		a.getRemovedDirectories().addAll(b.getRemovedDirectories());
		
		
	}
	

}

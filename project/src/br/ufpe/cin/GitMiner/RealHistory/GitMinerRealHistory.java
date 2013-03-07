package br.ufpe.cin.GitMiner.RealHistory;


import java.io.File;

import br.ufpe.cin.GitMiner.ComputeChangeSet.ChangeSet;
import br.ufpe.cin.GitMiner.io.FileHandler;


public class GitMinerRealHistory {
	
	private ChangeSet changeSet;
	
	private FileHandler fileHandler;

	public GitMinerRealHistory(){
		this.fileHandler = new FileHandler();
	}
	public void readInputXML(String filePath){
		
		
		this.changeSet = this.fileHandler.readXmlFile(filePath);
		System.out.println("done reading input file");
	}
	
	public void loadCommits(){
		
		this.downloadCommits();
		this.unzipCommits();
	
		
	}
	
	private void downloadCommits(){
		this.fileHandler.auxiliarDownload(this.changeSet.getBaseCommit().getUrlZip(), 
										this.changeSet.getBaseCommit().getName());
		
		System.out.println("Done downloading first commit");
		
		this.fileHandler.auxiliarDownload(this.changeSet.getTaskCommit().getUrlZip(), 
										this.changeSet.getTaskCommit().getName());
		
		System.out.println("Done downloading second commit");
	}
	
	private void unzipCommits(){
		
		String directoryName = this.changeSet.getBaseCommit().getName();

		this.fileHandler.auxiliarUnzip((directoryName + ".zip"), directoryName);
		
		System.out.println("Done unzipping first commit");
		
		String directoryTaskName = this.changeSet.getTaskCommit().getName();
	
		this.fileHandler.auxiliarUnzip((directoryTaskName + ".zip") , directoryTaskName);
		
		System.out.println("Done unzipping second commit");
		
		this.renameDirectory();
	
	}
	
	//renames the root directory from pattern repoName-shakey to sourcecode
	private void renameDirectory(){
		
		//first commit
		File oldBaseName = new File (this.changeSet.getBaseCommit().getName() + File.separator + "rgms-" + 
									this.changeSet.getBaseCommit().getSHAKey() );
		
		File newBaseName = new File (this.changeSet.getBaseCommit().getName() + File.separator +"sourcecode");
		
		oldBaseName.renameTo(newBaseName);
		
		//second commit
		
		File oldTaskName = new File (this.changeSet.getTaskCommit().getName() + File.separator + "rgms-" + 
				this.changeSet.getTaskCommit().getSHAKey() );

		File newTaskName = new File (this.changeSet.getTaskCommit().getName() + File.separator +"sourcecode");
		oldBaseName.renameTo(newBaseName);
		
		oldTaskName.renameTo(newTaskName);
	}
	
	private void loadCommitFiles(){
		this.changeSet.getBaseCommit().loadDirectory();
		this.changeSet.getTaskCommit().loadDirectory();
	}
	
	public void computeChangeSet(){
		
		this.loadCommitFiles();
		
		this.changeSet.loadChangeSet(this.changeSet.getTaskCommit().getDirectory(),
				this.changeSet.getBaseCommit().getDirectory());
		
		System.out.println("done computing changeset");
		
		this.reportChangeSet();
	}
	
	private void reportChangeSet(){
		
		this.fileHandler.writer(this.changeSet.getBaseCommit().getName() + "_And_" + 
						this.changeSet.getTaskCommit().getName(), this.changeSet.report());
		
		System.out.println("done reporting commits changeset");
	}
	
	public static void main(String[] args) {
		GitMinerRealHistory repo = new GitMinerRealHistory();
		repo.readInputXML("input.xml");
		//repo.loadCommits();
		repo.computeChangeSet();
		
	}
	
}

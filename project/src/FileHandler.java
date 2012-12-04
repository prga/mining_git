import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/*the input must contain the following informations:
 * the repository user's id
 * the repository name
 * baseCommit, key
 * baseName, key
 * an so on
 * 
 * */

public class FileHandler {

	
	
	 GitMiner reader(String fileName)throws IOException{
		
		GitMiner result = new GitMiner(fileName);
		CSVReader reader = new CSVReader(new FileReader(fileName));
		List<String[]> entries = reader.readAll();
		
		//set GitMiner userId attribute
		String[] userLine = entries.get(0);
		String userId  = userLine[0];
		result.setUserId(userId);
		
		//set GitMiner repo attribute
		String[] repoLine = entries.get(1);
		String repo  = repoLine[0];
		result.setRepoName(repo);
		
		//set GitMiner base commit
		String[] baseLine = entries.get(2);
		String name = baseLine[0];
		String sha = baseLine[1];
		result.setBase(new BaseCommit(name, sha));
		
		//set GitMiner features commits
		List<FeatureCommit> features = new ArrayList<FeatureCommit>();
		int featuresIndex = 3;
		while(featuresIndex < entries.size()){
			
			String featureLine[] = entries.get(featuresIndex);
			name = featureLine[0];
			sha = featureLine[1];
			features.add(new FeatureCommit(name, sha));
			
			featuresIndex++;
			
		}
		result.setFeatures(features);
		
		return result;
		
	}
	
	public void downloader(GitMiner repo) throws MalformedURLException, IOException{
		
		String gitUrl = "https://github.com/" + repo.getUserId() + "/" + 
		repo.getRepoName() + "/archive/";
		
		//download base commit
		String url = gitUrl  + repo.getBase().getSHAKey() + ".zip";
		
		this.auxiliarDownload(url, repo.getBase().getName());
		System.out.println("end of " + repo.getBase().getName() + " download.");
		//download feature commits
		
		for(FeatureCommit f : repo.getFeatures()){
			String fUrl = gitUrl + f.getSHAKey() + ".zip";
			this.auxiliarDownload(fUrl, f.getName());
			System.out.println("end of " + f.getName() + " download.");
		}

		
		
	}
	
	private void auxiliarDownload(String url, String name) throws MalformedURLException, IOException{
		
		BufferedInputStream in = new java.io.BufferedInputStream(new java.net.URL(url).openStream());
		FileOutputStream fos = new java.io.FileOutputStream(name + ".zip");
		BufferedOutputStream bout = new BufferedOutputStream(fos,8192);
		byte[] data = new byte[8192];
		int x=0;
		while((x=in.read(data,0,8192))>=0)
		{
		bout.write(data,0,x);
		}
		bout.close();
		in.close();
		}
	
	
	public void writer(){
		
	}

}

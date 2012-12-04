
import java.io.FileReader;
import java.io.IOException;
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

	
	
	static GitMiner reader(String fileName)throws IOException{
		
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
	
	public void writer(){
		
	}

}

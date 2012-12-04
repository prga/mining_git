
import java.io.IOException;
import java.util.List;




/*everything related to git repo 
 * step 1: read input file --ok
 * step 2: download zipped commits from github
 * step 3: unzip commits
 * step 4: load commits directories
 * step 5: compute features changeset
 * step 6: compute features intersection
 * step 7: write features report
 * */


public class GitMiner {
	
	private String inputFile;
	
	private String outputFile;
	
	private String userId;
	
	private String repoName;
	
	private BaseCommit base;
	
	private List<FeatureCommit> features;
	
	public GitMiner(String inputFile){
		
		this.inputFile = inputFile;
		
		
	}
	
	public void readInput() throws IOException  {
		
		GitMiner temp = FileHandler.reader(this.inputFile);
		
		this.userId = temp.getUserId();
		this.repoName = temp.getRepoName();
		this.base = temp.getBase();
		this.features = temp.getFeatures();
		
	}
	
		
		

	
	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public BaseCommit getBase() {
		return base;
	}

	public void setBase(BaseCommit base) {
		this.base = base;
	}

	public List<FeatureCommit> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureCommit> features) {
		this.features = features;
	}

	public static void main(String[] args) {
		
		GitMiner repo = new GitMiner("input.csv");
		try {
			repo.readInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
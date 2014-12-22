package tryagain.objects;

public enum TryAgainParams {

	RETRY_COUNT("retrycount", "RETRY_COUNT");

	public String getTagName() {
		return this.tagName;
	}

	public String getVMArg() {
		return this.vmArgName;
	}

	private String tagName;
	private String vmArgName;

	private TryAgainParams(String tagName, String vmArgName) {
		this.tagName = tagName;
		this.vmArgName = vmArgName;
	}

}

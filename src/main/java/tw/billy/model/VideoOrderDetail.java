package tw.billy.model;

public class VideoOrderDetail {

	private int vOrderDetailID;
	private int vOrderID;
	private int videoID;

	public int getvOrderDetailID() {
		return vOrderDetailID;
	}

	public void setvOrderDetailID(int vOrderDetailID) {
		this.vOrderDetailID = vOrderDetailID;
	}

	public int getvOrderID() {
		return vOrderID;
	}

	public void setvOrderID(int vOrderID) {
		this.vOrderID = vOrderID;
	}

	public int getVideoID() {
		return videoID;
	}

	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}

}

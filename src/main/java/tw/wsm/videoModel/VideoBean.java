package tw.wsm.videoModel;

import tw.billy.model.VideoOrder;

public class VideoBean extends VideoOrder{
	private int id;
	private String videoName;
	private String videoClass;
	private String videoFileName;
	private String imgFileName;
	private int videoHit;
	private int videoPrice;

	public VideoBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public int getVideoPrice() {
		return videoPrice;
	}

	public void setVideoPrice(int videoPrice) {
		this.videoPrice = videoPrice;
	}

	public int getVideoHit() {
		return videoHit;
	}

	public void setVideoHit(int videoHit) {
		this.videoHit = videoHit;
	}

	public String getVideoClass() {
		return videoClass;
	}

	public void setVideoClass(String videoClass) {
		this.videoClass = videoClass;
	}

}

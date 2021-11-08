package tw.billy.model;

import java.util.Date;

public class VideoOrder {

	private int videoOrderID;
	private int uID;
	private Date orderDate;

	public int getVideoOrderID() {
		return videoOrderID;
	}

	public void setVideoOrderID(int videoOrderID) {
		this.videoOrderID = videoOrderID;
	}

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}

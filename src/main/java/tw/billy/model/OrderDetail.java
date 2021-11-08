package tw.billy.model;

public class OrderDetail extends Product{

	private int orderdetailid;
	private String orderNo;
	private int pid;
	//private int uid;
	private int orderquentity;
	private String date;

	public OrderDetail() {

	}

	public int getOrderdetailid() {
		return orderdetailid;
	}

	public void setOrderdetailid(int orderdetailid) {
		this.orderdetailid = orderdetailid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

//	public int getUid() {
//		return uid;
//	}
//
//	public void setUid(int uid) {
//		this.uid = uid;
//	}

	public int getOrderquentity() {
		return orderquentity;
	}

	public void setOrderquentity(int orderquentity) {
		this.orderquentity = orderquentity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	



}

package tw.jacky.model;

public class Aesdata {
	private String TempLogisticsID ;
	private Integer GoodsAmount;
	private String GoodsName ;
	private String SenderName ;
	private String SenderZipCode ;
	private String SenderAddress ;
	private String ServerReplyURL ;
	private String ClientReplyURL ;
	
	public String getTempLogisticsID() {
		return TempLogisticsID;
	}
	public void setTempLogisticsID(String tempLogisticsID) {
		TempLogisticsID = tempLogisticsID;
	}
	public Integer getGoodsAmount() {
		return GoodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		GoodsAmount = goodsAmount;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	public String getSenderName() {
		return SenderName;
	}
	public void setSenderName(String senderName) {
		SenderName = senderName;
	}
	public String getSenderZipCode() {
		return SenderZipCode;
	}
	public void setSenderZipCode(String senderZipCode) {
		SenderZipCode = senderZipCode;
	}
	public String getSenderAddress() {
		return SenderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		SenderAddress = senderAddress;
	}
	public String getServerReplyURL() {
		return ServerReplyURL;
	}
	public void setServerReplyURL(String serverReplyURL) {
		ServerReplyURL = serverReplyURL;
	}
	public String getClientReplyURL() {
		return ClientReplyURL;
	}
	public void setClientReplyURL(String clientReplyURL) {
		ClientReplyURL = clientReplyURL;
	}
	
	

}

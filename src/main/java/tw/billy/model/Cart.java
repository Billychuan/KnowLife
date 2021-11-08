package tw.billy.model;

public class Cart extends Product {
private int quantity;//加入購物車的數量
	
	public Cart()	{
		
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	

}

package tw.billy.model;

public class Product {
	
	private int pid;	
	private String name;
	private String category;	
	private int price;	
	private int quantity;	
	private String filename;

	
	public Product() {

	}

	public Product(String name, String category, Integer price, Integer quantity, String filename) {

		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.filename = filename;

	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	

}

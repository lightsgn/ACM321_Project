package structures;

public class Supplier {
	public int id;
	public String name;
	public String contactNumber;
	public String address;
	public boolean isActive;
	
	public Supplier(int id, String name, String contactNumber, String address, boolean isActive) {
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.address = address;
		this.isActive = isActive;
	}
}

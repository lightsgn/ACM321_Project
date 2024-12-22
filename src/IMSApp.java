import gui.IMSGui;
import repo.SupplierRepo;

public class IMSApp {
	public static void main(String[] args) throws Exception {
		SupplierRepo repo = new SupplierRepo();
		new IMSGui(repo);
	}
}

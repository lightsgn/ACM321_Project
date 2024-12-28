import gui.IMSGui;
import repo.SupplierRepo;
import repo.BoardGameRepo;

public class IMSApp {
	public static void main(String[] args) throws Exception {
		SupplierRepo repo = new SupplierRepo();
		BoardGameRepo boardGameRepo = new BoardGameRepo();
		new IMSGui(repo, boardGameRepo);
		
	}
}

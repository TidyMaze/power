import view.WindowView;
import controller.EnergyController;

public class Application {
	public static void main(String[] args) {
		WindowView wv = new WindowView();
		EnergyController ec = new EnergyController(wv);

		wv.setEnergyController(ec);
		ec.lancer();
		wv.pack();
		wv.setVisible(true);
	}

}
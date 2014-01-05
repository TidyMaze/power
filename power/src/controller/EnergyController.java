package controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.TreeSet;

import model.EnergyOption;
import view.WindowView;

public class EnergyController {
	private WindowView vue;
	private TreeSet<EnergyOption> liste;

	public EnergyController(WindowView wv) {
		this.vue = wv;
	}

	public TreeSet<EnergyOption> obtenirListePlans() {
		String[] commandList = { "powercfg", "-L" };
		ProcessBuilder pb = new ProcessBuilder(commandList);
		pb.redirectErrorStream(true);
		Process process;

		TreeSet<EnergyOption> liste = new TreeSet<EnergyOption>();

		try {
			process = pb.start();
			BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "cp850"));

			String s;
			while ((s = inStreamReader.readLine()) != null) {
				String guid = "";
				String desc = "";
				boolean selected = false;

				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == ':') {
						i += 2;
						for (int j = 0; j < 36; j++, i++) {
							guid += s.charAt(i);
						}

						while (i < s.length() && s.charAt(i) != '(') {
							i++;
						}

						i++;

						while (i < s.length() && s.charAt(i) != ')') {
							desc += s.charAt(i);
							i++;
						}

						i++;

						while (i < s.length()) {
							if (s.charAt(i) == '*') {
								selected = true;
								break;
							} else {
								i++;
							}
						}

						liste.add(new EnergyOption(guid, desc, selected));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public void activerPlan(String guid) {
		String[] commandChange = { "powercfg", "-S", guid };
		ProcessBuilder pb = new ProcessBuilder(commandChange);
		pb.redirectErrorStream(true);
		Process process;

		BufferedReader inStreamReader;
		try {
			process = pb.start();

			inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			String s;
			while ((s = inStreamReader.readLine()) != null) {
				System.out.println(s);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void lancer() {
		liste = this.obtenirListePlans();
		vue.genererBoutons(liste);
	}

	public void radioChoosed(ActionEvent e) {
		this.activerPlan(e.getActionCommand());
	}
}

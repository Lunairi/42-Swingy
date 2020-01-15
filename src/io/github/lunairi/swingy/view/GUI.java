package io.github.lunairi.swingy.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	public enum Panels {
		StartMenu, NewGame, LoadGame;
		
		public static final int size = 3;
	};
	
	private JPanel[] panels = new JPanel[Panels.size];

	public GUI(String name, int width, int length) {
		super(name);
		this.setSize(width,length);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initPanels() {
		this.panels[Panels.StartMenu.ordinal()] = new StartMenu();
		this.panels[Panels.NewGame.ordinal()] = new NewGameMenu();
		this.panels[Panels.LoadGame.ordinal()] = new LoadGameMenu();
		for (int i = 0; i < Panels.size; i++) {
			this.add(panels[i]);
		}
		this.setVisible(true);
	}
	
	public void renderPanel(int index) {
		for (int i = 0; i < Panels.size; i++) {
			if (index == i) {
				this.panels[i].setVisible(true);
			}
			else {
				this.panels[i].setVisible(false);
			}
		}
	}
	
//	public void addNewButton(String name, int x0, int y0, int x1, int y1) {
//		this.bManager.createButton(name, x0, y0, x1, y1);
//	}

}

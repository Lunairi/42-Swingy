package io.github.lunairi.swingy.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.lunairi.swingy.controller.GameEngine;
import io.github.lunairi.swingy.controller.Player;
import io.github.lunairi.swingy.view.GUI.Panels;
import io.github.lunairi.swingy.model.Class;
import io.github.lunairi.swingy.model.Class.Stats;

@SuppressWarnings("serial")
public class NewGameMenu extends JPanel {
	
	private String classSelected = "Knight";
	private JLabel statPreview;
	private JTextField nameBox;
	
	public NewGameMenu() {
		this.setSize(800, 600);
		this.setLayout(null);
		this.setVisible(false);
		this.initHeader();
		this.heroNameBox();
		this.classSelection();
		this.previewStats();
		this.confirmCharacterButton();
		this.backButton();
	}
	
	private void initHeader() {
		JLabel header = new JLabel();
		header.setText("Create New Character");
		header.setBounds(320, 20, 200, 40);
		this.add(header);
	}
	
	private void heroNameBox() {
		this.nameBox = new JTextField();
		this.nameBox.setText("   Input hero name here...");
		this.nameBox.setBounds(300, 80, 200, 40);
		this.add(this.nameBox);
	}
	
	private void classSelection() {
		String[] classes = {"Knight", "Fighter", "Trickster", "Chanter", "Marksman"};
		JComboBox<String> classList = new JComboBox<String>(classes);
		classList.setSelectedIndex(0);
		classList.setBounds(300, 140, 200, 40);
		classList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> selection = (JComboBox<String>)e.getSource();
				classSelected = classes[selection.getSelectedIndex()];
				setStatPreview();
			}
		});
		this.add(classList);
	}
	
	private void setStatPreview() {
		int[] stats = Class.classes.get(classSelected);
		this.statPreview.setText(
				"Health: [" + stats[Stats.Health.ordinal()] + 
				"] - Attack: [" + stats[Stats.Attack.ordinal()] +
				"] - Defense: [" + stats[Stats.Defense.ordinal()] +
				"] - Speed [" + stats[Stats.Speed.ordinal()] + "]"
		);
	}
	
	private void previewStats() {
		this.statPreview = new JLabel();
		this.setStatPreview();
		this.statPreview.setBounds(240, 200, 400, 40);
		this.add(statPreview);
	}
	
	private void confirmCharacterButton() {
		JButton createButton = new JButton("Confirm Character Creation");
		createButton.setBounds(240, 260, 300, 40);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.createCharacter(nameBox.getText(), classSelected, Class.classes.get(classSelected));
				GameEngine.progressGame(Panels.StartMenu.ordinal()); // will have to change to game panel
			}
		});
		this.add(createButton);
	}
	
	private void backButton() {
		JButton backButton = new JButton("Back");
		backButton.setBounds(280, 310, 200, 40);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEngine.progressGame(Panels.StartMenu.ordinal());
			}
		});
		this.add(backButton);
	}
}
















package com.azovstal.core.button;

import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;

public class AddButton implements Serializable {

	public AddButton() {
	}

	/**
	 * 
	 * @param textBtn	-	наименование
	 * @param panelBtn	-	панель, куда ляжет кнопка
	 * @param listener	-	событие
	 */
	public AddButton(String textBtn, JPanel panelBtn, ActionListener listener) {
		if (textBtn != "" || textBtn == null)
			label = textBtn;
		else
			label = "Submit";
		JButton button = new JButton(label);
		if (panelBtn != null)
			panelBtn.add(button);
		if (listener != null)
			button.addActionListener(listener);
	}

	/**
	 * 
	 * @param textBtn	-	наименование
	 * @param icon		-	иконка
	 * @param panelBtn	-	панель, куда ляжет кнопка
	 * @param listener	-	событие
	 */
	public AddButton(String textBtn, Icon icon, JPanel panelBtn,
			ActionListener listener) {
		if (textBtn != "" || textBtn == null)
			label = textBtn;
		else
			label = "Submit";
		JButton button = new JButton(label);
		if (icon != null)
			button.setIcon(icon);
		if (panelBtn != null)
			panelBtn.add(button);
		if (listener != null)
			button.addActionListener(listener);
	}

	/**
	 * 
	 * @param textBtn	-	наименование
	 * @param panelBtn	-	панель, куда ляжет кнопка
	 */
	public AddButton(String textBtn, JPanel panelBtn) {
		if (textBtn != "" || textBtn == null)
			label = textBtn;
		else
			label = "Submit";
		JButton button = new JButton(label);
		if (panelBtn != null)
			panelBtn.add(button);
	}

	/**
	 * 
	 * @param textBtn	-	наименование
	 * @param icon		-	иконка
	 * @param panelBtn	-	панель, куда ляжет кнопка
	 */
	public AddButton(String textBtn, Icon icon, JPanel panelBtn) {
		if (textBtn != "" || textBtn == null)
			label = textBtn;
		else
			label = "Submit";
		JButton button = new JButton(label);
		if (icon != null)
			button.setIcon(icon);
		if (panelBtn != null)
			panelBtn.add(button);
	}

	/**
	 * 
	 * @param textBtn	-	наименование
	 */
	public AddButton(String textBtn) {
		if (textBtn != "" || textBtn == null)
			label = textBtn;
		else
			label = "Submit";
		new JButton(label);
	}

	private static final long serialVersionUID = 0x76a1e039b639f920L;
	String label;
}
package com.azovstal.test.animcursor;

import javax.swing.ImageIcon;


public class FactoryImage {

	private FactoryImage() {
	}

	public static FactoryImage getInstance() {
		return factory;
	}

	private final static FactoryImage factory = new FactoryImage();

	private static final String RESOURCE_PICTURES_PATH = "/com/azovstal/test/animcursor/pic/";
	
	public final ImageIcon ICON_LOADER010 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader010.png"));
	public final ImageIcon ICON_LOADER009 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader009.png"));
	public final ImageIcon ICON_LOADER008 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader008.png"));
	public final ImageIcon ICON_LOADER007 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader007.png"));
	public final ImageIcon ICON_LOADER006 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader006.png"));
	public final ImageIcon ICON_LOADER005 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader005.png"));
	public final ImageIcon ICON_LOADER004 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader004.png"));
	public final ImageIcon ICON_LOADER003 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader003.png"));
	public final ImageIcon ICON_LOADER002 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader002.png"));
	public final ImageIcon ICON_LOADER001 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "loader001.png"));

}

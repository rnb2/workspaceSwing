package com.rnb2.swApp.resource;

import javax.swing.ImageIcon;

/**
 * 
 * @author budukh-rn
 *
 */
public class FactoryImage {
	/**
	 * com/rnb2/swApp/resource/pic/
	 */
	public static final String RESOURCE_PICTURES_PATH = "/com/rnb2/swApp/resource/pic/";

	private FactoryImage() {

	}

	public static FactoryImage getInstance() {
		return factory;
	}

	private final static FactoryImage factory = new FactoryImage(

	);

	public final ImageIcon ICON_GOTOOBJ_16 = new ImageIcon(getClass()
			.getResource(RESOURCE_PICTURES_PATH + "gotoobj_16.gif"));
	public final ImageIcon ICON_PASTE_16 = new ImageIcon(getClass()
			.getResource(RESOURCE_PICTURES_PATH + "paste_16.gif"));
	public final ImageIcon ICON_COPY_16 = new ImageIcon(getClass().getResource(
			RESOURCE_PICTURES_PATH + "copy_16.gif"));
	public final ImageIcon ICON_COPY_R_CO_16 = new ImageIcon(getClass()
			.getResource(RESOURCE_PICTURES_PATH + "copy_r_co_16.gif"));
	public final ImageIcon ICON_CLEAR_16 = new ImageIcon(getClass()
			.getResource(RESOURCE_PICTURES_PATH + "clear_16.gif"));

}

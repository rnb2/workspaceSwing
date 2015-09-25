/**
 * 
 */
package com.rnb2.swApp;

/**
 * @author budukh-rn
 * 
 */
public enum Bitrate {
	VBR("VBR"), _128("128"), _192("192"), _256("256"), _320("320");

	private Bitrate(String text) {
		this.text = text;
	}

	private String text;

	@Override
	public String toString() {
		return "Mp3 /" + text + " kbps / 44.1KHz";
	}

}

package nl.hsleiden.ipsen2.inf2b1.g2.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Creates the imagedisplay unit for showing the vehicle images.
 * 
 * @author Deam
 * 
 */
public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public void setImage(BufferedImage img) {
		this.image = img;
		repaint();
	}

	/**
	 * Paint the image to the panel in the imageslider
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, (getPreferredSize().width),
				(getPreferredSize().height), null);
		repaint();
	}

	/**
	 * Set the size of the panel image relative to the screen resolution
	 */
	@Override
	public Dimension getPreferredSize() {
		GraphicsDevice gDevice = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return new Dimension((gDevice.getDisplayMode().getWidth() * 3) / 6,
				(gDevice.getDisplayMode().getHeight() * 3) / 6);
	}
}

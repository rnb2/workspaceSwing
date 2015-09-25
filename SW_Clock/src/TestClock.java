

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestClock extends JFrame {

	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();
	private int srcWidth = dimension.width - 500;
	private int srcHeight = dimension.height - 500;
	private JPanel panel = new JPanel();
	private JLabel jLabel = new JLabel();
	private JButton button = new JButton("Обновить");
	private DateFormat dateFormat = new SimpleDateFormat();
	private JLabel jLabelTz = new JLabel();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestClock frame;
		try {
			frame = new TestClock();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TestClock() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("Clock");
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		setSize(srcWidth, srcHeight);
		setLocationRelativeTo(null);
		
		Date data = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		
		jLabel.setText(data.toString());
		jLabelTz.setText("  Time zone: " + calendar.getTimeZone().getDisplayName());
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date data = new Date();
				jLabel.setText(data.toString());
				
			}
		});
		
		panel.add(jLabel);
		panel.add(button);
		panel.add(jLabelTz);
		getContentPane().add(panel);
	}

}

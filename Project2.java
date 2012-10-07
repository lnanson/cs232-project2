import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;

public class Project2 extends JFrame implements ChangeListener{
	private AsciiViewer myAsciiViewer;
	private ImageViewer myImageViewer;
	//private ButtonPanel myButtonPanel;

	public Project2(Image image) {
		int zoom = 1;
		myAsciiViewer = new AsciiViewer(new AsciiImage(image));
		myImageViewer = new ImageViewer(new Image(image));
		JSlider zoomControl = new JSlider(JSlider.VERTICAL, 1, 10, 1);
		zoomControl.addChangeListener(this);
		zoomControl.setMajorTickSpacing(1);
		zoomControl.setPaintTicks(true);
		zoomControl.setPaintLabels(true);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(myImageViewer);
		panel.add(myAsciiViewer);
		setLayout(new BorderLayout());
		add(panel, "Center");
		add(zoomControl, "East");
		//add(myButtonPanel, "South");
		
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int x = (int)source.getValue();
			myImageViewer.setZoomLevel(x);
			myAsciiViewer.setZoomLevel(x);
		}
	}
	
	
	public static void main(String[] args) {
		String file = args[0];
		try {
			Image image = ImageUtilities.loadJPEG(file);
			Project2 window = new Project2(image);
			window.setDefaultCloseOperation(3);
			window.pack();
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		}
		catch (IOException exception) {
			System.out.println("You must pass a JPEG filename as a command-line parameter.");
			System.out.println("USAGE: java Project2 <filename>");
		}
	}
}

		//JScrollPane scrollImage = new JScrollPane(ImageViewer); //needs to be in Class
		//JScrollPane scrollAscii = new JScrollPane(AsciiViewer); // needs to be in Class
		//add(scrollImage);
		//add(scrollAscii);
		//frame.add(ZoomControl);
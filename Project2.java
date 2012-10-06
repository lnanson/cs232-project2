public class Project2 {



	public static void main(String[] args) {
		JFrame frame = new JFrame("Create Ascii Image");
		frame.add(ImageViewer);		
		frame.add(AsciiViewer);
		JScrollPane scrollImage = new JScrollPane(ImageViewer);
		JScrollPane scrollAscii = new JScrollPane(AsciiViewer);
		add(scrollImage);
		add(scrollAscii);
		frame.add(ZoomControl);


	}

}
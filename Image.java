/*Image
This class represents a grayscale image. It is just a rectangular array of integers representing the pixel values. Three image manipulation methods should be provided—shrink, invert, and mirror.
Public Attributes
*/
import java.io.*;
import java.util.*;

public class Image {
	public enum Axis {HORIZONTAL, VERTICAL};
	// an enumeration representing the axis by which the image should be mirrored
	// Private Attributes
	private int[][] pixel;
	private int[][] copy;
	// a two-dimensional array of integers representing the grayscale pixel values (0 – 255)
	private int width;
	private int height;
	// the width (number of columns) and height (number of rows) of the image
	// Public Methods
	public Image(int width, int height) { // constructor that creates a fully black (all 0s) image of the specified width and height
		pixel = new int[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				pixel[i][j] = 0;
			}
		}
	}
	
	public Image(Image image) { // constructor that creates a deep copy of the image passed to it
		
		copy = new int[image.width][image.height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				copy[i][j] = image.getPixel(i,j);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPixel(int row, int col) {
		return pixel[row][col];
	}
	public void setPixel(int row, int col, int value) { 	// gets and sets the pixel at the specified row and column
		//this[row][col] = value;	
	}

	public void shrink() { //needs better special cases for odd number of rows/columns/ out of bounds
		int x;
		int[][] smallImage = new int[width/2][height/2];
		for (int i=0; i<width; i=i+2) {
			for (int j=0; j<height; j=j+2) {
				//try {
					x = (pixel[i][j]+pixel[i+1][j+1]+pixel[i][j+1]+pixel[i+1][j])%4;
				//}
				//catch (IndexOutOfBoundsException exception) {
				//	x = pixel[i*2][j*2];
				//}
				smallImage[i][j]= x;
			}
			
		}
	}
	// halves the size of the image in both width and height by taking the 
	// average of the current pixel and its immediate east, southeast, and 
	// south neighbors and using that value as the pixel value for the smaller image
	public void invert() {
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				pixel[i][j]=255-pixel[i][j];
			}
		}
	
	}
	// creates the ?negative? of the image by replacing each pixel value with its
	// inverted value. For example a pixel value of 0 would become 255, 
	// 100 would become 155, and 200 would become 55.
	public void mirror(Axis axis) {}
	// ?flips? the image about the specified axis. For instance, if the axis were vertical,
	 // then the first column and last column would be swapped, the second and next-to-last 
	 // columns would be swapped, etc.
	 
	public static void main(String[] args) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("strawberry.jpg"));
		} 
		catch (IOException e) {
		}
		
		
		
		
		Image myImage;
		//Image myImage = new Image(255, 198);
		try {
			myImage = ImageUtilities.loadJPEG("test.jpg");
			System.out.println(myImage.getPixel(142,124));
			}
		catch (java.io.IOException e){
			System.out.println("file not found.");
		}
		//myImage3 = Image(myImage2);
		

		//System.out.println(myImage2.getPixel(20,20));
		// myImage.shrink();
		// System.out.println(myImage.getPixel(1,2));

		
	}
}

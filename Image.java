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
	//private int[][] copy;
	// a two-dimensional array of integers representing the grayscale pixel values (0 – 255)
	private int width;
	private int height;
	// the width (number of columns) and height (number of rows) of the image
	// Public Methods
	public Image(int width, int height) { // constructor that creates a fully black (all 0s) image of the specified width and height
		this.width = width;
		this.height = height;
		pixel = new int[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				pixel[i][j] = i;
			}
		}
	}
	
	public Image(Image image) { // constructor that creates a deep copy of the image passed to it
        width = image.getWidth();
        height = image.getHeight();
		pixel = new int[width][height];

		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				pixel[i][j] = image.getPixel(i,j);
			}
		}
		System.out.println(image.getPixel(142, 124));
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPixel(int row, int col) {
		try {
			return pixel[row][col];
		}
		catch (IndexOutOfBoundsException exception) {
			return 0;
		}
	}
	public void setPixel(int row, int col, int value) { 
		try {
			pixel[row][col] = value;
		}
		catch (IndexOutOfBoundsException exception) {
		}
	}

	public void shrink() { //needs better special cases for odd number of rows/columns/ out of bounds
		int x=0;
		int[][] smallImage = new int[width/2][height/2];
		for (int i=0; i<width; i=i+2) {
			for (int j=0; j<height; j=j+2) {
				try {
					x = (pixel[i][j]+pixel[i+1][j+1]+pixel[i][j+1]+pixel[i+1][j])%4;
				}
				catch (IndexOutOfBoundsException exception) {
				//	x = pixel[i*2][j*2];
				}
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
				try {
					this.setPixel(i,j,255-this.getPixel(i,j));
				}
				catch (IndexOutOfBoundsException exception){
					this.setPixel(i,j,0);
				}
			}
		}
	}
	// creates the ?negative? of the image by replacing each pixel value with its
	// inverted value. For example a pixel value of 0 would become 255, 
	// 100 would become 155, and 200 would become 55.
	public void mirror(Axis axis) {
		int y=this.getHeight();
		int x=this.getWidth();
		Image temp = new Image(y,x);
		if (axis == Axis.HORIZONTAL) {
			for (int i=0; i<x; i++) {
				for (int j=0; j<y; j++) {
					temp.setPixel((y-i),j,this.getPixel(i,j));
				}
			}
		}
		if (axis == Axis.VERTICAL) {
			for (int i=0; i<x; i++) {
				for (int j=0; j<y; j++) {
					temp.setPixel(i,(x-j),this.getPixel(i,j));
				}
			}
		}
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				this.setPixel(i,j,temp.getPixel(i,j));
			}
		}
	}
	// ?flips? the image about the specified axis. For instance, if the axis were vertical,
	 // then the first column and last column would be swapped, the second and next-to-last 
	 // columns would be swapped, etc.
	 
	public static void main(String[] args) {
		
		
		Image myImageB = new Image(100, 200);
		try {
			Image myImageA = new Image(ImageUtilities.loadJPEG("test.jpg"));
			System.out.println("original" + myImageA.getPixel(142,124));
			myImageA.invert();
			System.out.println("inverse" + myImageA.getPixel(142, 124));
			System.out.println("height " + myImageA.getHeight());
			System.out.println("before mirror "+ myImageB.getPixel(5,5));
			System.out.println("before mirror "+ myImageB.getPixel(95,195));
			myImageB.mirror(Axis.HORIZONTAL);
			//myImageB.mirror(Axis.VERTICAL);
			System.out.println("mirrored "+ myImageB.getPixel(5,5));
			System.out.println("mirrored "+ myImageB.getPixel(95,195));
			}
		catch (java.io.IOException e){
			System.out.println("file not found.");
		}

		
		
	}
}

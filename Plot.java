import list.*;

/**
 * Produces a 2-dimensional plot of a set of supplied points.
 * @author 	Kushal Ranjan
 * @version	042913
 */
public class Plot {

	/*
	 * Input should be in form:
	 * 		java Plot [x1,y1] [x2,y2] ...
	 */
	public static void main(String[] args) {
		LinkedList<Comparable> points = new LinkedList<Comparable>();
		for(String s: args) {
			points.add(readPoint(s));
		}
		int[] bounds = getLimits(points);
		int x = Math.max(1, bounds[0]) + 1;
		int y = Math.max(1, bounds[1]) + 1;
		System.out.println("Plotting " + points + ":");
		points = sortPoints(points);
		Iterator<Comparable> pointsIt = points.iterator();
		Point curr = (Point)pointsIt.next();
		if(curr == null) //No points given
			curr = new Point(x + 1, y + 1); //Will never be plotted
		for(int j = y; j >= -y; j--) {
			for(double i = -x; i <= x; i += 0.5) {
				if(i == curr.x && j == curr.y) { //Point
					System.out.print("*");
					if(pointsIt.hasNext()) curr = (Point)pointsIt.next();
				} else if(i == 0 && j == 0) { //Origin
					System.out.print("+");
				} else if(i == 0) { //Y-axis
					if(j == -y) {
						System.out.print("v");
					} else if(j == y) {
						System.out.print("^");
					} else {
						System.out.print("|");
					}
				} else if(j == 0) { //X-axis
					if(i == -x) {
						System.out.print("<");
					} else if(i == x) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Uses a String representation to produce a point.
	 * @param text	String representation of the form "[x,y]"
	 * @return		a Point with coordinates (x,y)
	 */
	private static Point readPoint(String text) {
		text = text.substring(1,text.length()-1);
		String[] numText = text.split(",");
		return new Point(Integer.parseInt(numText[0].trim()), Integer.parseInt(numText[1].trim()));
	}
	
	/**
	 * Sorts the points so that they can be printed using a quicksort.
	 * @param points	unsorted list of points
	 * @return			sorted version of points
	 */
	private static LinkedList<Comparable> sortPoints(LinkedList<Comparable> points) {
		return QuickSort.sort(points);
	}
	
	/**
	 * Finds the upper bounds on the absolute values of the coordinates for the points
	 * @param points	list of points
	 * @return			{maximum |x|, maximum |y|}
	 */
	private static int[] getLimits(LinkedList<Comparable> points) {
		int[] bounds = {0,0};
		Iterator<Comparable> it = points.iterator();
		while(it.hasNext()) {
			Point point = (Point)it.next();
			bounds[0] = Math.max(bounds[0], Math.abs(point.x));
			bounds[1] = Math.max(bounds[1], Math.abs(point.y));
		}
		return bounds;
	}
}

/**
 * Data holder class for point structures.
 * @author Kushal Ranjan
 */
class Point implements Comparable<Point> {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Any point with a higher y-coordinate than o is "smaller" than o.
	 * If the y-coordinates are equal, the point with the lower x-coordinate is "smaller".
	 */
	public int compareTo(Point o) {
		if(this.y != o.y) {
			return o.y - this.y;
		} else {
			return this.x - o.x;
		}
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

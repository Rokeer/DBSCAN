import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Util {
	public Util () {
		
	}
	
	public ArrayList<Point> readFile(String fileName) {
		ArrayList<Point> result = new ArrayList<Point>();
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int id = 0;
            while ((tempString = reader.readLine()) != null) {
            	String[] xy = tempString.split("\t");
                Point p = new Point(id, Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
            	result.add(p);
            	id++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return result;
	}
	
	public double calDis(Point a, Point b) {
		double result = 0;
		result = Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));
		return result;
	}
}


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double e = 3.0;
		int k = 5;
		String fileName = "3spiral.txt";
		
		DBSCAN d = new DBSCAN();
		Util u = new Util();
		d.run(e, k, u.readFile(fileName));
	}

}

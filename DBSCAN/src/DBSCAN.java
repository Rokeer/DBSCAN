import java.util.ArrayList;
import java.util.Hashtable;


public class DBSCAN {
	private Util u = new Util();
	public DBSCAN() {
		
	}
	
	public void run(double e, int k, ArrayList<Point> list) {
		Hashtable<String, Double> disTable = calculateDistance(list);
		Hashtable<String, ArrayList<Point>> markedPoint = markPoint(e, k, list, disTable);
		/**
		System.out.println("Core: ");
		for(int i = 0; i < markedPoint.get("core").size(); i++) {
			System.out.println("\tPoint: ID: "+markedPoint.get("core").get(i).getID()+", Coord: "+markedPoint.get("core").get(i).getX()+", "+markedPoint.get("core").get(i).getY());
		}
		System.out.println("Border: ");
		for(int i = 0; i < markedPoint.get("border").size(); i++) {
			System.out.println("\tPoint: ID: "+markedPoint.get("border").get(i).getID()+", Coord: "+markedPoint.get("border").get(i).getX()+", "+markedPoint.get("border").get(i).getY());
		}
		**/
		
		ArrayList<ArrayList<Point>> groups = group(e, k, markedPoint.get("core"), disTable);
		for(int i = 0; i < groups.size(); i++) {
			System.out.println("Group: "+(i+1));
			for(int j = 0; j < groups.get(i).size(); j++) {
				System.out.println("\tPoint: ID: "+groups.get(i).get(j).getID()+", Coord: "+groups.get(i).get(j).getX()+", "+groups.get(i).get(j).getY());
			}
		}
		
		groups = addBorder(e, k, groups, markedPoint.get("border"), disTable);
		
		/**
		for(int i = 0; i < groups.size(); i++) {
			System.out.println("Group: "+(i+1));
			for(int j = 0; j < groups.get(i).size(); j++) {
				System.out.println("\tPoint: ID: "+groups.get(i).get(j).getID()+", Coord: "+groups.get(i).get(j).getX()+", "+groups.get(i).get(j).getY());
			}
		}
		**/
	}
	
	public Hashtable<String, Double> calculateDistance(ArrayList<Point> list) {
		Hashtable<String, Double> disTable = new Hashtable<String, Double>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				disTable.put(i+"to"+j, u.calDis(list.get(i), list.get(j)));
			}
		}
		return disTable;
	}
	
	public Hashtable<String, ArrayList<Point>> markPoint(double e, int k, ArrayList<Point> list, Hashtable<String, Double> disTable) {
		Hashtable<String, ArrayList<Point>> result = new Hashtable<String, ArrayList<Point>>();
		ArrayList<Point> core = new ArrayList<Point>();
		for (int i = 0; i < list.size(); i++) {
			int count = 0;
			for (int j = 0; j < list.size(); j++) {
				if (disTable.get(i+"to"+j) <= e){
					count++;
				}
			}
			if (count >= k) {
				core.add(list.get(i));
				list.get(i).setIsCore(true);
			}
		}
		
		ArrayList<Point> border = new ArrayList<Point>();
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).getIsCore()){
				for (int j = 0; j < list.size(); j++) {
					if (disTable.get(i+"to"+j) <= e && list.get(j).getIsCore()) {
						//list.get(i).setType(1);
						border.add(list.get(i));
						break;
					}
				}
			}
		}
		
		result.put("core", core);
		result.put("border", border);
		return result;
	}
	
	public ArrayList<ArrayList<Point>> group(double e, int k, ArrayList<Point> core, Hashtable<String, Double> disTable) {
		ArrayList<ArrayList<Point>> groups = new ArrayList<ArrayList<Point>>();
		while(!core.isEmpty()) {
			ArrayList<Point> group = new ArrayList<Point>();
			for (int i = 0; i < core.size(); i++) {
				if(group.isEmpty()) {
					group.add(core.get(i));
					core.remove(i);
					i--;
				} else {
					if(inGroup(group, core.get(i), disTable, e)) {
						group.add(core.get(i));
						core.remove(i);
						i--;
					}
				}
			}
			groups.add(group);
		}
		return groups;
	}
	
	public ArrayList<ArrayList<Point>> addBorder(double e, int k, ArrayList<ArrayList<Point>> groups, ArrayList<Point> border, Hashtable<String, Double> disTable) {
		for (int i = 0; i < border.size(); i++) {
			for (int j = 0; j < groups.size(); j++) {
				if(inGroup(groups.get(j), border.get(i), disTable, e)){
					groups.get(j).add(border.get(i));
					break;
				}
			}
		}
		return groups;
	}
	
	public boolean inGroup(ArrayList<Point> list, Point p, Hashtable<String, Double> disTable, double e) {
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getID());
			if(disTable.get(list.get(i).getID()+"to"+p.getID()) <= e) {
				return true;
			}
		}
		return false;
	}

}

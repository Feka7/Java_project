import java.util.ArrayList;

public class Friend {
	
	// Rep Invariant
    /*   RI(c) =    c.name != null
     * 				&& c.category != null
     * 				&& (for all i, 0<= i < c.dimL ==> c.category.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.dimL, i !=j ==> c.category.get(i)!=  c.category.get(j))
     * 
     // Abstraction Function
    *    AF(c) = (password, <category.get(i)>) | 0 <= i < dimL

	*/
	
	private String name;
	private ArrayList<String> category;
	private int dimL;
	
	public Friend(String s, String cat) {
		name = s;
		category = new ArrayList<String>();
		category.add(cat);
		dimL = 1;
	}
	
	public String getFname() {
		return name;
	}
	
	public boolean compareName(String s) {
		return name.equals(s);
	}
	
	public boolean compareCatList(String s) {
		return category.contains(s);
	}
	
	public void addCategory(String s) {
		category.add(s);
		dimL++;
	}
	
	public void removeCategory(String s) {
		category.remove(s);
		dimL--;
	}
	
	public int sizeL() {
		return dimL;
	}
	
	public String getCategoryList(int i) {
		return category.get(i);
	}
	
	public ArrayList<String> getAllCategoryList() {
		return category;
	}
}

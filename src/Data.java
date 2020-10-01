import java.util.ArrayList;

public class Data {
	
	// Rep Invariant
    /*   RI(c) =    c.data != null
     * 				&& c.like != null
     * 				&& c.category != null
     * 				&& (for all i, 0<= i < c.like.size() ==> c.like.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.like.size(), i !=j ==> c.like.get(i)!=  c.like.get(j))
     * 
     // Abstraction Function
    *    AF(c) = (data, <like.get(i)>, category) | 0 <= i < like.size()

	*/
	
	private Object data;
	private ArrayList<String> like;
	private String category;
	
	public Data(Object x) {
		if(x == null) throw new NullPointerException();
		data = x;
		category = "none";
		like = new ArrayList<String>();
	}
	
	public void display() {
		System.out.println(data.toString());
		System.out.println(like.size());
		System.out.println(category);

	}
	
	public Object getData( ) {
		return data;
	}
	
	public int getLike() {
		return like.size();
	}
	
	public String getCategory() {
		return category;
	}
	
	public void addCategory(String s) {
		category = s;
	}
	
	public boolean addLike(String s) {
		if(like.contains(s)) {
			return false;
		} else {
			like.add(s);
			return true;
		}
	}
	
	public void removeLike(String s) {
		if(like.contains(s)) like.remove(s);
	}
	
	
}

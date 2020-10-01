import java.util.Vector;

public class Cat {
	// Rep Invariant
    /*   RI(c) =    c.category != null
     * 				&& c.friend != null
     * 				&& c.data != null
     * 				
     * 				&& (for all i, 0<= i < c.data.size() ==> c.data.get(i) !=null)
     * 				&& (for all i, 0<= i < c.freind.size() ==> c.friend.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.data.size(), i !=j ==> c.data.get(i).getData()!=  c.categories.data(j).getData())
     * 				&& (for all i, j 0<= i, j< c.friend.size(), i !=j ==> c.friend.get(i) != c.friend.get(y) )
     // Abstraction Function
    *    AF(c) = (password, <data.get(i)>, <friend.get(j)>) | 0 <= i < data.size() && 0 <= j < friend.size() 

	*/
	
		private String category;
		private Vector<String> friend;
		private Vector<Data> data;
		
		public Cat(String s) {
			category = s;
			friend = new Vector<String>();
			data = new Vector<Data>();
		}
		
		public String getCategory() {
			return category;
		}
		
		public void addFriend(String s) {
			friend.add(s);
			return;
		}
		
		public void removeFriend(String s) {
			friend.remove(s);
			for(int i = 0; i < data.size(); i++) {
				data.get(i).removeLike(s);
			}
			return;
		}
		
		public int containsF(String s) {
			 return friend.indexOf(s);
		}
		
		public Data getData(int num) {
			return data.get(num);
		}
		
		public void addData(Data d) {
			data.add(d);
			return;
		}
		
		public Data removeData(int num) {
			return data.remove(num);
			
		}
		
		public int containsD(Data d) {
			int index = -1, dim = this.data.size();
			
			 for(int i = 0; i < dim; i++) {
				 if(this.getData(i).equals(d)) {
					 index = i;
					 i = dim;
				 }
			 }
			 return index;
		}
		
		public Vector<Data> getDataList() {
			return this.data;
		}
		
		
		
}

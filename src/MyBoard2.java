import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;


public class MyBoard2<E extends Data> implements DataBoard<Data> {
	
	// Rep Invariant
    /*   RI(c) =    c.password != null
     * 				&& c.categories != null
     * 				&& c.friends != null
     * 				&& c.dati != null
     * 				&& (for all i, 0<= i < c.dimC ==> c.categories.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.dimC, i !=j ==> c.categories.get(i)!=  c.categories.get(j))
     * 				&& (for all i, 0<= i < c.dimF ==> c.friend.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.dimF, i !=j ==> c.friend.get(i).getFname!=  c.friend.get(j).getFname())
     * 				&& (for all i, 0<= i < c.dimD ==> c.data.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.dimD, i !=j ==> c.data.get(i).getData!=  c.data.get(j).getData())
     * 
     // Abstraction Function
    *    AF(c) = (password, <categories.get(i)>, <data.get(j)>, <friend.get(z)>) | 0 <= i < dimC, 0 <= j < dimD, 0 <= z < dimF 

	*/

	private ArrayList<String> categories;
	private ArrayList<Data> dati;
	private ArrayList<Friend> friends;
	private String password;
	private int dimC, dimF, dimD;
	
	public MyBoard2(String s) throws NullPointerException {
		if(s == null) throw new NullPointerException();
		password = s;
		categories = new ArrayList<String>();
		dati = new ArrayList<Data>();
		friends = new ArrayList<Friend>();
		dimC = 0;
		dimF = 0;
		dimD = 0;
	}

	@Override
	public void createCategory(String category, String passw)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new PasswordErrorException();
		if(categories.contains(category) == true) throw new NotValidCategoryExcpetion();
		categories.add(category);
		dimC++;
	}

	@Override
	public void removeCategory(String category, String passw)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new PasswordErrorException();
		if(categories.contains(category) != true) throw new NotValidCategoryExcpetion();
		categories.remove(category);
		dimC--;
		for(int i = 0; i < dimD; i++) {
			if(dati.get(i).getCategory().equals(category)) {
				dati.remove(i);
			}
		dimD = dati.size();
		}
		for(int i = 0; i < dimF; i++) {
			if(friends.get(i).getAllCategoryList().contains(category)) { 
				friends.get(i).removeCategory(category);
			}
			if(friends.get(i).sizeL() == 0) { 
				friends.remove(i);
			}
			dimF = friends.size();
		}
	}

	@Override
	public void addFriend(String category, String passw, String friend)
			throws NullPointerException, PasswordErrorException, NotValidFriendException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null || friend == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new PasswordErrorException();
		if(categories.contains(category) != true) throw new NotValidCategoryExcpetion();
		int res = -1, dim = friends.size(), index = 0;
		for(int i = 0; i < dimF; i++) {
			if(friends.get(i).compareName(friend)) {
				if(friends.get(i).compareCatList(category)) {
					res = 0;
					i = dim;
				}
				else {
					res = 1;
					index = i;
					i = dim;
				}
			}
		}
		if(res < 0) {
			Friend tmp = new Friend(friend, category);
			friends.add(tmp);
			dimF++;
		}
		else if(res > 0) {
			friends.get(index).addCategory(category);
		}
		else throw new NotValidFriendException();
	}
	
	@Override
	public void removeFriend(String category, String passw, String friend)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion, NotValidFriendException {
		// TODO Auto-generated method stub
		if(category == null || passw == null || friend == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new PasswordErrorException();
		int res = -1;
		for(int i = 0; i < dimF; i++) {
			Friend tmp = friends.get(i);
			if(tmp.compareName(friend)) {
				res++;
				if(tmp.compareCatList(category)) {
					friends.get(i).removeCategory(category);
					if(friends.get(i).sizeL() == 0) {
						friends.remove(i);
						dimF--;
					}
					i = dimF;
					res++;
				}
			}
		}
		if(res > 0) return;
		else if (res < 0) throw new NotValidFriendException();
		else throw new NotValidCategoryExcpetion();
	
	}

	@Override
	public boolean put(String passw, Data dato, String category)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion, NotValidDataException {
		// TODO Auto-generated method stub
		if(category == null || passw == null || dato == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		if(categories.contains(category) != true) throw new NotValidCategoryExcpetion();
		if(this.containsData(dato) > 0) throw new NotValidDataException();
		dato.addCategory(category);
		dati.add(dato);
		dimD++;
		return true;
	}

	@Override
	public Data get(String passw, Data dato)
			throws NullPointerException, PasswordErrorException, NotFoundDataExcpetion {
		// TODO Auto-generated method stub
		if(passw == null || dato == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int index = this.containsData(dato);
		if(index >= 0) return dati.get(index);
		else throw new NotFoundDataExcpetion();
	}

	@Override
	public Data remove(String passw, Data dato)
			throws NullPointerException, PasswordErrorException, NotFoundDataExcpetion {
		// TODO Auto-generated method stub
		if(passw == null || dato == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int index = this.containsData(dato);
		if(index >= 0) { 
			dimD--;
			return dati.remove(index);
		}
		else throw new NotFoundDataExcpetion();
	}

	@Override
	public ArrayList<Data> getlist(String category, String passw)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new PasswordErrorException();
		if(categories.contains(category) != true) throw new NotValidCategoryExcpetion();
		ArrayList <Data> tmp = new ArrayList<Data>();
		for(int i = 0; i < dimD; i++) {
			if(dati.get(i).getCategory().equals(category)) tmp.add(dati.get(i));
		}
		return tmp;
	}

	@Override
	public void insertLike(String friend, Data dato)
			throws NullPointerException, NotValidFriendException, NotFoundDataExcpetion, LikeAlreadyExistException {
		// TODO Auto-generated method stub
		if(friend == null || dato == null) throw new NullPointerException();
		if(this.containsFriend(friend) < 0) throw new NotValidFriendException();
		int pos = this.containsData(dato);
		if(pos >= 0) { 
			if(dati.get(pos).addLike(friend)) return; 
			else throw new LikeAlreadyExistException();
		}
		else throw new NotFoundDataExcpetion();
	}

	@Override
	public Iterator<Data> getIterator(String passw) throws NullPointerException, PasswordErrorException {
		// TODO Auto-generated method stub
		if(passw == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new PasswordErrorException();
		ArrayList <Data> All = new ArrayList<Data>();
		for(int i = 0; i < dimD; i++) {
			All.add(dati.get(i));
		}
		All.sort(new LikeComparator());
		Iterator<Data> tmp=new Iterator<Data>(){
			Iterator<Data> ite=All.iterator();
            @Override
            public boolean hasNext() {
                return ite.hasNext();
            }

            @Override
            public Data next() {
                return ite.next();
            } 

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
		};
		
		return tmp;
	}

	@Override
	public Iterator<Data> getFriendIterator(String friend) throws NullPointerException, NotValidFriendException {
		// TODO Auto-generated method stub
		if(friend == null) throw new NullPointerException();
		int pos = this.containsFriend(friend);
		ArrayList <Data> All = new ArrayList<Data>();
		if(pos < 0) throw new NotValidFriendException();
		else {
			for(int i = 0; i < friends.get(pos).sizeL(); i++) {
				String s =friends.get(pos).getCategoryList(i);
				for(int y = 0; y < dimD; y++) {
					if(dati.get(i).getCategory().equals(s)) All.add(dati.get(i));
				}
			}
		}
		Iterator<Data> tmp=new Iterator<Data>(){
			Iterator<Data> ite=All.iterator();
            @Override
            public boolean hasNext() {
                return ite.hasNext();
            }

            @Override
            public Data next() {
                return ite.next();
            } 

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
		};
		
		return tmp;
	}
	
	public int containsData(Data d) {
		int found = -1;
		for(int i = 0; i < dimD; i++) {
			if(dati.get(i).getData().equals(d.getData())) {
				found = i;
				i = dati.size();
			}
		}
		return found;
	}
	
	public int containsFriend(String s) {
		int found = -1;
		for(int i = 0; i < dimF; i++) {
			if(friends.get(i).compareName(s)) {
				found = i;
				i = dati.size();
			}
		}
		return found;
	}
	
	class LikeComparator implements Comparator<Data> {
		   
		@Override
		public int compare(Data a, Data b) {
			
			return b.getLike() - a.getLike();
		}
	}
	
	

}

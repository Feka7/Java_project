import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class MyBoard<E extends Data> implements DataBoard<Data> {
	
	// Rep Invariant
    /*   RI(c) =    c.password != null
     * 				&& c.categories != null
     * 				&& (for all i, 0<= i < c.dim ==> c.categories.get(i) !=null)
    //           	&& (for all i, j 0<= i, j< c.dim, i !=j ==> c.categories.get(i).getCategory()!=  c.categories.get(j).getCategory())
     * 
     // Abstraction Function
    *    AF(c) = (password, <categories.get(i)>) | 0 <= i < dim

	*/
	private Vector<Cat> categories;
	private String password;
	int dim;
	
	public MyBoard(String passw) throws NullPointerException {
		
		if(passw == null) throw new NullPointerException();
		categories = new Vector<Cat>();
		password = passw;
		dim = 0;
	}

	@Override
	public void createCategory(String category, String passw)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		if(this.containCategory(category) >= 0) throw new NotValidCategoryExcpetion();
		Cat tmp = new Cat(category);
		this.categories.add(tmp);
		dim++;
	}

	@Override
	public void removeCategory(String category, String passw)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int pos = this.containCategory(category);
		if(pos < 0) throw new NotValidCategoryExcpetion();
		else { this.categories.remove(pos);
				dim--;
		}
		
	}

	@Override
	public void addFriend(String category, String passw, String friend)
			throws NullPointerException, PasswordErrorException, NotValidFriendException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null || friend == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int pos = this.containCategory(category);
		if(pos < 0) throw new NotValidCategoryExcpetion();
		else {
			int posF = this.containFriend(pos, friend);
			if(posF >= 0) throw new NotValidFriendException();
			else this.categories.get(pos).addFriend(friend);
		}
		
		
	}

	@Override
	public void removeFriend(String category, String passw, String friend)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion, NotValidFriendException {
		// TODO Auto-generated method stub
		if(category == null || passw == null || friend == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int pos = this.containCategory(category);
		if(pos < 0) throw new NotValidCategoryExcpetion();
		else {
			int posF = this.containFriend(pos, friend);
			if(posF < 0) throw new NotValidFriendException();
			else {
				this.categories.get(pos).removeFriend(friend);
			}
		}
		
	}

	@Override
	public boolean put(String passw, Data dato, String category)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion, NotValidDataException {
		// TODO Auto-generated method stub
		if(category == null || passw == null || dato == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int pos = this.containCategory(category);
		if(pos < 0) throw new NotValidCategoryExcpetion();
		int t = 0, dim = this.categories.size();
		for(int i = 0; i < dim; i++) {
			if(this.categories.get(i).containsD(dato) >= 0) { t = 1; i = dim; }
		} 
		//int posD = this.containData(pos, dato);
			if(t > 0) throw new NotValidDataException();
			dato.addCategory(category);
			this.categories.get(pos).addData(dato);
			return true;
		
	}

	@Override
	public Data get(String passw, Data dato)
			throws NullPointerException, PasswordErrorException, NotFoundDataExcpetion {
		// TODO Auto-generated method stub
		if(passw == null || dato == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int index = -1, dim = this.categories.size(), pos = 0;
		for(int i = 0; i < dim; i++) {
			if(this.categories.get(i).containsD(dato) >= 0) {
				index = i;
				pos = this.categories.get(i).containsD(dato);
				i = dim;
			}
		}
		if(index < 0) throw new NotFoundDataExcpetion();
		else return this.categories.get(index).getData(pos);
	}

	@Override
	public Data remove(String passw, Data dato)
			throws NullPointerException, PasswordErrorException, NotFoundDataExcpetion {
		// TODO Auto-generated method stub
		if(passw == null || dato == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int index = -1, dim = this.categories.size(), pos = 0;
		for(int i = 0; i < dim; i++) {
			if(this.categories.get(i).containsD(dato) >= 0) {
				index = i;
				pos = this.categories.get(i).containsD(dato);
				i = dim;
			}
		}
		if(index < 0) throw new NotFoundDataExcpetion();
		else return this.categories.get(index).removeData(pos);
	}

	@Override
	public ArrayList<Data> getlist(String category, String passw)
			throws NullPointerException, PasswordErrorException, NotValidCategoryExcpetion {
		// TODO Auto-generated method stub
		if(category == null || passw == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		int pos = this.containCategory(category);
		if(pos < 0) throw new NotValidCategoryExcpetion();
		else {
			ArrayList<Data> tmp = new ArrayList<Data>(this.categories.get(pos).getDataList());
			return tmp;
		}
	}

	@Override
	public void insertLike(String friend, Data dato)
			throws NullPointerException, NotValidFriendException, NotFoundDataExcpetion, LikeAlreadyExistException {
		// TODO Auto-generated method stub
		if(friend == null || dato == null) throw new NullPointerException();
		int pos = -1, dim = this.categories.size();
		for(int i = 0; i < dim; i++) {
			if(this.containData(i, dato) >= 0) {
				if(this.containFriend(i, friend) >= 0) {
					pos = this.containData(i, dato);
					if(this.categories.get(i).getData(pos).addLike(friend)) return;
					else throw new LikeAlreadyExistException();
				}
				else throw new NotValidFriendException();
				
			} 
				
		}
		throw new NotFoundDataExcpetion();
		
	}

	@Override
	public Iterator<Data> getIterator(String passw) throws NullPointerException, PasswordErrorException {
		// TODO Auto-generated method stub
		if(passw == null) throw new NullPointerException();
		if(this.password.equals(passw) == false) throw new PasswordErrorException();
		Vector<Data> All = new Vector<Data>();
		for(int i = 0; i < this.categories.size(); i++) {
			All.addAll(this.categories.get(i).getDataList());
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
		Vector<Data> All = new Vector<Data>();
		for(int i = 0; i < this.categories.size(); i++) {
			if(this.categories.get(i).containsF(friend) >= 0) {
				All.addAll(this.categories.get(i).getDataList());
			}
		}
		if(All.size() == 0) throw new NotValidFriendException();
		
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
	
	public int containCategory(String s) {
		int dim = this.categories.size();
		int index = -1, i;
		for(i = 0; i < dim; i++) {
			if(this.categories.get(i).getCategory().equals(s)) { 
				index = i;
				i = dim;
			}
		}
		return index;
	}
	
	public int containFriend(int num, String s) {
		if(num < 0) return num;
		else return this.categories.get(num).containsF(s);
	}
	
	public int containData(int num, Data d) {
		if(num < 0) return num;
		else return this.categories.get(num).containsD(d);
	}
	
	class LikeComparator implements Comparator<Data> {
	   
		@Override
		public int compare(Data a, Data b) {
			
			return b.getLike() - a.getLike();
		}
	}
		
	
}

import java.util.ArrayList;
import java.util.Iterator;

public interface DataBoard<E extends Data> {
	/*OVERVIEW: DataBoard è un insieme di dati modificabile di lunghezza variabile
	 * 			formato da tre tipologie di elementi chiamati Data, Friend e Category
	 * 			e dotato di password per accedere ad alcune sue funzioni*/
	/*TYPICAL ELEMENT: (passw, database) */
	
	//Crea una categoria di dati se vengono rispettati i controlli di identità
	public void createCategory(String category, String passw) throws NullPointerException, PasswordErrorException, 
																	 NotValidCategoryExcpetion;
	//REQUIRES: category != null && passw != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category è gia contenuta in DataBoard lancia NotValidCategoryExcpetion*/
	//MODIFIES: this
	//EFFECTS: crea in this la nuova categoria category
	
	// Rimuove  una categoria di dati se vengono rispettati i controlli di identità
	public void removeCategory(String category, String passw) throws NullPointerException, PasswordErrorException, 
															  NotValidCategoryExcpetion;
	//REQUIRES: category != null && passw != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category non è contenuta in DataBoard lancia NotValidCategoryExcpetion*/
	//MODIFIES: this
	//EFFECTS: rimuove da this la categoria category

	// Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità
	public void addFriend(String category, String passw, String friend) throws NullPointerException, PasswordErrorException,
																			   NotValidFriendException, NotValidCategoryExcpetion;
	//REQUIRES: category != null && passw != null && friend != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 *        se friend == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category non è contenuta in DataBoard lancia NotValidCategoryExcpetion
	 * 		  se friend è gia contenuto in DataBoard.category lancia NotValidFriendException*/
	//MODIFIES: this
	//EFFECTS: aggiunge alla categoria category un nuovo amico friend
	
	// rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità
	public void removeFriend(String category, String passw, String friend) throws NullPointerException, PasswordErrorException, 
	  																			  NotValidCategoryExcpetion, NotValidFriendException;
	//REQUIRES: category != null && passw != null && friend != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 *        se friend == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category non è contenuta in DataBoard lancia NotValidCategoryExcpetion
	 * 		  se friend non è contenuto in DataBoard.category lancia NotValidFriendException*/
	//MODIFIES: this
	//EFFECTS: rimuove dalla categoria category friend
	
	//Inserisce un dato in bacheca se vengono rispettati i controlli di identità
	public boolean put(String passw, Data dato, String category) throws NullPointerException, PasswordErrorException, 
	  																		  NotValidCategoryExcpetion, NotValidDataException;
	//REQUIRES: category != null && passw != null && dato != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 *        se dato == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category non è contenuta in DataBoard lancia NotValidCategoryExcpetion
	 *        se dato è gia contenuto in DataBoard lancia NotValidDataException*/
	//MODIFIES: this
	//EFFECTS: aggiunge dato a this
	
	//Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità
	public E get(String passw, Data dato) throws NullPointerException, PasswordErrorException, 
	 														 NotFoundDataExcpetion;
	//REQUIRES: category != null && passw != null && dato != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 *        se dato == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category non è contenuta in DataBoard lancia NotValidCategoryExcpetion
	 *        se dato non è contenuto in DataBoard lancia NotFoundDataExcpetion*/
	//EFFECTS: restituisce una copia di this.categories.get(dato)
	
	//Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità
	public E remove(String passw,  Data dato) throws NullPointerException, PasswordErrorException, 
	 															NotFoundDataExcpetion;
	//REQUIRES: category != null && passw != null && dato != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 *        se dato == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category non è contenuta in DataBoard lancia NotValidCategoryExcpetion
	 *        se dato non è contenuto in DataBoard lancia NotFoundDataExcpetion*/
	//MODIFIES: this
	//EFFECTS: rimuove dato da DataBoard
	
	//Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità
	public ArrayList<Data> getlist(String category, String passw) throws NullPointerException, PasswordErrorException, 
	  																		  NotValidCategoryExcpetion;
	//REQUIRES: category != null && passw != null
	/*THROWS: se category == null lancia NullPointerException
	 * 		  se passw == null lancia NullPointerException
	 * 		  se passw != this.password lancia PasswordErrorException
	 * 		  se category è gia contenuta in DataBoard lancia NotValidCategoryExcpetion*/
	//MODIFIES: this
	//EFFECTS: restituisce un ArrayList contenente tutti i dati presenti in DataBoard
	
	// Aggiunge un like a un dato se vengono rispettati i controlli di identità
	void insertLike(String friend, Data dato) throws NullPointerException, NotValidFriendException, NotFoundDataExcpetion, LikeAlreadyExistException;
	//REQUIRES: friend != null && dato != null
	/*THROWS: se friend == null lancia NullPointerException
	 *        se dato == null lancia NullPointerException
	 *        se dato non è contenuto in DataBoard lancia NotFoundDataExcpetion
	 *        se friend non è contenuto in DataBoard lancia NotValidFriendException
	 *        se friend è già presente nella lista dei like di dato lancia LikeAlreadyExistException*/
	//MODIFIES: this
	//EFFECTS: aggiunge alla lista dei like di dato friend se non è già presente
	
	//restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
	//se vengono rispettati i controlli di identità
	public Iterator<E>getIterator(String passw) throws NullPointerException, PasswordErrorException;
	
	//restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi
	public Iterator<E> getFriendIterator(String friend) throws NullPointerException, NotValidFriendException;
	
}


class NotValidFriendException extends Exception {
	public NotValidFriendException() {
		return;
	}
	public NotValidFriendException(String s) {
		super(s);
	}
}


class NotFoundDataExcpetion extends Exception {
	
	public NotFoundDataExcpetion() {
		return;
	}
	public NotFoundDataExcpetion(String s) {
		super(s);
	}
}


class PasswordErrorException extends Exception {
	public PasswordErrorException() {
		return;
	}
	public PasswordErrorException(String s) {
		super(s);
	}
}


class NotValidCategoryExcpetion extends Exception {
	public NotValidCategoryExcpetion() {
		return;
	}
	public NotValidCategoryExcpetion(String s) {
		super(s);
	}
}


class NotValidDataException extends Exception {
	public NotValidDataException() {
		return;
	}
	public NotValidDataException(String s) {
		super(s);
	}
}


class LikeAlreadyExistException extends Exception {
	public LikeAlreadyExistException() {
		return;
	}
	public LikeAlreadyExistException(String s) {
		super(s);
	}
}


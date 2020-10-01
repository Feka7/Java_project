import java.util.Iterator;

public class test {

	public static void main(String[] args) throws NullPointerException, PasswordErrorException, 
	NotValidCategoryExcpetion, NotValidDataException, NotFoundDataExcpetion, LikeAlreadyExistException, NotValidFriendException {
		// TODO Auto-generated method stub
		
		
		Data one = new Data("hey");
		Data two = new Data(87);
		Data three = new Data("hi");
		String friend1 = "a";
		String friend2 = "b";
		String category = "word";
		String category2 = "word2";
		String category3 = "word3";
		String passw = "ok";
		String passwErr ="okErr";
		Iterator<Data> it;
		DataBoard<Data> Test = new MyBoard<Data>(passw);
		DataBoard<Data> Test2 = new MyBoard2<Data>(passw);
		
		//Crea categoria in DataBoard
		Test.createCategory(category, passw);
		
		
		//Non aggiunge categoria se già presente in Databoard
		try {
			Test.createCategory(category, passw);
		}
		catch (NotValidCategoryExcpetion e) {
			System.out.println("Test ok");
		}
		
		//Non aggiunge categoria se password è errata
		try {
			Test.createCategory(category2, passwErr);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Rimuove categoria in Databoard se presente
		try {
		Test.removeCategory(category, passw);
		}
		catch (NotValidCategoryExcpetion e) {
		}
		
		//Lancia eccezione se la categoria da rimuovere non è presente
		try {
			Test.removeCategory(category, passw);
		}
		catch (NotValidCategoryExcpetion e) {
			System.out.println("Test ok");
		}
		
		//Non rimuove la categoria se la password è errata
		Test.createCategory(category, passw);
		try {
			Test.removeCategory(category, passwErr);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Aggiunge friend ad una categoria presente in DataBoard
		Test.addFriend(category, passw, friend1);
		
		//Non aggiunge friend se già presente nella stessa categoria in DataBoard 
		try {
		Test.addFriend(category, passw, friend1);
		}
		catch (NotValidFriendException e) {
			System.out.println("Test ok");

		}
		
		//Non aggiunge friend se la password è errata
		try {
			Test.addFriend(category, passwErr, friend2);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Non aggiunge friend se la categoria non è presente in DataBoard
		try {
			Test.addFriend(category3, passw, friend2);
		}
		catch (NotValidCategoryExcpetion e) {
			System.out.println("Test ok");
		}
		
		//Rimuove friend se presente nella categoria in DataBoard
		Test.removeFriend(category, passw, friend1);
		
		Test.addFriend(category, passw, friend1);
		
		//Non rimuove friend se la password è errata
		try {
		Test.removeFriend(category, passwErr, friend1);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Non rimuove friend se la categoria non è presente in DataBoard
		try {
			Test.removeFriend(category3, passw, friend1);
		}
		catch (NotValidCategoryExcpetion e) {
			System.out.println("Test ok");
		}
		
		//Inserisce dato nella categoria in DataBoard
		Test.put(passw, one, category);
		
		//Non inserisce dato se già presente in DataBoard
		try {
			Test.put(passw, one, category);
		}
		catch (NotValidDataException e) {
			System.out.println("Test ok");
		}
		
		//Non inserisce dato se la categoria non è presente in DataBoard
		try {
			Test.put(passw, two, category3);
		}
		catch (NotValidCategoryExcpetion e) {
			System.out.println("Test ok");
		}
		
		//Non inserisce dato se la password è errata
		try {
			Test.put(passwErr, two, category);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Restituisce copia dato in DataBoard
		Test.get(passw, one).display();
		
		//Non restituisce copia dato se non è presente in DataBoard
		try {
			Test.get(passw, two);
		}
		catch (NotFoundDataExcpetion e) {
			System.out.println("Test ok");

		}
		
		//Non restituisce copia dato se la password è errata
		try {
			Test.get(passwErr, one);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Rimuove dato in DataBoard
		Test.remove(passw, one);
		
		//Lancia eccezione se il dato non è presente in DataBoard
		try {
			Test.remove(passw, one);
		}
		catch (NotFoundDataExcpetion e) {
			System.out.println("Test ok");

		}
		
		Test.put(passw, one, category);
		
		//Non rimuove dato se la password è errata
		try {
			Test.remove(passwErr, one);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Ritorna la lista dei dati presenti nella categoria
		Test.getlist(category, passw);
		
		//Non restituisce la lista se la categoria non è presente
		try {
			Test.getlist(category3, passw);
		}
		catch (NotValidCategoryExcpetion e) {
			System.out.println("Test ok");
		}
		
		//Non restituisce la categoria se la password è errata
		try {
			Test.getlist(category, passwErr);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Aggiunge like a un dato presente in DataBoard
		Test.insertLike(friend1, one);
		
		//Non aggiunge like se il dato non è presente in DataBoard
		try {
			Test.insertLike(friend1, two);
		}
		catch (NotFoundDataExcpetion e) {
			System.out.println("Test ok");

		}
		
		//Non aggiunge like se friend non è presente in DataBoard
		try {
			Test.insertLike(friend2, one);
		}
		catch (NotValidFriendException e) {
			System.out.println("Test ok");

		}
		
		//Non aggiunge like se friend ha precedentemente aggiunto un like allo stesso dato
		try {
			Test.insertLike(friend1, one);
		}
		catch (LikeAlreadyExistException e) {
			System.out.println("Test ok");

		}
		
		//Restituisce iteratore contenente tutti i dati in DataBoard
		Test.getIterator(passw);
		
		//Non restituisce iteratore se la password è errata
		try { 
			Test.getIterator(passwErr);
		}
		catch (PasswordErrorException e) {
			System.out.println("Test ok");

		}
		
		//Restituisce iteratore contenente tutti i dati condivisi con un friend
		Test.getFriendIterator(friend1);
		
		//Non restituisce iteratore se friend non è presente in DataBoard
		try { 
			Test.getFriendIterator(friend2);
		}
		catch (NotValidFriendException e) {
			System.out.println("Test ok");

		}
		
		//Test.removeFriend(cat2, passw, b);
		it = Test.getIterator(passw);
		
		//Non esegue l'operazione remove sull'iteratore
		try {
			it.remove();
		}
		catch(UnsupportedOperationException e) {
			System.out.println("Test ok");
		}
		
	    System.out.println("List elements : "); 
	    
        while (it.hasNext()) {
            it.next().display(); 
 
        System.out.println(); 
    
        } 
       
      //Test seconda implementazione
        System.out.println("Inizio seconda implementazione");
        one = three;
      //Crea categoria in DataBoard
      		Test2.createCategory(category, passw);
      		
      		
      		//Non aggiunge categoria se già presente in Databoard
      		try {
      			Test2.createCategory(category, passw);
      		}
      		catch (NotValidCategoryExcpetion e) {
      			System.out.println("Test ok");
      		}
      		
      		//Non aggiunge categoria se password è errata
      		try {
      			Test2.createCategory(category2, passwErr);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Rimuove categoria in Databoard se presente
      		try {
      		Test2.removeCategory(category, passw);
      		}
      		catch (NotValidCategoryExcpetion e) {
      		}
      		
      		//Lancia eccezione se la categoria da rimuovere non è presente
      		try {
      			Test2.removeCategory(category, passw);
      		}
      		catch (NotValidCategoryExcpetion e) {
      			System.out.println("Test ok");
      		}
      		
      		//Non rimuove la categoria se la password è errata
      		Test2.createCategory(category, passw);
      		try {
      			Test2.removeCategory(category, passwErr);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Aggiunge friend ad una categoria presente in DataBoard
      		Test2.addFriend(category, passw, friend1);
      		
      		//Non aggiunge friend se già presente nella stessa categoria in DataBoard 
      		try {
      		Test2.addFriend(category, passw, friend1);
      		}
      		catch (NotValidFriendException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Non aggiunge friend se la password è errata
      		try {
      			Test2.addFriend(category, passwErr, friend2);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Non aggiunge friend se la categoria non è presente in DataBoard
      		try {
      			Test2.addFriend(category3, passw, friend2);
      		}
      		catch (NotValidCategoryExcpetion e) {
      			System.out.println("Test ok");
      		}
      		
      		//Rimuove friend se presente nella categoria in DataBoard
      		Test2.removeFriend(category, passw, friend1);
      		
      		Test2.addFriend(category, passw, friend1);
      		
      		//Non rimuove friend se la password è errata
      		try {
      		Test2.removeFriend(category, passwErr, friend1);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Non rimuove friend se la categoria non è presente in DataBoard
      		try {
      			Test2.removeFriend(category3, passw, friend1);
      		}
      		catch (NotValidCategoryExcpetion e) {
      			System.out.println("Test ok");
      		}
      		
      		//Inserisce dato nella categoria in DataBoard
      		Test2.put(passw, one, category);
      		
      		//Non inserisce dato se già presente in DataBoard
      		try {
      			Test2.put(passw, one, category);
      		}
      		catch (NotValidDataException e) {
      			System.out.println("Test ok");
      		}
      		
      		//Non inserisce dato se la categoria non è presente in DataBoard
      		try {
      			Test2.put(passw, two, category3);
      		}
      		catch (NotValidCategoryExcpetion e) {
      			System.out.println("Test ok");
      		}
      		
      		//Non inserisce dato se la password è errata
      		try {
      			Test2.put(passwErr, two, category);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Restituisce copia dato in DataBoard
      		Test2.get(passw, one).display();
      		
      		//Non restituisce copia dato se non è presente in DataBoard
      		try {
      			Test2.get(passw, two);
      		}
      		catch (NotFoundDataExcpetion e) {
      			System.out.println("Test ok");

      		}
      		
      		//Non restituisce copia dato se la password è errata
      		try {
      			Test2.get(passwErr, one);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Rimuove dato in DataBoard
      		Test2.remove(passw, one);
      		
      		//Lancia eccezione se il dato non è presente in DataBoard
      		try {
      			Test2.remove(passw, one);
      		}
      		catch (NotFoundDataExcpetion e) {
      			System.out.println("Test ok");

      		}
      		
      		Test2.put(passw, one, category);
      		
      		//Non rimuove dato se la password è errata
      		try {
      			Test2.remove(passwErr, one);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Ritorna la lista dei dati presenti nella categoria
      		Test2.getlist(category, passw);
      		
      		//Non restituisce la lista se la categoria non è presente
      		try {
      			Test2.getlist(category3, passw);
      		}
      		catch (NotValidCategoryExcpetion e) {
      			System.out.println("Test ok");
      		}
      		
      		//Non restituisce la categoria se la password è errata
      		try {
      			Test2.getlist(category, passwErr);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		Test2.get(passw, one).display();
      		//Aggiunge like a un dato presente in DataBoard
      		Test2.insertLike(friend1, one);
      		
      		//Non aggiunge like se il dato non è presente in DataBoard
      		try {
      			Test2.insertLike(friend1, two);
      		}
      		catch (NotFoundDataExcpetion e) {
      			System.out.println("Test ok");

      		}
      		
      		//Non aggiunge like se friend non è presente in DataBoard
      		try {
      			Test2.insertLike(friend2, one);
      		}
      		catch (NotValidFriendException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Non aggiunge like se friend ha precedentemente aggiunto un like allo stesso dato
      		try {
      			Test2.insertLike(friend1, one);
      		}
      		catch (LikeAlreadyExistException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Restituisce iteratore contenente tutti i dati in DataBoard
      		Test2.getIterator(passw);
      		
      		//Non restituisce iteratore se la password è errata
      		try { 
      			Test2.getIterator(passwErr);
      		}
      		catch (PasswordErrorException e) {
      			System.out.println("Test ok");

      		}
      		
      		//Restituisce iteratore contenente tutti i dati condivisi con un friend
      		Test2.getFriendIterator(friend1);
      		
      		//Non restituisce iteratore se friend non è presente in DataBoard
      		try { 
      			Test2.getFriendIterator(friend2);
      		}
      		catch (NotValidFriendException e) {
      			System.out.println("Test ok");

      		}
      		
      		
      		it = Test2.getIterator(passw);
      		
      	//Non esegue l'operazione remove sull'iteratore
    		try {
    			it.remove();
    		}
    		catch(UnsupportedOperationException e) {
    			System.out.println("Test ok");
    		}
      		
      	    System.out.println("List elements : "); 
      	    
              while (it.hasNext()) {
                  it.next().display(); 
       
              System.out.println(); 
          
              }
         
	}

}

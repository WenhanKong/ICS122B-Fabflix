package Fabflix;

import java.util.HashMap;

public class ShoppingCart {
	private HashMap<String, Integer> cartItems;
	private HashMap<String, Movie > movieItems;

	public ShoppingCart(){
	     cartItems = new HashMap<String, Integer>();
	     movieItems = new HashMap<String, Movie>();
	}
	public HashMap getCartItems(){
	       return cartItems;
	}
	public HashMap getMovieItems() {
		return movieItems;
	}
	public boolean contains(String movieID) {
		if(cartItems.containsKey(movieID)) {
	
			return true;
		}
		return false;
	}
	
	public void addToCart(String movieID, Movie movie){
		this.print();
		if (this.contains(movieID)){
//			System.out.println("duplicate movie");
//			System.out.println(IDItems.get(movie));
			int currentQuantity = cartItems.get(movieID);
//			System.out.println(currentQuantity);
			cartItems.put(movieID, currentQuantity+1);
//			IDItems.put(movie.getMovieID(), currentQuantity+1);
		}else{
//			System.out.println("this is a new movie");
			cartItems.put(movieID,1);
			movieItems.put(movieID, movie);
//			System.out.println(cartItems.get(movie));
//			IDItems.put(movie.getMovieID(), 1);
//			System.out.println(IDItems.get(movie.getMovieID()));
	    }
	      
	}
	public void changeQuantity(String movieID,int quantity){
		if(quantity > 0 ){
			cartItems.put(movieID,quantity);
//			IDItems.put(movie.getMovieID(), quantity);
		}else{
			cartItems.remove(movieID);
			movieItems.remove(movieID);
		}
	}
	//removes the item if it exists
	
	public void removeItem(String movieID){
		cartItems.remove(movieID);
//		IDItems.remove(m.getMovieID());
	}
	
	public void remove_all(){
		cartItems.clear();
//		IDItems.clear();
	}
	
	public void print() {
		for (String name: this.cartItems.keySet()){

	        System.out.println(name);
		}
		for(Integer key : cartItems.values()) {
			System.out.println(key);
		}
	}
	
//	public void printValues() {
//		for(String name : this.IDItems.keySet()) {
//			
//		}
//	}
}

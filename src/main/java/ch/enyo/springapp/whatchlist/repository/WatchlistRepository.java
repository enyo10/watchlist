package ch.enyo.springapp.whatchlist.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.enyo.springapp.whatchlist.domaine.WatchlistItem;

@Service
public class WatchlistRepository {
	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	public List<WatchlistItem> getList(){
		return watchlistItems;
	}
	
	public void addItem(WatchlistItem watchlistItem) {
		watchlistItem.setId(index++);
		watchlistItems.add(watchlistItem);
	}
	
	public WatchlistItem findById(Integer id) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}
	
	public WatchlistItem findByTitle(String title) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getTitle().equals(title)) {
				return watchlistItem;
			}
		}
		return null;		
	}


}

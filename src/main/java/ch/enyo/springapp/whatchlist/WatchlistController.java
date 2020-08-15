package ch.enyo.springapp.whatchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WatchlistController {

	List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
	
		if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }
		
		WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());
		
		if (existingItem == null) {
			watchlistItem.setId(index++);
			watchlistItems.add(watchlistItem);
			
		} else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setTitle(watchlistItem.getTitle());  
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/watchlist");
		
		return new ModelAndView(redirect);
	}

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
		Map<String, Object> model = new HashMap<String, Object>();
		
		WatchlistItem watchlistItem = findWatchlistItemById(id);
		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);
		}
		return new ModelAndView("watchlistItemForm" , model);
	}
	
	private WatchlistItem findWatchlistItemById(Integer id) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	@GetMapping("/watchlist")
	public ModelAndView getList() {

		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<String, Object>();

	
		model.put("watchlistItems", watchlistItems);
		model.put("numberOfMovies", watchlistItems.size());

		return new ModelAndView(viewName, model);
	}
}
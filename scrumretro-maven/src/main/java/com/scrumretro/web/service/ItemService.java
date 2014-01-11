package com.scrumretro.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrumretro.rest.Service;
import com.scrumretro.web.model.ItemResponse;
import com.scrumretro.worker.ItemWorker;

/**
 * 
 * @author Sanju Thomas
 *
 */
@Controller
public class ItemService extends Service{
	
	@Autowired
	private ItemWorker itemWorker;
	
	/**
	 * 
	 * @param itemWorker
	 */
	public void setItemWorker(final ItemWorker itemWorker) {
		this.itemWorker = itemWorker;
	}

	/**
	 * This method shall find the Item for given id.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item/findById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemResponse findById(@PathVariable("id") final String id){
		return itemWorker.findById(id);
	}
	
	/**
	 * This method shall find items for given project id.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item/findByRetrospectiveId/{retrospectiveId}", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemResponse> findByRetrospectiveId(@PathVariable("retrospectiveId") final String retrospectiveId){
		return itemWorker.findByRetrospectiveId(retrospectiveId);
	}

}

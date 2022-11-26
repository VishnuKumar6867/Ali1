package com.example.flipkart.services;

import com.example.flipkart.models.*;
import com.example.flipkart.repository.LoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdminService {

	@Autowired
	private LoginDetailsRepository adminRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RestTemplate rest;

	private final String allProductURL = "lb://PRODUCT/product/getAllproducts";
	private final String productRegisterURL = "lb://PRODUCT/product/register";
	private final String blockURL = "lb://PRODUCT/product/block/{product}";
	private final String unBlockURL = "lb://PRODUCT/product/unblock/{product}";
	private final String addItemURL = "lb://PRODUCT/product/addItem";
	//private final String searchItemURL = "lb://PRODUCT/product/searchItems;
	private final String saveOrderURL = "lb://SHOPPING/order/{itemId}";
	private final String getOrderURL= "lb://SHOPPING/order/ticket/{tnr}";
	private final String historyURL = "lb://SHOPPING/order/history/{email}";
	private final String cancelURL = "lb://SHOPPING/order/cancel/{tnr}";

	public List<ProductResponse> getAllProduct(){
		return rest.exchange(allProductURL, HttpMethod.GET, null,new ParameterizedTypeReference<List<ProductResponse>>(){}).getBody();
	}

	public ProductResponse registerProduct(ProductModel productModel) {
		return rest.exchange(productRegisterURL,HttpMethod.POST,new HttpEntity<>(productModel),ProductResponse.class).getBody();
	}

	public ProductResponse unBlockProduct(String product) {
		return rest.exchange(unBlockURL,HttpMethod.PUT,null,ProductResponse.class,product).getBody();
	}

	public ProductResponse blockProduct(String product) {
		return rest.exchange(blockURL,HttpMethod.PUT,null,ProductResponse.class,product).getBody();
	}

	public ItemResponse addItem(ItemModel itemModel) {
		return rest.exchange(addItemURL,HttpMethod.POST,new HttpEntity<>(itemModel),ItemResponse.class).getBody();
	}

	/*public List<SearchResponse> searchItem(SearchModel searchModel) {
		return rest.exchange(searchItemURL,HttpMethod.POST,new HttpEntity<>(searchModel),new ParameterizedTypeReference<List<SearchResponse>>(){}).getBody();
	}*/

	public OrderResponse saveOrder(OrderModel model, long itemId ){
		return rest.exchange(saveOrderURL, HttpMethod.POST, new HttpEntity<>(model), OrderResponse.class, itemId).getBody();
	}

	public OrderResponse getOrder(String tnr){
		return rest.exchange(getOrderURL, HttpMethod.GET, null, OrderResponse.class, tnr).getBody();
	}

	public List<OrderResponse> historyByMail(String email){
		return rest.exchange(historyURL, HttpMethod.GET, null,new ParameterizedTypeReference<List<OrderResponse>>(){}, email).getBody();
	}

	public String cancelOrder(String pnr){
		return rest.exchange(cancelURL, HttpMethod.DELETE, null, String.class, pnr).getBody();
	}

}

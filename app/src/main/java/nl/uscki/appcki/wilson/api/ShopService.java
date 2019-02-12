package nl.uscki.appcki.wilson.api;

import java.util.List;

import nl.uscki.appcki.wilson.models.common.Pageable;
import nl.uscki.appcki.wilson.models.shop.Order;
import nl.uscki.appcki.wilson.models.shop.Product;
import nl.uscki.appcki.wilson.models.shop.Store;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShopService {
    @GET("shops/")
    Call<List<Store>> getStores();

    @GET("shops/{store}/products/")
    Call<Pageable<Product>> getProductsForStore(@Path("store") Integer store, @Query("page") Integer page, @Query("size") Integer size);

    @GET("shops/orders/")
    Call<Pageable<Order>> getOrders(@Query("page") Integer page, @Query("size") Integer size);

    @POST("shops/{storeId}/products/{productId}/order")
    Call<Boolean> placeOrder(@Path("storeId") Integer storeId, @Path("productId") Integer productId, @Query("amount") Integer amount);
}

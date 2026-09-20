package org.example.uberbookingservice.Controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import okhttp3.OkHttpClient;
import org.example.uberbookingservice.apis.LocationServiceApi;
import org.example.uberbookingservice.apis.UberSocketApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Configuration
public class RetroConfig {

//    @Bean
//    public Retrofit retrofit(){
//        return new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
    @Autowired
    private EurekaClient eurekaClient;

    private String getServiceUrl(String serviceName) {
    try {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(serviceName, false);
        if (instanceInfo != null && instanceInfo.getHomePageUrl() != null) {
            return instanceInfo.getHomePageUrl();
        }
    } catch (Exception e) {
        // Log warning or fallback to container DNS if Eureka lookup fails during boot
    }

    // Fallback to internal Docker container service names
    if ("LOCATIONSERVICE".equalsIgnoreCase(serviceName)) {
        return "http://uber-location-service:7777/";
    }
    if ("UBERSOCKETSERVER".equalsIgnoreCase(serviceName)) {
        return "http://uber-socket-server:7779/";
    }

    throw new RuntimeException("Service " + serviceName + " could not be resolved.");
}

    @Bean
    public LocationServiceApi locationServiceApi(){
        return new Retrofit.Builder()
                .baseUrl(getServiceUrl("LOCATIONSERVICE"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(LocationServiceApi.class);
    }

    @Bean
    public UberSocketApi uberSocketApi(){
        return new Retrofit.Builder()
                .baseUrl(getServiceUrl("UBERSOCKETSERVER"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(UberSocketApi.class);
    }

}

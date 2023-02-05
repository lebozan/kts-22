package ftn.kts;

import com.graphhopper.api.GraphHopperWeb;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KtsUberApplication {

	@Bean
	public GraphHopperWeb graphHopperWeb() {
		String graphhopperApiKey = "083b8dd6-731b-44a7-9507-e76f9e0926b8";
		GraphHopperWeb gh = new GraphHopperWeb();
		gh.setKey(graphhopperApiKey);
		gh.setDownloader(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).
				readTimeout(5, TimeUnit.SECONDS).build());
		return gh;
	}

	public static void main(String[] args) {
		SpringApplication.run(KtsUberApplication.class, args);
	}

}

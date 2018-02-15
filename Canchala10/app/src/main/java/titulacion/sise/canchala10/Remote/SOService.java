package titulacion.sise.canchala10.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import titulacion.sise.canchala10.Remote.Data.CampoResponse;
import titulacion.sise.canchala10.Remote.Data.SedeResponse;

/**
 * Created by yovanny on 14/02/2018.
 */

public interface SOService {

    @GET("sede")
    Call<SedeResponse> getSedes();

    @GET("campo")
    Call<CampoResponse> getCampos();
}

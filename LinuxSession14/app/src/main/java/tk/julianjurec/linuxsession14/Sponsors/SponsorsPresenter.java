package tk.julianjurec.linuxsession14.Sponsors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Base.MainApplication;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.LecturesResponse;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.Model.SpeakersResponse;
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.Model.SponsorsResponse;
import tk.julianjurec.linuxsession14.Network.Api;

/**
 * Created by sp0rk on 22.03.17.
 */

public class SponsorsPresenter implements SponsorsContract.Presenter {
    private SponsorsFragment view;
    private Api api;

    @Inject
    public SponsorsPresenter(SponsorsFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        final String BASE_URL = "http://tramwaj.asi.wroclaw.pl:6937/";
        api = ((MainActivity) view.getActivity()).getRetrofit().create(Api.class);
        fetchSponsors();
    }

    private void fetchSponsors() {
        Call<SponsorsResponse> call = api.getSponsors();
        call.enqueue(new Callback<SponsorsResponse>() {
            @Override
            public void onResponse(Call<SponsorsResponse> call, Response<SponsorsResponse> response) {
                view.onSponsorsFetched(response.body().getSponsors());
            }

            @Override
            public void onFailure(Call<SponsorsResponse> call, Throwable t) {
                view.onSponsorsFetchFailure(t);
            }
        });
    }

}

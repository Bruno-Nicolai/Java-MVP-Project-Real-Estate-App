package co.imob.version1.presenter;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

//    @Override
//    public void purchaseData(SharedPreferences sharedPreferences) {
//
//        String savedEmail = sharedPreferences.getString("email", "");
//        String savedPassword = sharedPreferences.getString("password", "");
//
//    }
}
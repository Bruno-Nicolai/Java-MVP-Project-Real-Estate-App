package co.imob.version1.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import co.imob.version1.R;

public class MoreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    //    public void logout() {
//        SharedPreferences sharedPreferences = getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("email");
//        editor.remove("password");
//        editor.apply();
//
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish(); // Finalizar a atividade atual para que o usuário não possa voltar para a tela anterior usando o botão "Voltar"
//    }


//    public void logout() {
//        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("email");
//        editor.remove("password");
//        editor.apply();
//
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        LoginFragment loginFragment = new LoginFragment();
//        fragmentTransaction.replace(R.id.fragment_container, loginFragment);
//
//        fragmentTransaction.commit();
//    }

}
package br.com.elasnojogo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


import br.com.elasnojogo2.R;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
 //   private TextView sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

  //      sms = findViewById(R.id.textViewMensagem);
  //      sms.setText(getString(R.string.mensagem_home));

        View headView = navigationView.getHeaderView(0);
        ImageView imageProfile = headView.findViewById(R.id.nav_imageView);
        imageProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                      Intent i = new Intent(HomeActivity.this, PerfilFragment.class);
                      startActivity(i);
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_favoritos, R.id.nav_pesquisar, R.id.nav_sair).setDrawerLayout(drawer).build();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

              if (id == R.id.nav_home) {
                    replaceFragment(new HomeFragment());
                } else if (id == R.id.nav_favoritos) {
                    replaceFragment(new FavoritosFragment());
                } else if (id == R.id.nav_pesquisar){
                    replaceFragment(new PesquisaFragment());
                } else if (id == R.id.nav_sair){
                      Snackbar.make(toolbar,"SAIR",Snackbar.LENGTH_LONG).show();
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        replaceFragment(new HomeFragment());
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onBackPressed() {

    }
}
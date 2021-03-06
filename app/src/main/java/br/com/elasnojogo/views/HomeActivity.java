package br.com.elasnojogo.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static br.com.elasnojogo.constantes.Constantes.USUARIO;
import static br.com.elasnojogo.views.LoginActivity.GOOGLE_ACCOUNT;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private String nome;
    private CircleImageView nav_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enviaDadosFragmento();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View headerView = navigationView.getHeaderView(0);
        TextView nav_user = headerView.findViewById(R.id.nome_usuaria_drawer);
        CircleImageView nav_image = headerView.findViewById(R.id.nav_imageView);
        TextView nav_email = headerView.findViewById(R.id.textViewEmail);

        if (user != null) {
            nav_user.setText(user.getDisplayName());
            AppUtil.loadImageFromFirebase(getApplication(), nav_image);
            Picasso.get().load(user.getPhotoUrl()).into(nav_image);
            nav_email.setText(user.getEmail());
        }

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_meus_eventos,  R.id.nav_editarPerfil, R.id.nav_sair).setDrawerLayout(drawer).build();

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        initViews();
    }

    private void initViews() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nav_image  = headerView.findViewById(R.id.nav_imageView);
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            replaceFragment(new HomeFragment());
        } else if (id == R.id.nav_meus_eventos) {
            replaceFragment(new MeusEventosFragment());
        } else if (id == R.id.nav_sair) {
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_editarPerfil){
            replaceFragment(new PerfilFragment());
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        replaceFragment(new HomeFragment());
    }

    private void enviaDadosFragmento(){
        Bundle bundle = new Bundle();
        bundle.putString(USUARIO, nome);
        Fragment homeFragmento = new HomeFragment();
        homeFragmento.setArguments(bundle);
        replaceFragment(homeFragmento);
    }
}
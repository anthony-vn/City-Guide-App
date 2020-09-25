package com.example.cityguideapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cityguideapp.Common.LoginSignup.RetailerStartupScreen;
import com.example.cityguideapp.HelperClasses.HomeAdapter.CategoryAdapter;
import com.example.cityguideapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguideapp.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.cityguideapp.HelperClasses.Modal.CategoryClass;
import com.example.cityguideapp.HelperClasses.Modal.FeaturedHelperClass;
import com.example.cityguideapp.HelperClasses.Modal.MostViewedHelperClass;
import com.example.cityguideapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashbroad extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    RecyclerView recyclerView_featured, mostViewed_recycler, category_recycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView img_menuicon, img_add;
    LinearLayout contentView;

    static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashbroad);

        //Hooks
        recyclerView_featured = findViewById(R.id.featured_recycler);
        mostViewed_recycler = findViewById(R.id.mostViewed_recycler);
        category_recycler = findViewById(R.id.category_recycler);
        img_add = findViewById(R.id.imgAddNav);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawable_layout);
        navigationView = findViewById(R.id.navigationViews);
        img_menuicon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //hiển thị color cho icon navigation
        navigationView.setItemIconTintList(null);

        navigationDrawer();

        //run
        recyclerView_featured();
        mostViewed_recycler();
        category_recycler();
    }

    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        img_menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }


    //Icon add
    public void callRetailerScreens(View view){
        startActivity(new Intent(getApplicationContext(), RetailerStartupScreen.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
        }
        return true;
    }

    private void category_recycler() {
        category_recycler.setHasFixedSize(true);
        category_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<CategoryClass> categoryClasses = new ArrayList<>();
        categoryClasses.add(new CategoryClass(R.drawable.restaurant_image, "McMacdonald's"));
        categoryClasses.add(new CategoryClass(R.drawable.restaurant_image, "McMacdonald's"));
        categoryClasses.add(new CategoryClass(R.drawable.restaurant_image, "McMacdonald's"));

        adapter = new CategoryAdapter(categoryClasses);
        category_recycler.setAdapter(adapter);
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    private void mostViewed_recycler() {
        mostViewed_recycler.setHasFixedSize(true);
        mostViewed_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedHelperClasses = new ArrayList<>();
        mostViewedHelperClasses.add(new MostViewedHelperClass(R.drawable.restaurant_image, "McMacdonald's", "afasf afasf asfasfas asfsafc asf wdafsfa"));
        mostViewedHelperClasses.add(new MostViewedHelperClass(R.drawable.restaurant_image, "McMacdonald's", "afasf afasf asfasfas asfsafc asf wdafsfa"));
        mostViewedHelperClasses.add(new MostViewedHelperClass(R.drawable.restaurant_image, "McMacdonald's", "afasf afasf asfasfas asfsafc asf wdafsfa"));

        adapter = new MostViewedAdapter(mostViewedHelperClasses);
        mostViewed_recycler.setAdapter(adapter);

        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    private void recyclerView_featured() {
        recyclerView_featured.setHasFixedSize(true);
        recyclerView_featured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.make_a_call, "McMacdonald's", "afasf afasf asfasfas asfsafc asf wdafsfa"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ic_hotel, "McMacdonald's", "afasf afasf asfasfas asfsafc asf wdafsfa"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ic_shop, "McMacdonald's", "afasf afasf asfasfas asfsafc asf wdafsfa"));

        adapter = new FeaturedAdapter(featuredLocations);
        recyclerView_featured.setAdapter(adapter);


        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }


}
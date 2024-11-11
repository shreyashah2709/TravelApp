    package com.example.project.Activity;

    import android.os.Bundle;
    import android.view.View;
    import android.widget.ArrayAdapter;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import androidx.viewpager2.widget.CompositePageTransformer;
    import androidx.viewpager2.widget.MarginPageTransformer;

    import com.example.project.Adapter.CategoryAdapter;
    import com.example.project.Adapter.PopularAdapter;
    import com.example.project.Adapter.RecommendedAdapter;
    import com.example.project.Adapter.SliderAdapter;
    import com.example.project.Domain.Category;
    import com.example.project.Domain.ItemDomain;
    import com.example.project.Domain.Location;
    import com.example.project.Domain.SliderItems;
    import com.example.project.R;
    import com.example.project.databinding.ActivityMainBinding;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;

    public class MainActivity extends BaseActivity {
        ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());

            setContentView(binding.getRoot());

            initLocation();
            initBanner();
            initCategory();
            initrecommended();
            initpopular();

        }
        private void initpopular() {
            DatabaseReference myref = database.getReference("Popular");
            binding.progressBarPopular.setVisibility(View.VISIBLE);

            ArrayList<ItemDomain> list = new ArrayList<>();
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot issue: snapshot.getChildren()){
                            list.add(issue.getValue(ItemDomain.class));
                        }
                        if (!list.isEmpty()){
                            binding.recyclerViewPopular.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                            RecyclerView.Adapter adapter = new PopularAdapter(list);
                            binding.recyclerViewPopular.setAdapter(adapter);

                        }
                        binding.progressBarPopular.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        private void initrecommended() {
            DatabaseReference myref = database.getReference("Item");
            binding.progressBarRecommended.setVisibility(View.VISIBLE);

            ArrayList<ItemDomain> list = new ArrayList<>();
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot issue: snapshot.getChildren()){
                            list.add(issue.getValue(ItemDomain.class));
                        }
                        if (!list.isEmpty()){
                            binding.recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                            RecyclerView.Adapter adapter = new RecommendedAdapter(list);
                            binding.recyclerViewRecommended.setAdapter(adapter);

                        }
                        binding.progressBarRecommended.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        private void initCategory() {
            DatabaseReference myref = database.getReference("Category");
            binding.progressBarCategory.setVisibility(View.VISIBLE);
            ArrayList<Category> list = new ArrayList<>();
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot issue : snapshot.getChildren()) {
                            list.add(issue.getValue(Category.class));
                        }
                        if (!list.isEmpty()){
                            binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                            RecyclerView.Adapter<CategoryAdapter.ViewHolder> adapter = new CategoryAdapter(list);
                            binding.recyclerViewCategory.setAdapter(adapter);
                        }
                        binding.progressBarCategory.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        private void initLocation() {

            DatabaseReference myref = database.getReference("Location");
            ArrayList<Location> list = new ArrayList<>();
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot issue : snapshot.getChildren()) {
                            list.add(issue.getValue(Location.class));
                        }
                        ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        binding.locationSp.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        private void banners(ArrayList<SliderItems> items){

            binding.viewPagerSlider.setAdapter(new SliderAdapter(items,binding.viewPagerSlider));
            binding.viewPagerSlider.setClipToPadding(false);
            binding.viewPagerSlider.setClipChildren(false);
            binding.viewPagerSlider.setOffscreenPageLimit(3);
            binding.viewPagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(40));
            binding.viewPagerSlider.setPageTransformer(compositePageTransformer);


        }

        private void initBanner(){

            DatabaseReference myref = database.getReference("Banner");
            binding.progressBarBanner.setVisibility(View.VISIBLE);
            ArrayList<SliderItems> items = new ArrayList<>();
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()){
                        for (DataSnapshot issue: snapshot.getChildren()){

                            items.add(issue.getValue(SliderItems.class));

                        }
                        banners(items);
                        binding.progressBarBanner.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        }



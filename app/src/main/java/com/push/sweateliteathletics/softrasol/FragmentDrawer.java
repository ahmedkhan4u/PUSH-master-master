package com.push.sweateliteathletics.softrasol;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import androidx.appcompat.widget.Toolbar;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentDrawer extends Fragment {
    private static int[] icons = new int[]{
            R.drawable.ic_running,
            R.drawable.ic_statistic,
            R.drawable.ic_challenge,
            R.drawable.ic_personal_best,
            R.drawable.ic_history,
            R.drawable.ic_overall,
            R.drawable.calculator,
            R.drawable.ic_settings,


    };
    private static String[] titles = null;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private FragmentDrawerListener drawerListener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView recyclerView;

    public interface ClickListener {
        void onClick(View view, int i);

        void onLongClick(View view, int i);
    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int i);
    }

    static class RecyclerTouchListener implements OnItemTouchListener {
        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            this.gestureDetector = new GestureDetector(context, new SimpleOnGestureListener() {
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (!(child == null || this.clickListener == null || !this.gestureDetector.onTouchEvent(e))) {
                this.clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList();
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem(false,titles[i],icons[i]);
            navItem.setTitle(titles[i]);
            navItem.setIcon(icons[i]);
            data.add(navItem);
        }
        return data;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        this.recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        this.adapter = new NavigationDrawerAdapter(getActivity(), getData());
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), this.recyclerView, new ClickListener() {
            public void onClick(View view, int position) {
                FragmentDrawer.this.drawerListener.onDrawerItemSelected(view, position);
                FragmentDrawer.this.mDrawerLayout.closeDrawer(FragmentDrawer.this.containerView);
            }

            public void onLongClick(View view, int position) {
            }
        }));
        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        this.containerView = getActivity().findViewById(fragmentId);
        this.mDrawerLayout = drawerLayout;
        final Toolbar toolbar2 = toolbar;
        this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);

        this.mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                FragmentDrawer.this.getActivity().invalidateOptionsMenu();
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                FragmentDrawer.this.getActivity().invalidateOptionsMenu();
            }

            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar2.setAlpha(1.0f - (slideOffset / 2.0f));
            }
        };


        mDrawerToggle.setDrawerIndicatorEnabled(false);

       // toolbar2.setNavigationIcon(R.drawable.list);

        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);


            }
        });






        this.mDrawerLayout.post(new Runnable() {
            public void run() {
                FragmentDrawer.this.mDrawerToggle.syncState();
            }
        });
    }
}

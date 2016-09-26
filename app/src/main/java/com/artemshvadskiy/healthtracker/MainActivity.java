package com.artemshvadskiy.healthtracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.artemshvadskiy.healthtracker.model.Event;
import com.artemshvadskiy.healthtracker.model.Repository;
import com.artemshvadskiy.healthtracker.model.Tracker;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Inject Repository<Tracker> mTrackerRepository;
    @Inject Repository<Event> mEventRepository;

    private RecyclerView mEventList;
    private RecyclerView mTrackerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((HealthTrackerApplication) getApplication()).getRealmComponent().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTrackerActivity.startActivity(MainActivity.this, null);
            }
        });

        setupLists();
    }

    private void setupLists() {
        mTrackerList = (RecyclerView) findViewById(R.id.tracker_list);
        mTrackerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTrackerList.setAdapter(new TrackerRecyclerAdapter(mTrackerRepository.getAll(Tracker.class)));

        mEventList = (RecyclerView) findViewById(R.id.event_list);
        mEventList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mEventList.setAdapter(new EventRecyclerAdapter(mEventRepository.getAll(Event.class)));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class TrackerRecyclerAdapter extends RecyclerView.Adapter<TrackerRecyclerAdapter.ViewHolder> {
        private final List<Tracker> mTrackers;

        public TrackerRecyclerAdapter(List<Tracker> trackers) {
            mTrackers = trackers;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tracker_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Tracker tracker = mTrackers.get(position);
            holder.mTracker = tracker;
            holder.mName.setText(tracker.getName());
        }

        @Override
        public int getItemCount() {
            return mTrackers.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private Tracker mTracker;

            private TextView mName;
            private ImageButton mEditButton;
            private ImageButton mLogEventButton;

            public ViewHolder(View rootView) {
                super(rootView);

                mName = (TextView) rootView.findViewById(R.id.name);
                mEditButton = (ImageButton) rootView.findViewById(R.id.edit_button);
                mLogEventButton = (ImageButton) rootView.findViewById(R.id.log_event_button);

                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditTrackerActivity.startActivity(MainActivity.this, mTracker.getId());
                    }
                });

                mLogEventButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTracker.logEvent();
                    }
                });
            }
        }
    }

    private class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {
        private final List<Event> mEvents;

        public EventRecyclerAdapter(List<Event> events) {
            mEvents = events;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(new TextView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Event event = mEvents.get(position);
            holder.mText.setText("event " + event.getId() + ": " + event.getTrackerName()
                    + " at " + event.getDate());
        }

        @Override
        public int getItemCount() {
            return mEvents.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mText;

            public ViewHolder(View rootView) {
                super(rootView);
                mText = (TextView) rootView;
            }
        }
    }
}
